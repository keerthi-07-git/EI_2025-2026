Strategy Pattern

Overview  
The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Here, it demonstrates switching between sorting algorithms (QuickSort and MergeSort) at runtime.

Setup  
Clone this repository or download it as a ZIP and extract it:
https://github.com/keerthi-07-git/EI_2025-2026.git


Navigate into BehaviouralDesignPattern/Strategy before running the commands below.

Structure  
- SortStrategy: Strategy interface  
- QuickSort: Concrete strategy  
- MergeSort: Concrete strategy  
- Sorter: Context class that uses a strategy  
- BehaviouralStrategy: Main class with entry point  

How to Run  
Compile:
javac -d out -cp out src/*.java BehaviouralStrategy.java

Run:
java -cp out behaviouraldesignpattern.strategy.src.BehaviouralStrategy

Expected Output:
QuickSort executed  
MergeSort executed
