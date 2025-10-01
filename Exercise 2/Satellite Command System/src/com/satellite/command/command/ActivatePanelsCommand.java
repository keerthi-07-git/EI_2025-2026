package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.ErrorHandler;
import com.satellite.command.infrastructure.Logger;

public class ActivatePanelsCommand implements Command {
    private static final Logger logger = Logger.getInstance();
    
    public ActivatePanelsCommand() {
        logger.debug("ActivatePanelsCommand created");
    }
    
    @Override
    public CommandResult execute(SatelliteController controller) {
        logger.debug("Executing activate panels command");
        
        try {
            controller.activateSolarPanels();
            
            String message = "Solar panels activated successfully";
            logger.info("Activate panels command executed successfully");
            
            return new CommandResult("ACTIVATE_PANELS", message);
            
        } catch (Exception e) {
            logger.error("Activate panels command execution failed", e);
            return ErrorHandler.handleException("ACTIVATE_PANELS", e);
        }
    }
    
    @Override
    public String getCommandName() {
        return "ACTIVATE_PANELS";
    }
    
    @Override
    public String getDescription() {
        return "Activate satellite solar panels";
    }
}