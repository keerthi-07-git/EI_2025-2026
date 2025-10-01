Decorator Pattern

Overview  
The Decorator pattern allows adding new responsibilities to objects dynamically. Here, a FileStream is wrapped with compression and encryption decorators.

Setup  
Clone this repository or download it as a ZIP and extract it:
https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into StructuralDesignPattern/Decorator before running the commands below.

Structure  
- DataStream: Component interface  
- FileStream: Concrete component  
- EncryptDecorator: Concrete decorator  
- CompressDecorator: Concrete decorator  
- StructuralDecorator: Main class with entry point  

How to Run  
Compile:
javac -d out -cp out src/*.java StructuralDecorator.java

Run:
java -cp out structuraldesignpattern.decorator.src.StructuralDecorator

Expected Output:
encrypted(compressed(raw-data))
