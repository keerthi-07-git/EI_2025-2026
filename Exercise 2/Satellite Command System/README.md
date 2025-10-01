Satellite Command System - User Installation Guide
==================================================

Prerequisites
-------------
Required:
- Java Development Kit (JDK) 11 or higher  
  Check version: java -version  
  Download: https://www.oracle.com/java/technologies/downloads/

Optional (choose one):
- Eclipse IDE (recommended for easy running)  
  Download: https://www.eclipse.org/downloads/
- Maven (for command-line build)  
  Check version: mvn -version  
  Download: https://maven.apache.org/download.cgi
- Git (for cloning the repository)  
  Check version: git --version  
  Download: https://git-scm.com/downloads/

Method 1: Download from GitHub (no Git required)
------------------------------------------------
Step 1: Download the Project
1. Go to the GitHub repository: https://github.com/yourusername/your-repo-name
2. Click the green "Code" button
3. Click "Download ZIP"
4. Extract the ZIP file to a location such as C:\Projects or ~/Projects

Step 2: Navigate to the Project
Windows:
    cd C:\Projects\your-repo-name-main\ex2\satellitecmdsys  
Mac/Linux:
    cd ~/Projects/your-repo-name-main/ex2/satellitecmdsys  

Step 3: Run the Application
Option A - Using Eclipse:  
1. Open Eclipse  
2. File → Import → Existing Projects into Workspace  
3. Browse to: your-repo-name-main/ex2/satellitecmdsys  
4. Finish  
5. Navigate to src/main/java/com/satellite/command/SatelliteCommandSystemApplication.java  
6. Right-click → Run As → Java Application  

Option B - Using Command Line:  
    javac -d bin -sourcepath src/main/java src/main/java/com/satellite/command/**/*.java  
    java -cp bin com.satellite.command.SatelliteCommandSystemApplication  

Option C - Using Maven (if pom.xml exists):  
    mvn clean compile  
    mvn exec:java -Dexec.mainClass="com.satellite.command.SatelliteCommandSystemApplication"  

Method 2: Clone from GitHub
---------------------------
Step 1: Clone the Repository  
    cd C:\Projects        (Windows)  
    cd ~/Projects         (Mac/Linux)  
    git clone https://github.com/yourusername/your-repo-name.git  
    cd your-repo-name/ex2/satellitecmdsys  

Step 2: Open in Eclipse  
1. Open Eclipse  
2. File → Import → Git → Projects from Git → Existing local repository  
3. Browse to the cloned repository  
4. Select the project and click Finish  

Step 3: Run the Application  
Open src/main/java → com.satellite.command → SatelliteCommandSystemApplication.java  
Right-click → Run As → Java Application  

Using the Application
---------------------
When the application starts, you will see the main console with a prompt:
    satellite>  

Example commands:
    help
    status
    rotate south
    activate
    collect
    stats
    quickstats
    history
    reset
    exit

Command Reference
-----------------
Satellite Operations:
- rotate <direction> : Rotate satellite  
- activate : Activate solar panels  
- deactivate : Deactivate solar panels  
- collect : Collect data (requires active panels)  
- reset : Reset to initial state  

Analytics Commands:
- stats : Show detailed statistics  
- quickstats or qs : Show compact statistics  

System Commands:
- status : Show current status  
- history : Show command history  
- clear : Clear history  
- help : Show help message  
- exit or quit : Exit the application  

Valid Directions: north, south, east, west, north-east, north-west, south-east, south-west

Troubleshooting
---------------
Problem: "java: command not found"  
Solution: Install Java JDK 11+ and add to PATH  

Problem: "Could not find or load main class"  
Solution:  
    cd ex2/satellitecmdsys  
    javac -d bin -sourcepath src/main/java src/main/java/com/satellite/command/**/*.java  
    java -cp bin com.satellite.command.SatelliteCommandSystemApplication  

Problem: Eclipse shows errors  
Solution: Project → Clean → OK, or update Maven project  


Verification Checklist
----------------------
- Application starts without errors  
- Help command works  
- Rotate in all directions works  
- Activate/deactivate panels works  
- Collect data works when panels active  
- Statistics and history work  
- Reset confirmation works  
- Application exits cleanly  

