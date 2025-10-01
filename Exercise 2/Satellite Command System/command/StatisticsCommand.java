package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.ErrorHandler;
import com.satellite.command.infrastructure.Logger;
import com.satellite.command.service.StatisticsService;

public class StatisticsCommand implements Command {
    private static final Logger logger = Logger.getInstance();
    private final StatisticsService statisticsService;
    private final boolean compact;
    
    public StatisticsCommand(StatisticsService statisticsService, boolean compact) {
        this.statisticsService = statisticsService;
        this.compact = compact;
        logger.debug("StatisticsCommand created (compact: " + compact + ")");
    }
    
    @Override
    public CommandResult execute(SatelliteController controller) {
        logger.info("Executing statistics command");
        
        try {
            if (compact) {
                statisticsService.displayCompactStatistics();
            } else {
                statisticsService.displayStatistics();
            }
            
            return new CommandResult("STATISTICS", "Statistics displayed successfully");
            
        } catch (Exception e) {
            logger.error("Statistics command execution failed", e);
            return ErrorHandler.handleException("STATISTICS", e);
        }
    }
    
    @Override
    public String getCommandName() {
        return "STATISTICS";
    }
    
    @Override
    public String getDescription() {
        return "Display satellite statistics and analytics";
    }
}