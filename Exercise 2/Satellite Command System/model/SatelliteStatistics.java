package com.satellite.command.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SatelliteStatistics {
    private final LocalDateTime startTime;
    private int totalCommandsExecuted;
    private int successfulCommands;
    private int failedCommands;
    private long totalExecutionTimeMs;
    private int totalDataCollected;
    private int dataCollectionAttempts;
    private int successfulDataCollections;
    private long totalPanelActiveTimeMs;
    private LocalDateTime panelActivatedAt;
    private final Map<String, Integer> commandFrequency;
    private final Map<Orientation, Integer> orientationFrequency;
    
    public SatelliteStatistics() {
        this.startTime = LocalDateTime.now();
        this.totalCommandsExecuted = 0;
        this.successfulCommands = 0;
        this.failedCommands = 0;
        this.totalExecutionTimeMs = 0;
        this.totalDataCollected = 0;
        this.dataCollectionAttempts = 0;
        this.successfulDataCollections = 0;
        this.totalPanelActiveTimeMs = 0;
        this.panelActivatedAt = null;
        this.commandFrequency = new HashMap<>();
        this.orientationFrequency = new HashMap<>();
    }
    
    public void recordCommandExecution(String commandName, boolean success, long executionTimeMs) {
        totalCommandsExecuted++;
        if (success) {
            successfulCommands++;
        } else {
            failedCommands++;
        }
        totalExecutionTimeMs += executionTimeMs;
        
        commandFrequency.put(commandName, commandFrequency.getOrDefault(commandName, 0) + 1);
    }
    
    public void recordOrientationChange(Orientation orientation) {
        orientationFrequency.put(orientation, orientationFrequency.getOrDefault(orientation, 0) + 1);
    }
    
    public void recordDataCollection(int amount, boolean success) {
        dataCollectionAttempts++;
        if (success && amount > 0) {
            successfulDataCollections++;
            totalDataCollected += amount;
        }
    }
    
    public void recordPanelActivation() {
        if (panelActivatedAt == null) {
            panelActivatedAt = LocalDateTime.now();
        }
    }
    
    public void recordPanelDeactivation() {
        if (panelActivatedAt != null) {
            long activeTime = Duration.between(panelActivatedAt, LocalDateTime.now()).toMillis();
            totalPanelActiveTimeMs += activeTime;
            panelActivatedAt = null;
        }
    }
    
    public long getCurrentPanelActiveTime() {
        if (panelActivatedAt != null) {
            return totalPanelActiveTimeMs + Duration.between(panelActivatedAt, LocalDateTime.now()).toMillis();
        }
        return totalPanelActiveTimeMs;
    }
    
    public LocalDateTime getStartTime() { return startTime; }
    public int getTotalCommandsExecuted() { return totalCommandsExecuted; }
    public int getSuccessfulCommands() { return successfulCommands; }
    public int getFailedCommands() { return failedCommands; }
    public long getTotalExecutionTimeMs() { return totalExecutionTimeMs; }
    public int getTotalDataCollected() { return totalDataCollected; }
    public int getDataCollectionAttempts() { return dataCollectionAttempts; }
    public int getSuccessfulDataCollections() { return successfulDataCollections; }
    public Map<String, Integer> getCommandFrequency() { return new HashMap<>(commandFrequency); }
    public Map<Orientation, Integer> getOrientationFrequency() { return new HashMap<>(orientationFrequency); }
    
    public double getSuccessRate() {
        if (totalCommandsExecuted == 0) return 0.0;
        return (successfulCommands * 100.0) / totalCommandsExecuted;
    }
    
    public double getAverageExecutionTimeMs() {
        if (totalCommandsExecuted == 0) return 0.0;
        return (double) totalExecutionTimeMs / totalCommandsExecuted;
    }
    
    public double getDataCollectionSuccessRate() {
        if (dataCollectionAttempts == 0) return 0.0;
        return (successfulDataCollections * 100.0) / dataCollectionAttempts;
    }
    
    public Duration getUptime() {
        return Duration.between(startTime, LocalDateTime.now());
    }
    
    public double getPanelActivePercentage() {
        long totalUptime = getUptime().toMillis();
        if (totalUptime == 0) return 0.0;
        long currentActiveTime = getCurrentPanelActiveTime();
        return (currentActiveTime * 100.0) / totalUptime;
    }
    
    public String getMostUsedCommand() {
        if (commandFrequency.isEmpty()) return "None";
        return commandFrequency.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("None");
    }
    
    public String getMostUsedOrientation() {
        if (orientationFrequency.isEmpty()) return "None";
        return orientationFrequency.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> entry.getKey().getDisplayName() + " (" + entry.getValue() + " times)")
            .orElse("None");
    }
}