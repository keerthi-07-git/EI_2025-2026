Chain of Responsibility Pattern

Overview  
The Chain of Responsibility pattern lets you pass requests along a chain of handlers. Each handler can either process the request or forward it to the next handler. In this example, it models HTTP Middleware, where authentication and logging are chained together.

Setup  
Clone this repository or download it as a ZIP and extract it:
git clone https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into BehaviouralDesignPattern/ChainOfResponsibility before running the commands below.

Structure  
- Request: Represents a user request  
- Middleware: Abstract handler that links to the next middleware  
- AuthMiddleware: Checks if the user is authenticated  
- LogMiddleware: Logs request data  
- BehaviouralChain: Main class with entry point  

How to Run  
Compile:
javac -d out -cp out src/*.java BehaviouralChain.java

Run:
java -cp out behaviouraldesignpattern.chainofresponsibility.src.BehaviouralChain

Expected Output:
Auth ok  
Log: upload-file
