# Abstract Factory Pattern – Database Simulation

## Overview
The **Abstract Factory pattern** provides an interface for creating families of related objects without specifying their concrete classes.  

Here, it is applied to **database drivers**, where different factories can create **MySQL** or **PostgreSQL** components.  
Each factory produces a **Connection** and a **Command** object that work together.  

This simulates real-world DB workflows such as **connecting, executing queries, and handling transactions**.

---

## Setup
1. Clone this repository or download it as a ZIP and extract it:  
   git clone https://github.com/keerthi-07-git/EI_2025-2026.git
2. Navigate into:
   cd CreationalDesignPatterns/AbstractFactory

## Implementation

DBConnection → Interface for database connections.

DBCommand → Interface for database commands (queries, transactions).

MySQLConnection / PostgresConnection → Concrete connection classes.

MySQLCommand / PostgresCommand → Concrete command classes.

DBFactory → Abstract factory interface.

MySQLFactory / PostgresFactory → Concrete factories producing MySQL or PostgreSQL families.

CreationalAbstractFactory → Main class (entry point) that demonstrates usage.

## How to Run
### Compile:
javac -d out -cp out src/*.java CreationalAbstractFactory.java

### Run:
java -cp out creationaldesignpatterns.abstractfactory.src.CreationalAbstractFactory

### output 
Choose Database Factory:
1. PostgreSQL
2. MySQL
Enter choice: 1

=== Using PostgreSQL Factory ===
Connecting to PostgreSQL database...
Starting PostgreSQL transaction...
Enter SQL command (or type 'exit' to quit): SELECT * FROM employees
Executing PostgreSQL query: SELECT * FROM employees
Enter SQL command (or type 'exit' to quit): UPDATE employees SET salary = salary + 1000 WHERE id = 101
Executing PostgreSQL query: UPDATE employees SET salary = salary + 1000 WHERE id = 101
Enter SQL command (or type 'exit' to quit): commit
Committing PostgreSQL transaction...
Enter SQL command (or type 'exit' to quit): exit
Program finished.

