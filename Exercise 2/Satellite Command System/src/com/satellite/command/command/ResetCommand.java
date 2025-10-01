package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.ErrorHandler;
import com.satellite.command.infrastructure.Logger;

import java.util.Scanner;

public class ResetCommand implements Command {
    private static final Logger logger = Logger.getInstance();
    private final Scanner scanner;
    
    public ResetCommand(Scanner scanner) {
        this.scanner = scanner;
        logger.debug("ResetCommand created");
    }
    
    @Override
    public CommandResult execute(SatelliteController controller) {
        logger.info("Reset command initiated - requesting user confirmation");
        
        try {
            String currentState = controller.getCurrentStatus();
            System.out.println("\n⚠️  WARNING: Reset Operation");
            System.out.println("Current State: " + currentState);
            System.out.println("This will reset the satellite to initial state:");
            System.out.println("  - Orientation: North");
            System.out.println("  - Solar Panels: Inactive");
            System.out.println("  - Data Collected: 0");
            System.out.println();
            System.out.print("Do you want to continue? (y/n): ");
            
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                logger.info("User confirmed reset operation");
                
                controller.resetToInitialState();
                
                logger.info("Satellite reset to initial state successfully");
                return new CommandResult("RESET", 
                    "Satellite has been reset to initial state successfully");
            } else {
                logger.info("User cancelled reset operation");
                return new CommandResult("RESET", "Reset operation cancelled by user");
            }
            
        } catch (Exception e) {
            logger.error("Reset command execution failed", e);
            return ErrorHandler.handleException("RESET", e);
        }
    }
    
    @Override
    public String getCommandName() {
        return "RESET";
    }
    
    @Override
    public String getDescription() {
        return "Reset satellite to initial state (requires confirmation)";
    }
}