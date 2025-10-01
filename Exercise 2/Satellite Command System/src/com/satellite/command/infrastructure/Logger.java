package com.satellite.command.infrastructure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    private static final String LOG_DIRECTORY = "logs";
    private static final String LOG_FILE_NAME = "satellite-system.log";
    private PrintWriter fileWriter;
    private boolean fileLoggingEnabled = true;
    
    private Logger() {
        initializeLogFile();
    }
    
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    private void initializeLogFile() {
        try {
            File logDir = new File(LOG_DIRECTORY);
            if (!logDir.exists()) {
                logDir.mkdirs();
                System.out.println("Created logs directory: " + logDir.getAbsolutePath());
            }
            
            File logFile = new File(LOG_DIRECTORY, LOG_FILE_NAME);
            fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)), true);
            
            String sessionStart = "\n" + "=".repeat(80) + "\n";
            sessionStart += "SESSION STARTED: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            sessionStart += "\n" + "=".repeat(80) + "\n";
            fileWriter.println(sessionStart);
            
            System.out.println("Logging to file: " + logFile.getAbsolutePath());
            
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            fileLoggingEnabled = false;
        }
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
        
        if (fileLoggingEnabled && fileWriter != null) {
            fileWriter.println(logEntry);
            
            if (throwable != null) {
                fileWriter.println("Exception: " + throwable.getMessage());
                throwable.printStackTrace(fileWriter);
            }
        }
        
        if (throwable != null && (level == LogLevel.ERROR || level == LogLevel.FATAL)) {
            System.err.println("Exception: " + throwable.getMessage());
            throwable.printStackTrace();
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
    
    public void close() {
        if (fileWriter != null) {
            String sessionEnd = "\n" + "=".repeat(80) + "\n";
            sessionEnd += "SESSION ENDED: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            sessionEnd += "\n" + "=".repeat(80) + "\n";
            fileWriter.println(sessionEnd);
            
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Log file closed successfully");
        }
    }
    
    public void setFileLoggingEnabled(boolean enabled) {
        this.fileLoggingEnabled = enabled;
        info("File logging " + (enabled ? "enabled" : "disabled"));
    }
    
    public boolean isFileLoggingEnabled() {
        return fileLoggingEnabled;
    }
    
    public String getLogFilePath() {
        return LOG_DIRECTORY + File.separator + LOG_FILE_NAME;
    }
}
