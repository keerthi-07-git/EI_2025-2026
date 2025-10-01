package com.satellite.command.controller;

import com.satellite.command.command.Command;
import com.satellite.command.command.CommandResult;
import com.satellite.command.infrastructure.Logger;
import com.satellite.command.service.StatisticsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandInvoker {
    private final SatelliteController satelliteController;
    private final StatisticsService statisticsService;

    private final List<CommandHistoryEntry> commandHistory;
    private final Logger logger;
    
    
    public CommandInvoker(SatelliteController satelliteController, StatisticsService statisticsService) {
        this.satelliteController = satelliteController;
        this.statisticsService = statisticsService;
        this.commandHistory = new ArrayList<>();
        this.logger = Logger.getInstance();
    }
    
    public CommandResult executeCommand(Command command) {
        long startTime = System.currentTimeMillis();
        String previousState = satelliteController.getCurrentStatus();
        
        logger.info("Executing command: " + command.getCommandName());
        logger.debug("Pre-execution state: " + previousState);
        
        try {
            CommandResult result = command.execute(satelliteController);
            
            long executionTime = System.currentTimeMillis() - startTime;
            String currentState = satelliteController.getCurrentStatus();
            
            logger.logCommandExecution(command.getCommandName(), result.isSuccess(), executionTime);
            
            if (!previousState.equals(currentState)) {
                logger.logStateChange("Satellite", previousState, currentState);
            }
            
            statisticsService.getStatistics().recordCommandExecution(
                command.getCommandName(), result.isSuccess(), executionTime);
            
            CommandHistoryEntry historyEntry = new CommandHistoryEntry(
                command, result, previousState, currentState, executionTime);
            commandHistory.add(historyEntry);
            
            logger.debug("Post-execution state: " + currentState);
            
            return result;
            
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.error("Command execution failed", e);
            logger.logCommandExecution(command.getCommandName(), false, executionTime);
            
            statisticsService.getStatistics().recordCommandExecution(
                command.getCommandName(), false, executionTime);
            
            CommandResult errorResult = new CommandResult(command.getCommandName(), 
                "Command execution failed: " + e.getMessage(), e);
            
            CommandHistoryEntry historyEntry = new CommandHistoryEntry(
                command, errorResult, previousState, previousState, executionTime);
            commandHistory.add(historyEntry);
            
            return errorResult;
        }
    }
    
    public List<CommandHistoryEntry> getCommandHistory() {
        return Collections.unmodifiableList(commandHistory);
    }
    
    public void clearHistory() {
        logger.info("Clearing command history (" + commandHistory.size() + " entries)");
        commandHistory.clear();
    }
    
    public int getHistorySize() {
        return commandHistory.size();
    }
    
    public void showCommandHistory() {
        if (commandHistory.isEmpty()) {
            System.out.println("No commands executed yet.");
            return;
        }
        
        System.out.println("\n=== Command History ===");
        for (int i = 0; i < commandHistory.size(); i++) {
            CommandHistoryEntry entry = commandHistory.get(i);
            System.out.printf("%d. [%s] %s - %s (%dms)\n", 
                i + 1,
                entry.getResult().getExecutionTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")),
                entry.getCommand().getCommandName(),
                entry.getResult().isSuccess() ? "SUCCESS" : "FAILED",
                entry.getExecutionTimeMs());
        }
        System.out.println("========================\n");
    }
    
    public static class CommandHistoryEntry {
        private final Command command;
        private final CommandResult result;
        private final String previousState;
        private final String currentState;
        private final long executionTimeMs;
        private final LocalDateTime timestamp;
        
        public CommandHistoryEntry(Command command, CommandResult result, 
                                 String previousState, String currentState, long executionTimeMs) {
            this.command = command;
            this.result = result;
            this.previousState = previousState;
            this.currentState = currentState;
            this.executionTimeMs = executionTimeMs;
            this.timestamp = LocalDateTime.now();
        }
        
        public Command getCommand() { return command; }
        public CommandResult getResult() { return result; }
        public String getPreviousState() { return previousState; }
        public String getCurrentState() { return currentState; }
        public long getExecutionTimeMs() { return executionTimeMs; }
        public LocalDateTime getTimestamp() { return timestamp; }
        
        public boolean hasStateChanged() {
            return !previousState.equals(currentState);
        }
    }
}