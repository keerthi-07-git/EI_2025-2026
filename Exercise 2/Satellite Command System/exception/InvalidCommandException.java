package com.satellite.command.exception;

public class InvalidCommandException extends SatelliteException {
    private static final long serialVersionUID = 1L;

    public InvalidCommandException(String message) {
        super(message);
    }
}