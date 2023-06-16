package com.ecommerce.qp.core.business.logs;

public interface ILogs {
    void logSuccess(String message);
    void logWarning(String message);
    void logError(String message);
    void logInfo(String message);
    void log(String tag, String message);
}
