# Builder Pattern

## Overview
The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations. In this example, an `HttpRequest` object is built step-by-step using a nested `Builder` class. This allows flexible creation of HTTP requests with optional fields like the request body.

## Setup
Clone this repository or download it as a ZIP and extract it:

https://github.com/keerthi-07-git/EI_2025-2026.git

Navigate into `CreationalDesignPatterns/Builder` before running the commands below.

## Structure
- **HttpRequest**: The main class representing an HTTP request. Immutable once constructed.  
- **HttpRequest.Builder**: Nested static class used to construct `HttpRequest` objects. Supports method chaining for optional parameters.  
- **CreationalBuilder**: Main class with entry point to demonstrate building an HTTP request interactively.

## How to Run

### Compile
javac -d out -cp out src/*.java CreationalBuilder.java
### Run:
java -cp out creationaldesignpatterns.builder.src.CreationalBuilder

## Usage

You will be prompted to enter:

URL

HTTP Method (GET, POST, PUT, DELETE)

Request Body (optional for non-GET methods)

The program will then display the built request in the format:

METHOD URL Body:body
