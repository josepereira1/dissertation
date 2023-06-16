package com.ecommerce.qp.inbound.messaging.version;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.logic.IMessagingBusiness;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.ports.in.version.ICommandVersion;
import com.ecommerce.qp.inbound.messaging.version.exceptions.MaximumVersionCheckAttemptsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilsVersion implements IUtilsVersion{

    private static final Integer WAITING_TIME_REFERENCE =  3000;
    private static final Integer MAX_TRIES = 5000;

    @Autowired
    private ICommandVersion commandVersion;

    @Autowired
    private IMessagingBusiness messagingBusiness;

    @Autowired
    private ILogs logs;

    @Override
    public Version isNextVersion(IMessage message) throws EcommerceBusinessLogicException, InterruptedException {
        int attempts = 0, differenceToCurrentVersion;
        long waitingTime;
        Version version = messagingBusiness.getGson().fromJson(message.getMetadata(), Version.class);
        while(true){
            differenceToCurrentVersion = commandVersion.isNext(version);
            attempts++;
            if(differenceToCurrentVersion == 0){
                break;
            }else {
                if(attempts > MAX_TRIES) throw MaximumVersionCheckAttemptsExceededException.builder().groupId(version.getGroupId()).attempts(attempts).build();
                waitingTime = (long) (((double) differenceToCurrentVersion/100) * WAITING_TIME_REFERENCE);
                logs.logError("[CQRS] - wait: version: " + version + " - attempts: " + attempts + " - differenceToCurrentVersion: " + differenceToCurrentVersion + " - waitingTime: " + waitingTime);
                Thread.sleep(waitingTime);
            }
        }
        return version;
    }

    @Override
    public Version nextVersion(Version version) throws EcommerceBusinessLogicException {
        return commandVersion.next(version);
    }
}
