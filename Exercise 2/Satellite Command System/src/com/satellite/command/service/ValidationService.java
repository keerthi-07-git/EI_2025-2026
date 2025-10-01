package com.satellite.command.service;

import com.satellite.command.infrastructure.Logger;
import com.satellite.command.model.Orientation;
import com.satellite.command.model.PanelStatus;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {
    private static final Logger logger = Logger.getInstance();
    
    public static class ValidationResult {
        private final boolean valid;
        private final List<String> errors;
        
        public ValidationResult(boolean valid, List<String> errors) {
            this.valid = valid;
            this.errors = new ArrayList<>(errors);
        }
        
        public boolean isValid() { return valid; }
        public List<String> getErrors() { return new ArrayList<>(errors); }
        public String getErrorMessage() { return String.join("; ", errors); }
    }
    
    public ValidationResult validateRotateCommand(String orientationStr) {
        List<String> errors = new ArrayList<>();
        
        if (orientationStr == null || orientationStr.trim().isEmpty()) {
            errors.add("Orientation parameter is required");
        } else {
            try {
                Orientation.fromString(orientationStr);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        
        boolean valid = errors.isEmpty();
        if (!valid) {
            logger.warn("Validation failed for rotate command: " + String.join("; ", errors));
        }
        
        return new ValidationResult(valid, errors);
    }
    
    public ValidationResult validateDataCollection(PanelStatus panelStatus) {
        List<String> errors = new ArrayList<>();
        
        if (panelStatus == null) {
            errors.add("Panel status cannot be null");
        } else if (!panelStatus.isActive()) {
            errors.add("Cannot collect data: Solar panels must be active");
        }
        
        boolean valid = errors.isEmpty();
        if (!valid) {
            logger.warn("Validation failed for data collection: " + String.join("; ", errors));
        }
        
        return new ValidationResult(valid, errors);
    }
    
    public ValidationResult validateCommandInput(String input) {
        List<String> errors = new ArrayList<>();
        
        if (input == null) {
            errors.add("Command cannot be null");
        } else if (input.trim().isEmpty()) {
            errors.add("Command cannot be empty");
        } else if (input.length() > 100) {
            errors.add("Command too long (maximum 100 characters)");
        } else if (!input.matches("^[a-zA-Z0-9\\s\\-]+$")) { 
            errors.add("Command contains invalid characters");
        }
        
        boolean valid = errors.isEmpty();
        if (!valid) {
            logger.warn("Input validation failed: " + String.join("; ", errors));
        }
        
        return new ValidationResult(valid, errors);
    }}
