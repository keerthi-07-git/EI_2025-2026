# Satellite Command System - Class Diagram Documentation

## Overview
This documentation presents the **Satellite Command System architecture** using 5 interconnected diagrams that show how different components work together.  

---

## Section 1: Command Pattern Core
**What it shows:** The heart of the command system – how user actions become executable commands.

**Main Components:**
- `Command` Interface – Blueprint for all commands
- 6 Command Classes: `RotateCommand`, `ActivatePanelsCommand`, `DeactivatePanelsCommand`, `CollectDataCommand`, `ResetCommand`, `StatisticsCommand`

**Key Relationships:**
- All commands **implement** the `Command` interface (`- - -△`)
- All commands operate on `SatelliteController` (`──>`)

**Why it matters:**  
This design allows adding new commands easily without changing existing code. Each command is independent and reusable.

---

## Section 2: Controller Layer
**What it shows:** How the central controller manages the satellite and coordinates with services.

**Main Component:**
- `SatelliteController` – The central brain that controls everything

**Key Relationships:**
- Owns and manages `Satellite` (**composition - ◆──>**)  
- Uses `StatisticsService` to track operations (`──>`)  
- Uses `ValidationService` to check commands before execution (`──>`)

**Why it matters:**  
The controller keeps satellite operations organized and ensures only valid commands are executed.

---

## Section 3: Model Layer
**What it shows:** The satellite itself and its possible states.

**Main Components:**
- `Satellite` – The satellite object with orientation, panel status, and data  
- `Orientation` Enum – 8 directions (North, South, East, West, NE, NW, SE, SW)  
- `PanelStatus` Enum – Active or Inactive  

**Key Relationships:**
- `Satellite` uses `Orientation` to track direction  
- `Satellite` uses `PanelStatus` to track solar panel state  

**Why it matters:**  
Represents the core data model and current satellite state.

---

## Section 4: Command Orchestration
**What it shows:** How user input becomes executable commands.

**Main Components:**
- `CommandParser` – Converts text input into `Command` objects  
- `CommandInvoker` – Executes commands and maintains history  

**Key Relationships:**
- `CommandParser` creates `Command` objects from user input  
- `CommandInvoker` executes `Command` objects and tracks them  
- Both use `StatisticsService` to record metrics  

**Why it matters:**  
Shows the complete flow from "user types command" → "command executed and logged."

---

## Section 5: Statistics Subsystem
**What it shows:** How the system tracks performance and usage metrics.

**Main Components:**
- `StatisticsService` – Manages statistics operations  
- `SatelliteStatistics` – Stores metrics (success rate, command frequency, orientation history)

**Key Relationships:**
- `StatisticsService` owns `SatelliteStatistics` (**composition - ◆──>**)  
- `SatelliteStatistics` tracks orientation usage patterns  

**Why it matters:**  
Provides insights into system usage, success rates, and most-used commands.

---

## Understanding Arrow Notation
- Dashed line with triangle (`- - -△`) = implements interface  
- Solid line with diamond (`◆──>`) = owns/manages (strong relationship)  
- Solid line with arrow (`──>`) = uses/depends on  
- Dashed line with arrow (`- - ->`) = temporary use  

---

## Complete System Flow
