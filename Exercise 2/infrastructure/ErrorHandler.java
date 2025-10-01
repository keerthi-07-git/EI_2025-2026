package com.satellite.command.infrastructure;

import com.satellite.command.command.CommandResult;
import com.satellite.command.exception.SatelliteException;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    private static final Logger logger = Logger.getInstance();
    private static final Map<Class<? extends Exception>, String> ERROR_MESSAGES = new HashMap<>();
    
    static {
        ERROR_MESSAGES.put(IllegalArgumentException.class, "Invalid input provided");
        ERROR_MESSAGES.put(NullPointerException.class, "Required parameter is missing");
        ERROR_MESSAGES.put(SatelliteException.class, "Satellite operation failed");
    }
    
    public static CommandResult handleException(String commandName, Exception exception) {
        logger.error("Exception in command execution", exception);
        
        String userMessage = ERROR_MESSAGES.getOrDefault(
            exception.getClass(), 
            "An unexpected error occurred"
        );
        
        String detailMessage = String.format("%s: %s", userMessage, exception.getMessage());
        
        return new CommandResult(commandName, detailMessage, exception);
    }
    
    public static void logAndRethrow(String context, Exception exception) throws Exception {
        logger.error("Critical error in " + context, exception);
        throw exception;
    }
}