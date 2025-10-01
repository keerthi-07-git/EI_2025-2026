package com.satellite.command.infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    private Logger() {}
    
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR, FATAL
    }
    
    public void debug(String message) {
        log(LogLevel.DEBUG, message, null);
    }
    
    public void info(String message) {
        log(LogLevel.INFO, message, null);
    }
    
    public void warn(String message) {
        log(LogLevel.WARN, message, null);
    }
    
    public void error(String message) {
        log(LogLevel.ERROR, message, null);
    }
    
    public void error(String message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }
    
    public void fatal(String message) {
        log(LogLevel.FATAL, message, null);
    }
    
    public void fatal(String message, Throwable throwable) {
        log(LogLevel.FATAL, message, throwable);
    }
    
    private void log(LogLevel level, String message, Throwable throwable) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String threadName = Thread.currentThread().getName();
        
        String logEntry = String.format("[%s] [%s] [%s] %s", 
            timestamp, level, threadName, message);
        
        System.out.println(logEntry);
        
        if (throwable != null) {
            System.err.println("Exception: " + throwable.getMessage());
            if (level == LogLevel.DEBUG || level == LogLevel.ERROR || level == LogLevel.FATAL) {
                throwable.printStackTrace();
            }
        }
    }
    
    public void logCommandExecution(String commandName, boolean success, long executionTimeMs) {
        String message = String.format("Command [%s] executed in %dms - %s", 
            commandName, executionTimeMs, success ? "SUCCESS" : "FAILED");
        info(message);
    }
    
    public void logStateChange(String component, String oldState, String newState) {
        String message = String.format("State change in %s: %s -> %s", 
            component, oldState, newState);
        info(message);
    }
}
