package com.satellite.command.exception;

public class SatelliteException extends Exception {
    private static final long serialVersionUID = 1L;

    public SatelliteException(String message) {
        super(message);
    }
    
    public SatelliteException(String message, Throwable cause) {
        super(message, cause);
    }
}

