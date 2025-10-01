package com.satellite.command.service;

import com.satellite.command.command.*;
import com.satellite.command.infrastructure.Logger;
import com.satellite.command.model.Orientation;

public class CommandParser {
    private static final Logger logger = Logger.getInstance();
    private final ValidationService validationService;
    private final StatisticsService statisticsService;  

    
    public CommandParser(StatisticsService statisticsService) {
        this.validationService = new ValidationService();
        this.statisticsService = statisticsService;  
    }
    
    private Command parseRotateCommand(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("Rotate command requires direction (e.g., North, South-East, North West)");
        }
        
        StringBuilder directionBuilder = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            if (i > 1) {
                directionBuilder.append("-"); 
            }
            directionBuilder.append(parts[i]);
        }
        
        String orientationStr = directionBuilder.toString();
        
        ValidationService.ValidationResult validation = validationService.validateRotateCommand(orientationStr);
        if (!validation.isValid()) {
            throw new IllegalArgumentException(validation.getErrorMessage());
        }
        
        try {
            Orientation orientation = Orientation.fromString(orientationStr);
            logger.debug("Parsed rotate command with orientation: " + orientation);
            return new RotateCommand(orientation);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid orientation in rotate command: " + orientationStr);
            throw e;
        }
    }

    public Command parseCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        
        logger.debug("Parsing command: " + input);
        
        String[] parts = input.trim().toLowerCase().split("\\s+");
        String commandName = parts[0];
        
        switch (commandName) {
            case "rotate":
                return parseRotateCommand(parts);
                
            case "activatepanels":
            case "activate":
                logger.debug("Parsed activate panels command");
                return new ActivatePanelsCommand();
                
            case "deactivatepanels":
            case "deactivate":
                logger.debug("Parsed deactivate panels command");
                return new DeactivatePanelsCommand();
                
            case "collectdata":
            case "collect":
                logger.debug("Parsed collect data command");
                return new CollectDataCommand();
            
            case "reset":
                logger.debug("Parsed reset command");
                return new ResetCommand(new java.util.Scanner(System.in));  
            case "stats":
            case "statistics":
                logger.debug("Parsed statistics command");
                return new StatisticsCommand(statisticsService, false);
            
            case "quickstats":
            case "qs":
                logger.debug("Parsed quick statistics command");
                return new StatisticsCommand(statisticsService, true);
            default:
                String errorMsg = "Unknown command: " + commandName + 
                    ". Available commands: rotate, activate, deactivate, collect, reset, audioon, audiooff";
                logger.warn("Unknown command attempted: " + commandName);
                throw new IllegalArgumentException(errorMsg);
        }
    }
    
    public void showHelp() {
        System.out.println("\n=== Satellite Command System - Available Commands ===");
        System.out.println("SATELLITE OPERATIONS:");
        System.out.println("  rotate <direction>     - Rotate satellite (North/South/East/West)");
        System.out.println("  activate               - Activate solar panels");
        System.out.println("  deactivate             - Deactivate solar panels");
        System.out.println("  reset                  - Reset satellite to initial state (requires confirmation)");
        System.out.println("  collect                - Collect data (requires active panels)");
        System.out.println();
        System.out.println("SYSTEM COMMANDS:");
        System.out.println("  status                 - Show current satellite status");
        System.out.println("  history                - Show command execution history");
        System.out.println("  clear                  - Clear command history");
        System.out.println("  help                   - Show this help message");
        System.out.println("  exit                   - Exit the application");
        System.out.println("=====================================================\n");
        System.out.println("ANALYTICS:");
        System.out.println("  stats                  - Show detailed statistics and analytics");
        System.out.println("  quickstats (qs)        - Show compact statistics summary");

        
        logger.debug("Help displayed to user");
    }
}
