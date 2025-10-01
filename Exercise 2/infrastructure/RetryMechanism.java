package com.satellite.command.infrastructure;

import java.util.function.Supplier;

public class RetryMechanism {
    private static final Logger logger = Logger.getInstance();
    private static final int DEFAULT_MAX_ATTEMPTS = 3;
    private static final long DEFAULT_DELAY_MS = 100;
    
    public static <T> T executeWithRetry(Supplier<T> operation, String operationName) {
        return executeWithRetry(operation, operationName, DEFAULT_MAX_ATTEMPTS, DEFAULT_DELAY_MS);
    }
    
    public static <T> T executeWithRetry(Supplier<T> operation, String operationName, 
                                       int maxAttempts, long delayMs) {
        Exception lastException = null;
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                logger.debug(String.format("Executing %s (attempt %d/%d)", 
                    operationName, attempt, maxAttempts));
                
                T result = operation.get();
                
                if (attempt > 1) {
                    logger.info(String.format("%s succeeded on attempt %d", operationName, attempt));
                }
                
                return result;
                
            } catch (Exception e) {
                lastException = e;
                logger.warn(String.format("%s failed on attempt %d: %s", 
                    operationName, attempt, e.getMessage()));
                
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Operation interrupted", ie);
                    }
                    delayMs *= 2; 
                }
            }
        }
        
        logger.error(String.format("%s failed after %d attempts", operationName, maxAttempts));
        throw new RuntimeException("Operation failed after " + maxAttempts + " attempts", lastException);
    }
}