# Adapter Pattern

## Overview
The Adapter pattern converts the interface of a class into another interface that clients expect. Here, a `LegacyLogger` is adapted to work as a `ModernLogger` using a `LoggerAdapter`.

## Setup
Clone this repository or download it as a ZIP and extract it:

https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into `StructuralDesignPattern/Adapter` before running the commands below.

## Structure

- **LegacyLogger**: Existing class with an older logging method  
- **ModernLogger**: Target interface that client code expects  
- **LoggerAdapter**: Adapter class that bridges `LegacyLogger` to `ModernLogger`  
- **StructuralAdapter**: Main class with entry point to demonstrate the adapter


## How to Run  
**Compile:**
javac -d out -cp out src/*.java StructuralAdapter.java

**Run:**
java -cp out structuraldesignpatterns.adapter.src.StructuralAdapter



Output will vary depending on which decorators you apply and in what order.

