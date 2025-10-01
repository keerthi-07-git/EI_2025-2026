package com.satellite.command.service;

import com.satellite.command.infrastructure.Logger;
import com.satellite.command.model.SatelliteStatistics;

import java.time.Duration;

public class StatisticsService {
    private final SatelliteStatistics statistics;
    private static final Logger logger = Logger.getInstance();
    
    public StatisticsService() {
        this.statistics = new SatelliteStatistics();
        logger.info("StatisticsService initialized");
    }
    
    public SatelliteStatistics getStatistics() {
        return statistics;
    }
    
    public void displayStatistics() {
        Duration uptime = statistics.getUptime();

        System.out.println("\n+--------------------------------------------------------+");
        System.out.println("|           SATELLITE STATISTICS REPORT                  |");
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| GENERAL STATISTICS                                     |");
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("| System Uptime: %-39s |%n", formatDuration(uptime));
        System.out.printf("| Total Commands Executed: %-28d |%n", statistics.getTotalCommandsExecuted());
        System.out.printf("| Successful Commands: %-32d |%n", statistics.getSuccessfulCommands());
        System.out.printf("| Failed Commands: %-36d |%n", statistics.getFailedCommands());
        System.out.printf("| Success Rate: %-38.1f%% |%n", statistics.getSuccessRate());
        System.out.printf("| Average Command Execution Time: %-18.2fms |%n", statistics.getAverageExecutionTimeMs());
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| DATA COLLECTION STATISTICS                             |");
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("| Total Data Collected: %-31d units |%n", statistics.getTotalDataCollected());
        System.out.printf("| Data Collection Attempts: %-27d |%n", statistics.getDataCollectionAttempts());
        System.out.printf("| Successful Collections: %-29d |%n", statistics.getSuccessfulDataCollections());
        System.out.printf("| Data Collection Success Rate: %-22.1f%% |%n", statistics.getDataCollectionSuccessRate());
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| SOLAR PANEL STATISTICS                                 |");
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("| Panel Active Time: %-34.1f%% |%n", statistics.getPanelActivePercentage());
        System.out.printf("| Total Active Duration: %-30s |%n",
                formatDuration(Duration.ofMillis(statistics.getCurrentPanelActiveTime())));
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| USAGE PATTERNS                                         |");
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("| Most Used Command: %-34s |%n", statistics.getMostUsedCommand());
        System.out.printf("| Most Used Orientation: %-30s |%n", statistics.getMostUsedOrientation());
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| COMMAND FREQUENCY BREAKDOWN                            |");
        System.out.println("+--------------------------------------------------------+");

        statistics.getCommandFrequency().forEach((command, count) -> {
            String bar = createProgressBar(count, statistics.getTotalCommandsExecuted(), 20);
            System.out.printf("| %-15s %s %3d times |%n", command + ":", bar, count);
        });

        System.out.println("+--------------------------------------------------------+\n");

        logger.info("Statistics displayed to user");
    }
    public void displayCompactStatistics() {
        System.out.println("\n┌─ Quick Statistics ────────────────────────┐");
        System.out.printf("│ Commands: %d | Success Rate: %.1f%%       │%n", 
            statistics.getTotalCommandsExecuted(), 
            statistics.getSuccessRate());
        System.out.printf("│ Data Collected: %d units                  │%n", 
            statistics.getTotalDataCollected());
        System.out.printf("│ Uptime: %-33s │%n", 
            formatDuration(statistics.getUptime()));
        System.out.println("└───────────────────────────────────────────┘\n");
    }
    
    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        if (hours > 0) {
            return String.format("%dh %dm %ds", hours, minutes, seconds);
        } else if (minutes > 0) {
            return String.format("%dm %ds", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }
    
    private String createProgressBar(int value, int total, int barLength) {
        if (total == 0) return "[" + " ".repeat(barLength) + "]";
        
        int filled = (int) ((value * barLength) / (double) total);
        int empty = barLength - filled;
        
        return "[" + "+".repeat(Math.max(0, filled)) + "-".repeat(Math.max(0, empty)) + "]"; //+ represents progress
    }
}

