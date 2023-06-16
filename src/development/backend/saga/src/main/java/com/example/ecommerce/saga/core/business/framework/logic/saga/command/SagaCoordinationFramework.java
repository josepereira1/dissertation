package com.example.ecommerce.saga.core.business.framework.logic.saga.command;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.SagaDefinitionNotExistsException;
import com.example.ecommerce.saga.core.business.framework.resources.saga.ISagaFactory;
import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;
import com.example.ecommerce.saga.core.business.framework.resources.saga.SagaMetadata;
import com.example.ecommerce.saga.core.business.framework.resources.saga.SagaStatus;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.Participant;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.business.framework.utils.CommonSaga;
import com.example.ecommerce.saga.core.business.framework.utils.exceptions.SagaIdNotExistsInMessageException;
import com.example.ecommerce.saga.core.business.logs.ILogs;
import com.example.ecommerce.saga.core.business.messaging.factory.IMessageFactory;
import com.example.ecommerce.saga.core.business.messaging.logic.IMessagingBusiness;
import com.example.ecommerce.saga.core.business.messaging.resources.IMessage;
import com.example.ecommerce.saga.core.business.messaging.resources.MessageStatus;
import com.example.ecommerce.saga.core.business.messaging.resources.MessageType;
import com.example.ecommerce.saga.core.ports.in.coordination.ISagaCoordination;
import com.example.ecommerce.saga.core.ports.out.messaging.IMessagePublisher;
import com.example.ecommerce.saga.core.ports.out.saga.ICommandSagaRepository;
import com.example.ecommerce.saga.core.ports.out.saga.IQuerySagaRepository;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.IQuerySagaDefinitionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.everit.json.schema.ValidationException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SagaCoordinationFramework implements ISagaCoordination {

    @Autowired
    private ICommandSagaRepository commandSagaRepository;

    @Autowired
    private IQuerySagaRepository querySagaRepository;

    @Autowired
    private ISagaFactory sagaFactory;

    @Autowired
    private IMessageFactory messageFactory;

    @Autowired
    private IMessagingBusiness messagingBusiness;

    @Autowired
    private IMessagePublisher messagePublisher;

    @Autowired
    private ILogs logs;

    @Autowired
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private IQuerySagaDefinitionRepository querySagaDefinitionRepository;


    @Transactional(rollbackFor = Exception.class)
    public Saga initSaga(String id, String token, String entityId, String json) throws SagaDefinitionNotExistsException {
        Optional<SagaDefinition> optional = querySagaDefinitionRepository.findById(id);
        if(!optional.isPresent()) throw SagaDefinitionNotExistsException.builder().id(id).build();
        SagaDefinition sagaDefinition = optional.get();
        Saga saga = sagaFactory.factory(sagaDefinition.getId(), sagaDefinition.getParticipants().size());
        saga.setEntityId(entityId);
        Object input = messagingBusiness.getGson().fromJson(json, Object.class);
        saga.setInput(input);
        //  to obtain id
        saga = commandSagaRepository.save(saga);
        //  invoke first participant
        IMessage message = messageFactory.factory();
        message.setType(MessageType.COMMAND);
        JsonObject data = messagingBusiness.getGson().fromJson(json, JsonObject.class);
        JsonObject metadata = new JsonObject();
        metadata.addProperty("sagaId", saga.getId());
        metadata.addProperty("token", token);
        message.setData(data);
        message.setMetadata(metadata);
        logs.logInfo("[SAGA] - Init saga - saga: " + saga);
        invokeNextParticipant(saga, sagaDefinition, message);
        return saga;
    }

    public void handleMessage(String json) throws IOException, SagaDefinitionNotExistsException {
        IMessage message;
        String sagaId;

        logs.logInfo("[SAGA] - Message received - message: " + json);
        try{
            message = messagingBusiness.fromJson(json);
            sagaId = CommonSaga.getSagaMetadata(message.getMetadata()).getSagaId();
        } catch (ValidationException e){
            e.printStackTrace();
            logs.logError("[SAGA] - Json validator exception occur - message: " + json);
            return; //  caso haja problemas com validação de mensagens, é preferível terminar e não entrar em loop de tentativas
        } catch (JSONException e){
            e.printStackTrace();
            logs.logError("[SAGA] - Json exception (json struct) - message: " + json);
            return; //  caso haja problemas com validação de mensagens, é preferível terminar e não entrar em loop de tentativas
        } catch (SagaIdNotExistsInMessageException e) {
            e.printStackTrace();
            logs.logError("[SAGA] - " + e.getMessage() + " - message: " + json);
            return; //  caso haja problemas com validação de mensagens, é preferível terminar e não entrar em loop de tentativas

        }
        Optional<Saga> optional1 = querySagaRepository.findSagaById(sagaId);
        if(!optional1.isPresent()) { //  is possible that send of message is more faster than persistence of saga, due to this we make a sleep here
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                logs.logWarning("[SAGA] - Saga with id " +  sagaId + "do not exists, waiting 10 seconds for the next try");
                e.printStackTrace();
            }
            //  aqui nunca consegue ir buscar, mesmo que exista
            optional1 = querySagaRepository.findSagaById(sagaId);
            if(!optional1.isPresent()){
                logs.logError("[SAGA] - Saga with id " +  sagaId + "do not exists");
                return;   //  TODO deveria ser reportado um log aqui
            }
        }
        Saga saga = optional1.get();
        Optional<SagaDefinition> optional2 = querySagaDefinitionRepository.findById(saga.getName());
        if(!optional2.isPresent()) throw SagaDefinitionNotExistsException.builder().id(saga.getName()).build();
        analyzeMessageAndSaga(message, optional2.get(), saga);
    }

    /**
     * Invoke next participant based on status of the message
     * @param saga saga info
     * @param sagaDefinition saga process info
     * @param message message to send
     */
    private void invokeNextParticipant(Saga saga, SagaDefinition sagaDefinition, IMessage message){
        Participant participant;
        String method;
        if(saga.getStatus().equals(SagaStatus.ERROR)){
            saga.setPosition(saga.getPosition() - 1);
            participant = sagaDefinition.getParticipants().get(saga.getPosition());
            method = participant.getCompensatingTransaction();
        }
        else if(saga.getStatus().equals(SagaStatus.PENDING)){
            saga.setPosition(saga.getPosition() + 1);
            participant = sagaDefinition.getParticipants().get(saga.getPosition());
            method = participant.getLocalTransaction();
        }
        else {
            logs.logError("[SAGA] - Saga status do not match - status: " + saga.getStatus());
            return;
        }

        //  TODO TEMPORÁRIO
        saga.setCurrentService(participant.getServiceName());

        commandSagaRepository.save(saga);
        logs.logInfo("[SAGA] - Invoke next participant - exchange: " + sagaDefinition.getExchange() + " - routing: " + participant.getRoutingKey() + " - method: " + method + " - data: " + message.getData());

        if(participant != null) {
            //messaging.send(sagaDefinition.getExchange(), participant.getRouting(), communicationBusinessLogic.toJson(message));
            messagePublisher.publish(sagaDefinition.getExchange(), participant.getRoutingKey(), MessageType.COMMAND, MessageStatus.NO_STATUS, -1, "", method, message.getMetadata(), message.getData());
        }
    }

    @Transactional(rollbackFor = Exception.class)  //  to transactional messaging pattern
    protected void analyzeMessageAndSaga(IMessage message, SagaDefinition sagaDefinition, Saga saga) {
        if (message.getStatus() != null && message.getStatus().equals(MessageStatus.ERROR)){
            saga.setStatus(SagaStatus.ERROR);

            //  TODO TEMPORÁRIO
            saga.setErrorService(message.getOwner());

            if(message.getMessage().length() > 0 && message.getMessage().length() < 4000)saga.setMessage(message.getMessage());
            saga.setCode(message.getCode());
            saga = commandSagaRepository.save(saga);
        }

        if(!isLastParticipant(sagaDefinition, saga, message)) invokeNextParticipant(saga, sagaDefinition, message);
    }

    private boolean isLastParticipant(SagaDefinition sagaDefinition, Saga saga, IMessage message) {
        if (saga.getStatus() != null && saga.getStatus().equals(SagaStatus.ERROR) && saga.getPosition() == 0){
            saga.setEnd(LocalDateTime.now());
            commandSagaRepository.save(saga);
            return true;
        }
        else if (saga.getStatus() != null && saga.getStatus().equals(SagaStatus.PENDING) && saga.getPosition() == (saga.getSize() - 1)){
            saga.setStatus(SagaStatus.SUCCESS);
            saga.setEnd(LocalDateTime.now());
            logs.logInfo("[SAGA] - Commit saga");
            commit(sagaDefinition, saga);

            try {
                saga.setOutput(getFinalOutputFromMessage(message, sagaDefinition.getOutputParams()));
            } catch (Exception e) {
                saga.setOutput(null);
                logs.logError("[SAGA] - Error setting output of saga - message: " + message);
                e.printStackTrace();
            }
            saga.setCode(sagaDefinition.getSuccessfullyCode());
            saga.setMessage(sagaDefinition.getSuccessfullyMessage());
            commandSagaRepository.save(saga);
            return true;
        }
        return false;
    }

    public void commit(SagaDefinition sagaDefinition, Saga saga){
        logs.logInfo("[SAGA] - Invoked all participants (COMMIT) - exchange: " + sagaDefinition.getExchange() + " - routing: " + sagaDefinition.getCommitRoutingKey() + " - method: " + sagaDefinition.getCommitMethodName() + " - data: " + null);
        SagaMetadata sagaMetadata = new SagaMetadata();
        sagaMetadata.setSagaId(saga.getId());
        messagePublisher.publish(sagaDefinition.getExchange(), sagaDefinition.getCommitRoutingKey(), MessageType.COMMAND, MessageStatus.SUCCESS, 200, "Commit saga transactions.", sagaDefinition.getCommitMethodName(), sagaMetadata , null);
    }

    public Object getFinalOutputFromMessage(IMessage message, List<String> params) throws Exception {
        JsonObject input = message.getData().getAsJsonObject();
        JsonObject output = new JsonObject();

        for(String param : params)
            if(input.has(param))
                output.add(param, input.get(param));
        return new ObjectMapper().readValue(gson.toJson(output), Object.class);
    }
}
