package com.satellite.command.model;

public enum Orientation {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West"),
    
    NORTH_EAST("North-East"),
    NORTH_WEST("North-West"),
    SOUTH_EAST("South-East"),
    SOUTH_WEST("South-West");
    
    private final String displayName;
    
    Orientation(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static Orientation fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Orientation cannot be null or empty");
        }
        
        String normalized = input.trim().toUpperCase().replace("-", "_").replace(" ", "_");
        
        for (Orientation orientation : values()) {
            String enumName = orientation.name();
            String displayUpper = orientation.displayName.toUpperCase().replace("-", "_").replace(" ", "_");
            
            if (enumName.equals(normalized) || displayUpper.equals(normalized)) {
                return orientation;
            }
        }
        
        throw new IllegalArgumentException("Invalid orientation: " + input + 
            ". Valid options: North, South, East, West, North-East, North-West, South-East, South-West");
    }
    
    public boolean isCardinal() {
        return this == NORTH || this == SOUTH || this == EAST || this == WEST;
    }
    
    public boolean isOrdinal() {
        return !isCardinal();
    }
}