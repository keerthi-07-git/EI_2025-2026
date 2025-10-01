package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.ErrorHandler;
import com.satellite.command.infrastructure.Logger;

public class CollectDataCommand implements Command {
    private static final Logger logger = Logger.getInstance();
    
    public CollectDataCommand() {
        logger.debug("CollectDataCommand created");
    }
    
    @Override
    public CommandResult execute(SatelliteController controller) {
        logger.debug("Executing collect data command");
        
        try {
            int dataCollected = controller.collectData();
            
            String message;
            if (dataCollected > 0) {
                message = String.format("Successfully collected %d units of data", dataCollected);
                logger.info("Collect data command executed successfully: " + dataCollected + " units");
            } else {
                message = "Cannot collect data: Solar panels are inactive";
                logger.warn("Collect data command failed: solar panels inactive");
            }
            
            return new CommandResult("COLLECT_DATA", message);
            
        } catch (Exception e) {
            logger.error("Collect data command execution failed", e);
            return ErrorHandler.handleException("COLLECT_DATA", e);
        }
    }
    
    @Override
    public String getCommandName() {
        return "COLLECT_DATA";
    }
    
    @Override
    public String getDescription() {
        return "Collect data (only if solar panels are active)";
    }
}       