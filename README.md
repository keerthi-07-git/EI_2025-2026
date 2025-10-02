# EI_2025-2026 Coding exercises

## Exercise 1 
1.Two use cases to demonstrate two behavioural design pattern.
2.Two use cases to demonstrate two creational design pattern. 
3.Two use cases to demonstrate two structural design pattern.


## Exercise 2
Chosen Problem Statement:

**6. Satellite Command System Programming Exercise**
#### Problem Statement
You are tasked with developing a Satellite Command System that simulates controlling a satellitein orbit. The satellite starts in a default initial state and can accept a series of commands tochange its orientation, solar panel status, and data collection.

#### Functional Requirements
1.Initialize the Satellite: Create a class or function that initializes the satellite's attributes totheir initial state.

2.Rotate: Implement a command called 'rotate' that takes a direction parameter (North, South,East, West) and sets the satellite's orientation accordingly.
Example:
rotate(North)
would set the orientation to "North".

3.Activate/Deactivate Solar Panels: Implement commands called 'activatePanels' and'deactivatePanels' to control the solar panels' status.
Example:
activatePanels()
would set the solar panels to "Active".

4.Collect Data: Implement a command called 'collectData' that increments the 'Data Collected'attribute by 10 units, but only if the solar panels are "Active".
Example:
collectData()
would set the data collected to 10 if the solar panels are"Active".

#### Initial State
Your satellite begins with the following attributes:
Orientation: "North"
Solar Panels: "Inactive"
Data Collected: 0
#### Commands to be executed
The series of commands should be executed in a sequential manner over the initial state,altering the satellite's state accordingly. You could execute them through function calls, orsimulate a command-line interface where these commands can be entered.
For example:
rotate(South)
activatePanels()
collectData()
After these commands, the satellite's state would be:
Orientation: "South"
Solar Panels: "Active"
Data Collected: 10
#### Evaluation Criteria
1.Code Quality: Utilization of best practices, SOLID principles, and design patterns is highlyencouraged.

2.Functionality: Does your implementation correctly execute the commands and maintain thesatellite's state?

3.Global Convention: Your code should be easily understandable and maintainable.

4.Gold Standards: Logging, exception-handling, and transient error-handling mechanismsshould be implemented.

5.Code Walkthrough: Be prepared to explain your decisions, design patterns used, and theoverall architecture during a code review.

The intent behind the 'Satellite Command System' coding exercise is to evaluate a candidate'sability to design and implement a system that manages state transitions and dependenciesbetween various components... The challenge provides a realistic yet simplified simulation ofwhat engineers might encounter in fields like aerospace technology or complex systemmanagement.
This question not only assesses the technical skills and coding abilities of the candidates butalso their understanding of design patterns, state management, and system design principles.The task requires them to employ SOLID principles and various design patterns, which arecrucial for writing maintainable, scalable, and robust software. It also allows candidates todemonstrate their expertise in command execution, error handling, and logging—all of which arehighly relevant in real-world applications...

