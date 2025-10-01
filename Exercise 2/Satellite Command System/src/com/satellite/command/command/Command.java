package com.satellite.command.command;

import com.satellite.command.controller.SatelliteController;

public interface Command {
    CommandResult execute(SatelliteController controller);
    String getCommandName();
    String getDescription();
}                            