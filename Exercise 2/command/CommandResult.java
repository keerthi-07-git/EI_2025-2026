package com.satellite.command.command;

import java.time.LocalDateTime;

public class CommandResult {
    private final boolean success;
    private final String message;
    private final String commandName;
    private final LocalDateTime executionTime;
    private final Exception error;
    
    // Success constructor
    public CommandResult(String commandName, String message) {
        this.success = true;
        this.commandName = commandName;
        this.message = message;
        this.executionTime = LocalDateTime.now();
        this.error = null;
    }
    
    // Error constructor
    public CommandResult(String commandName, String message, Exception error) {
        this.success = false;
        this.commandName = commandName;
        this.message = message;
        this.executionTime = LocalDateTime.now();
        this.error = error;
    }
    
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getCommandName() { return commandName; }
    public LocalDateTime getExecutionTime() { return executionTime; }
    public Exception getError() { return error; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s: %s", 
            success ? "SUCCESS" : "ERROR", 
            commandName, 
            message);
    }
}