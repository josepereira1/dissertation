package com.ecommerce.consumer.core.business.logs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class Logs implements ILogs {

    @Value("${service}")
    private String SERVICE;

    @Value("${logs.on}")
    private String LOGS_ON;

    @Override
    public void log(String tag, String message) {
        if(LOGS_ON.toLowerCase().equals("no")) return;
        System.err.println(SERVICE.toUpperCase() + " - LOG[" + tag + "][" + LocalDateTime.now()+"] - " + message);
    }

    @Override
    public void logSuccess(String message){
        if(LOGS_ON.toLowerCase().equals("no")) return;
        System.err.println(SERVICE.toUpperCase() + " - LOG[SUCCESS][" + LocalDateTime.now()+"] - " + message);
    }

    @Override
    public void logWarning(String message){
        if(LOGS_ON.toLowerCase().equals("no")) return;
        System.err.println(SERVICE.toUpperCase() + " - LOG[WAR][" + LocalDateTime.now()+"] - " + message);
    }

    @Override
    public void logError(String message){
        if(LOGS_ON.toLowerCase().equals("no")) return;
        System.err.println(SERVICE.toUpperCase() + " - LOG[ERROR][" + LocalDateTime.now()+"] - " + message);
    }

    @Override
    public void logInfo(String message) {
        if(LOGS_ON.toLowerCase().equals("no")) return;
        System.err.println(SERVICE.toUpperCase() + " - LOG[INFO][" + LocalDateTime.now()+"] - " + message);
    }
}
