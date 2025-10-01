package com.satellite.command;

import com.satellite.command.command.Command;
import com.satellite.command.command.CommandResult;
import com.satellite.command.controller.CommandInvoker;
import com.satellite.command.controller.SatelliteController;
import com.satellite.command.infrastructure.Logger;
import com.satellite.command.model.Satellite;
import com.satellite.command.service.CommandParser;
import com.satellite.command.service.StatisticsService;
import com.satellite.command.service.ValidationService;

import java.util.Scanner;

public class SatelliteCommandSystemApplication {
    private final SatelliteController satelliteController;
    private final CommandInvoker commandInvoker;
    private final StatisticsService statisticsService;
    private final CommandParser commandParser;
    private final ValidationService validationService;
    private final Logger logger;
    private final Scanner scanner;
    private boolean isRunning;
    public SatelliteCommandSystemApplication() {
        this.logger = Logger.getInstance();
        this.statisticsService = new StatisticsService(); 
        this.satelliteController = new SatelliteController(new Satellite(), statisticsService);  
        this.commandInvoker = new CommandInvoker(satelliteController, statisticsService);
        this.commandParser = new CommandParser(statisticsService);
        this.validationService = new ValidationService();
        this.scanner = new Scanner(System.in);
        this.isRunning = false;
        
        logger.info("Satellite Command System initialized");
    }
    
    public static void main(String[] args) {
        SatelliteCommandSystemApplication app = new SatelliteCommandSystemApplication();
        app.start();
    }
    
    public void start() {
        isRunning = true;
        logger.info("Starting Satellite Command System");
        
        showWelcomeMessage();
        showCurrentStatus();
        
        while (isRunning) {
            try {
                System.out.print("satellite> ");
                String input = scanner.nextLine();
                
                if (input == null) {
                    logger.info("Received EOF signal, shutting down");
                    break; 
                }
                
                processInput(input.trim());
                
            } catch (Exception e) {
                logger.error("Unexpected error in main loop", e);
                System.err.println("Unexpected error: " + e.getMessage());
                System.out.println("Application will continue running. Type 'help' for assistance.");
            }
        }
        
        shutdown();
    }
    
    private void processInput(String input) {
        if (input.isEmpty()) {
            return;
        }
        
        logger.debug("Processing user input: " + input);
        
        ValidationService.ValidationResult inputValidation = validationService.validateCommandInput(input);
        if (!inputValidation.isValid()) {
            System.err.println("Invalid input: " + inputValidation.getErrorMessage());
            return;
        }
        
        switch (input.toLowerCase()) {
            case "exit":
            case "quit":
                logger.info("User requested application shutdown");
                isRunning = false;
                return;
                
            case "help":
                commandParser.showHelp();
                return;
                
            case "status":
                showCurrentStatus();
                return;
                
            case "history":
                commandInvoker.showCommandHistory();
                return;
                
            case "clear":
            case "clearhistory":
                commandInvoker.clearHistory();
                System.out.println("Command history cleared.");
                return;
        }
        
        try {
            Command command = commandParser.parseCommand(input);
            CommandResult result = commandInvoker.executeCommand(command);
            
            System.out.println(result);
            
            if (result.isSuccess()) {
                System.out.println("Updated Status: " + satelliteController.getCurrentStatus());
            } else {
                logger.warn("Command failed: " + result.getMessage());
            }
            
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid command received: " + input);
            System.err.println("Invalid command: " + e.getMessage());
            System.out.println("Type 'help' for available commands.");
        } catch (Exception e) {
            logger.error("Command processing failed", e);
            System.err.println("Command execution failed: " + e.getMessage());
        }
    }
    
    private void showWelcomeMessage() {
        System.out.println("=========================================");
        System.out.println("   Satellite Command System v2.0");
        System.out.println("   Enhanced with Logging & Validation");
        System.out.println("=========================================");
        System.out.println("Welcome to the Satellite Command System!");
        System.out.println("Type 'help' for available commands.");
        System.out.println("Type 'exit' to quit the application.");
        System.out.println("-----------------------------------------");
    }
    
    private void showCurrentStatus() {
        String status = satelliteController.getCurrentStatus();
        System.out.println("Current Status: " + status);
        logger.debug("Status requested: " + status);
    }
    
    private void shutdown() {
        logger.info("Initiating application shutdown");
        
        System.out.println("\nShutting down Satellite Command System...");
        
        int totalCommands = commandInvoker.getHistorySize();
        System.out.println("Total commands executed: " + totalCommands);
        System.out.println("Final Status: " + satelliteController.getCurrentStatus());
        
        if (totalCommands > 0) {
            System.out.println("Command history available in logs.");
        }
        
        System.out.println("Thank you for using Satellite Command System!");
        
        if (scanner != null) {
            scanner.close();
        }
        
        logger.info("Application shutdown completed");
        
        logger.close();
    }
}
