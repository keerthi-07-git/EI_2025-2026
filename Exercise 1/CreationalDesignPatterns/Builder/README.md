Builder Pattern

Overview  
The Builder pattern lets you construct complex objects step by step. Here, it builds an HTTP request object with URL, method, and body.

Setup  
Clone this repository or download it as a ZIP and extract it:
https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into CreationalDesignPattern/Builder before running the commands below.

Structure  
- HttpRequest: Product class  
- HttpRequest.Builder: Builder inner class  
- CreationalBuilder: Main class with entry point  

How to Run  
Compile:
javac -d out -cp out src/*.java CreationalBuilder.java

Run:
java -cp out creationaldesignpattern.builder.src.CreationalBuilder

Expected Output:
POST /api/data Body:{id:1}
