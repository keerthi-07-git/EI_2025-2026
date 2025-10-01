Adapter Pattern

Overview  
The Adapter pattern converts the interface of a class into another interface clients expect. Here, a LegacyLogger is adapted to work as a ModernLogger.

Setup  
Clone this repository or download it as a ZIP and extract it:

https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into StructuralDesignPattern/Adapter before running the commands below.

Structure  
- LegacyLogger: Existing class  
- ModernLogger: Target interface  
- LoggerAdapter: Adapter class  
- StructuralAdapter: Main class with entry point  

How to Run  
Compile:
javac -d out -cp out src/*.java StructuralAdapter.java

Run:
java -cp out structuraldesignpatterns.adapter.src.StructuralAdapter

Expected Output:
Legacy: System started
