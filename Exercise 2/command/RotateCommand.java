package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.ErrorHandler;
import com.satellite.command.infrastructure.Logger;
import com.satellite.command.model.Orientation;

public class RotateCommand implements Command {
    private final Orientation orientation;
    private static final Logger logger = Logger.getInstance();
    
    public RotateCommand(Orientation orientation) {
        this.orientation = orientation;
        logger.debug("RotateCommand created with orientation: " + orientation);
    }
    
    @Override
    public CommandResult execute(SatelliteController controller) {
        logger.debug("Executing rotate command to: " + orientation.getDisplayName());       
        
        try {
            String previousState = controller.getCurrentStatus();
            controller.rotate(orientation);
            String currentState = controller.getCurrentStatus();
            
            String message = String.format("Satellite rotated to %s", orientation.getDisplayName());
            logger.info("Rotate command executed successfully: " + message);
            
            return new CommandResult("ROTATE", message);
            
        } catch (Exception e) {
            logger.error("Rotate command execution failed", e);
            return ErrorHandler.handleException("ROTATE", e);
        }
    }
    
    @Override
    public String getCommandName() {
        return "ROTATE";
    }
    
    @Override
    public String getDescription() {
        return "Rotate satellite to specified orientation: " + orientation.getDisplayName();
    }
}