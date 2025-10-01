package com.satellite.command.model;

public enum PanelStatus {
	ACTIVE("Active"),
    INACTIVE("Inactive");
    
    private final String displayName;
    
    PanelStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public boolean isActive() {
        return this == ACTIVE;
    }

}
