package com.satellite.command.exception;

public class InvalidOrientationException extends SatelliteException {
    private static final long serialVersionUID = 1L;

    public InvalidOrientationException(String message) {
        super(message);
    }
}