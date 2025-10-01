# Decorator Pattern

## Overview
The Decorator pattern allows behavior to be added to individual objects, dynamically, without affecting the behavior of other objects from the same class. Here, `SimpleCoffee` can be decorated with `MilkDecorator` and `SugarDecorator` to add new functionality.

## Setup
Clone this repository or download it as a ZIP and extract it:

https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into `StructuralDesignPattern/Decorator` before running the commands below.

## Structure

- **Coffee**: Interface for coffee objects  
- **SimpleCoffee**: Concrete implementation of `Coffee`  
- **CoffeeDecorator**: Abstract decorator implementing `Coffee`  
- **MilkDecorator**: Adds milk functionality to a coffee object  
- **SugarDecorator**: Adds sugar functionality to a coffee object  
- **StructuralDecorator**: Main class with entry point

## How to Run
**Compile:**  
```bash
javac -d out -cp out src/*.java StructuralDecorator.java


How to Run  
Compile:
javac -d out -cp out src/*.java StructuralAdapter.java

Run:
java -cp out structuraldesignpatterns.adapter.src.StructuralAdapter
Cost: <total_cost>
Description: Simple Coffee, Milk, Sugar

Output will vary depending on which decorators you apply and in what order.

