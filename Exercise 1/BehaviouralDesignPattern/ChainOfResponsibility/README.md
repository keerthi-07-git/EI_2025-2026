Chain of Responsibility Pattern

Overview
The Chain of Responsibility pattern passes a request along a chain of handlers until one of them handles it.  
Here, it models HTTP Middleware like authentication and logging.

Structure
- Request: Represents a user request.
- Middleware: Abstract handler that links to the next middleware.
- AuthMiddleware: Checks if the user is authenticated.
- LogMiddleware: Logs request data.
- BehaviouralChain: Main class with entry point.

How to Run
javac -d out -cp out src/*.java BehaviouralChain.java
java -cp out behaviouraldesignpattern.chainofresponsibility.src.BehaviouralChain
