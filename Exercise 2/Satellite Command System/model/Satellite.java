package com.satellite.command.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Satellite {
    private Orientation orientation;
    private PanelStatus panelStatus;
    private int dataCollected;
    private LocalDateTime lastStateChange;
    
    public Satellite() {
        this.orientation = Orientation.NORTH;
        this.panelStatus = PanelStatus.INACTIVE;
        this.dataCollected = 0;
        this.lastStateChange = LocalDateTime.now();
    }
    
    public Orientation getOrientation() {
        return orientation;
    }
    
    public PanelStatus getPanelStatus() {
        return panelStatus;
    }
    
    public int getDataCollected() {
        return dataCollected;
    }
    
    public LocalDateTime getLastStateChange() {
        return lastStateChange;
    }
    
    public void setOrientation(Orientation orientation) {
        Objects.requireNonNull(orientation, "Orientation cannot be null");
        this.orientation = orientation;
        this.lastStateChange = LocalDateTime.now();
    }
    
    public void setPanelStatus(PanelStatus panelStatus) {
        Objects.requireNonNull(panelStatus, "Panel status cannot be null");
        this.panelStatus = panelStatus;
        this.lastStateChange = LocalDateTime.now();
    }
    
    public void addData(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Data amount cannot be negative");
        }
        this.dataCollected += amount;
        this.lastStateChange = LocalDateTime.now();
    }
    
    public boolean canCollectData() {
        return panelStatus.isActive();
    }
    
    @Override
    public String toString() {
        return String.format("Satellite State - Orientation: %s, Solar Panels: %s, Data Collected: %d", 
            orientation.getDisplayName(), 
            panelStatus.getDisplayName(), 
            dataCollected);
    }
    public void resetData() {
        this.dataCollected = 0;
        this.lastStateChange = LocalDateTime.now();
    }
    
}