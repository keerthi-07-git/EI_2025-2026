Abstract Factory Pattern

Overview  
The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes. Here, it is applied to database connections where different factories create MySQL or PostgreSQL drivers.

Setup  
Clone this repository or download it as a ZIP and extract it:
https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into CreationalDesignPattern/AbstractFactory before running the commands below.

Structure  
- DBConnection: Product interface  
- DBFactory: Factory interface  
- MySQLConnection, PostgresConnection: Concrete products  
- MySQLFactory, PostgresFactory: Concrete factories  
- CreationalAbstractFactory: Main class with entry point  

How to Run  
Compile:
javac -d out -cp out src/*.java CreationalAbstractFactory.java

Run:

java -cp out creationaldesignpattern.abstractfactory.src.CreationalAbstractFactory

Expected Output:
Connecting PostgreSQL...
