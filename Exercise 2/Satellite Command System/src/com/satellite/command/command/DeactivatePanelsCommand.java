package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.ErrorHandler;
import com.satellite.command.infrastructure.Logger;

public class DeactivatePanelsCommand implements Command {
    private static final Logger logger = Logger.getInstance();
    
    public DeactivatePanelsCommand() {
        logger.debug("DeactivatePanelsCommand created");
    }
    
    @Override
    public CommandResult execute(SatelliteController controller) {
        logger.debug("Executing deactivate panels command");
        
        try {
            controller.deactivateSolarPanels();
            
            String message = "Solar panels deactivated successfully";
            logger.info("Deactivate panels command executed successfully");
            
            return new CommandResult("DEACTIVATE_PANELS", message);
            
        } catch (Exception e) {
            logger.error("Deactivate panels command execution failed", e);
            return ErrorHandler.handleException("DEACTIVATE_PANELS", e);
        }
    }
    
    @Override
    public String getCommandName() {
        return "DEACTIVATE_PANELS";
    }
    
    @Override
    public String getDescription() {
        return "Deactivate satellite solar panels";
    }
}