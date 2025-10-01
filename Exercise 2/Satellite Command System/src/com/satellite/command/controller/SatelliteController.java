package com.satellite.command.controller;

import com.satellite.command.infrastructure.Logger;
import com.satellite.command.infrastructure.RetryMechanism;
import com.satellite.command.model.Orientation;
import com.satellite.command.model.PanelStatus;
import com.satellite.command.model.Satellite;
import com.satellite.command.service.StatisticsService;
import com.satellite.command.service.ValidationService;

public class SatelliteController {
    private final Satellite satellite;
    private final Logger logger;
    private final ValidationService validationService;
	private StatisticsService statisticsService;
    private static final int DATA_COLLECTION_AMOUNT = 10;
    
    public SatelliteController(Satellite satellite, StatisticsService statisticsService) {
        this.satellite = satellite;
        this.statisticsService = statisticsService; 
        this.logger = Logger.getInstance();
        this.validationService = new ValidationService();
        
        logger.info("SatelliteController initialized with satellite: " + satellite.toString());
    }
    
    public void rotate(Orientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }
        
        logger.info("Attempting to rotate satellite to: " + orientation.getDisplayName());
        
        RetryMechanism.executeWithRetry(() -> {
            Orientation oldOrientation = satellite.getOrientation();
            satellite.setOrientation(orientation);
            
            if (oldOrientation != orientation) {
                logger.logStateChange("Satellite Orientation", 
                    oldOrientation.getDisplayName(), 
                    orientation.getDisplayName());
                
                statisticsService.getStatistics().recordOrientationChange(orientation);
            }
            
            return null;
        }, "Satellite Rotation");
        
        logger.info("Satellite rotation completed successfully");
    }
    	public void activateSolarPanels() {
            logger.info("Attempting to activate solar panels");
            
            RetryMechanism.executeWithRetry(() -> {
                PanelStatus oldStatus = satellite.getPanelStatus();
                satellite.setPanelStatus(PanelStatus.ACTIVE);
                
                if (oldStatus != PanelStatus.ACTIVE) {
                    logger.logStateChange("Solar Panels", 
                        oldStatus.getDisplayName(), 
                        PanelStatus.ACTIVE.getDisplayName());
                    
                    statisticsService.getStatistics().recordPanelActivation();
                } else {
                    logger.info("Solar panels were already active");
                }
                
                return null;
            }, "Solar Panel Activation");
            
            logger.info("Solar panels activation completed successfully");
        }
    	public void deactivateSolarPanels() {
            logger.info("Attempting to deactivate solar panels");
            
            RetryMechanism.executeWithRetry(() -> {
                PanelStatus oldStatus = satellite.getPanelStatus();
                satellite.setPanelStatus(PanelStatus.INACTIVE);
                
                if (oldStatus != PanelStatus.INACTIVE) {
                    logger.logStateChange("Solar Panels", 
                        oldStatus.getDisplayName(), 
                        PanelStatus.INACTIVE.getDisplayName());
                    
                    statisticsService.getStatistics().recordPanelDeactivation();
                } else {
                    logger.info("Solar panels were already inactive");
                }
                
                return null;
            }, "Solar Panel Deactivation");
            
            logger.info("Solar panels deactivation completed successfully");
        }
    
    	public int collectData() {
            logger.info("Attempting to collect data");
            
            ValidationService.ValidationResult validation = 
                validationService.validateDataCollection(satellite.getPanelStatus());
            
            if (!validation.isValid()) {
                logger.warn("Data collection validation failed: " + validation.getErrorMessage());
                
                statisticsService.getStatistics().recordDataCollection(0, false);
                return 0;
            }
            
            return RetryMechanism.executeWithRetry(() -> {
                int oldDataAmount = satellite.getDataCollected();
                satellite.addData(DATA_COLLECTION_AMOUNT);
                int newDataAmount = satellite.getDataCollected();
                
                logger.logStateChange("Data Collected", 
                    String.valueOf(oldDataAmount), 
                    String.valueOf(newDataAmount));
                
                logger.info(String.format("Data collection successful: +%d units (total: %d)", 
                    DATA_COLLECTION_AMOUNT, newDataAmount));
                
                statisticsService.getStatistics().recordDataCollection(DATA_COLLECTION_AMOUNT, true);
                
                return DATA_COLLECTION_AMOUNT;
            }, "Data Collection");
        }
    
    public Satellite getSatellite() {
        return satellite;
    }
    
    public String getCurrentStatus() {
        return satellite.toString();
    }
    
    public String getDetailedStatus() {
        return String.format("Detailed Status: %s | Last Updated: %s", 
            satellite.toString(), 
            satellite.getLastStateChange().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    
    public boolean performHealthCheck() {
        logger.info("Performing satellite health check");
        
        try {
            boolean orientationOk = satellite.getOrientation() != null;
            boolean panelStatusOk = satellite.getPanelStatus() != null;
            boolean dataIntegrityOk = satellite.getDataCollected() >= 0;
            
            boolean healthy = orientationOk && panelStatusOk && dataIntegrityOk;
            
            if (healthy) {
                logger.info("Satellite health check: PASSED");
            } else {
                logger.warn("Satellite health check: FAILED - " +
                    "Orientation: " + orientationOk +
                    ", Panel Status: " + panelStatusOk +
                    ", Data Integrity: " + dataIntegrityOk);
            }
            
            return healthy;
            
        } catch (Exception e) {
            logger.error("Health check failed with exception", e);
            return false;
        }
    }
    
    public void resetToInitialState() {
        logger.info("Resetting satellite to initial state");
        
        RetryMechanism.executeWithRetry(() -> {
            String oldState = satellite.toString();
            
            satellite.setOrientation(Orientation.NORTH);
            satellite.setPanelStatus(PanelStatus.INACTIVE);
            satellite.resetData(); 
            
            String newState = satellite.toString();
            
            logger.logStateChange("Satellite (Full Reset)", oldState, newState);
            logger.info("Satellite reset completed successfully");
            
            return null;
        }, "Satellite Reset");
    }
}