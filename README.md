# Library Management System

A Java-based command-line Library Management System for managing books, members, book issue/return operations, searching, sorting, data persistence, and library statistics.

---

## 1. Project Overview

The **Library Management System** is a menu-driven Java application developed to simplify and organize common library management activities.

The system allows the user to manage book and member records through a command-line interface. It provides operations for adding, removing, searching and displaying books, managing library members, issuing and returning books, viewing issued books, sorting books, saving data, and generating basic library statistics.

The project follows a modular and object-oriented structure. The application separates the user interface, data models, business logic, and file handling into different classes and packages.

The main components of the system are:

- `Main.java` — Handles the command-line interface and user interaction.
- `Book.java` — Represents book information.
- `Member.java` — Represents library member information.
- `Library.java` — Contains the main library management logic.
- `FileManager.java` — Handles CSV-based data storage.

The application is developed using **Java 17** and **Maven**.

---

## 2. Problem Statement

Managing books and members manually can become difficult when the number of records increases. Library staff need to keep track of available books, issued books, registered members, and borrowing information.

The purpose of this project is to develop a simple computerized system that provides a centralized interface for performing common library operations.

The system reduces repetitive manual record management by allowing the user to perform operations such as:

- Adding and removing books
- Searching for books
- Displaying available library records
- Registering members
- Issuing books
- Returning books
- Sorting books
- Saving records
- Viewing library statistics

---

## 3. Objectives

The main objectives of the project are:

1. To develop a functional Library Management System using Java.
2. To apply Object-Oriented Programming concepts in a practical application.
3. To manage book and member records using Java classes and collections.
4. To implement book issue and return functionality.
5. To maintain book availability information.
6. To provide searching and sorting functionality.
7. To implement persistent data storage using CSV files.
8. To provide basic library statistics.
9. To organize the project using a modular package structure.
10. To build and package the application using Maven.

---

## 4. How the System Works

The application follows a menu-driven workflow.

When the application starts, the main menu is displayed to the user. The user selects an operation by entering the corresponding number.

The selected operation is processed by the `Library` service class. Depending on the operation, the system may interact with `Book` objects, `Member` objects, and the `FileManager` utility.

After completing the operation, the system displays the result and returns to the main menu.

The general workflow is:

```text
Start
  |
  v
Load Stored Data
  |
  v
Display Main Menu
  |
  v
User Selects Operation
  |
  v
Validate Input
  |
  v
Perform Library Operation
  |
  v
Display Result
  |
  v
Return to Main Menu
  |
  +------> Exit