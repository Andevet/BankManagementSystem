# Bank Management System - Java OOP Final Project

## Project Overview
This system is a comprehensive backend solution for managing bank operations, developed as a final project for the Object-Oriented Programming course. The application manages various types of accounts, financial logic, and client relations while adhering to high engineering standards.

## Key Features & Business Logic
The system implements a real-world banking model with the following functionalities:

### 1. Account Management
- **Diverse Account Types:** Supports **Checking** (Regular & Business), **Savings**, and **Mortgage** accounts.
- **Automated Calculations:** - **Management Fees:** Calculated automatically for relevant accounts via the `ManagementFees` interface.
    - **Profit Calculation:** Specialized logic to calculate the bank's annual profit from each account type (Checking credit lines, Mortgage interests, etc.).
    - **VIP Benefits:** Specialized handling for high-tier accounts using the `Vipable` and `VIPAccount` interfaces.

### 2. Advanced Reporting (Facade Pattern)
Using the **ReportFacade**, the system generates various reports:
- View all accounts sorted by **Profit** (Descending) or **Account ID** (Ascending).
- Detailed view of specific account types.
- Summary of the CEO's annual bonus based on collected management fees.

### 3. System Stability
- **Custom Exception Handling:** Robust validation for account numbers, names, and financial limits (e.g., `AmountException`, `CheckedException`, `NameException`).
- **Data Integrity:** Ensuring no duplicate accounts or invalid client ranks (1-10).

## Technical Implementation (Engineering Principles)
- **Object-Oriented Programming:** Full use of **Inheritance**, **Polymorphism**, and **Abstract Classes** (e.g., the `Account` base class).
- **Design Patterns:**
    - **Observer Pattern:** Implemented to notify the system of specific account actions or updates.
    - **Facade Pattern:** Provided a simplified interface (`ReportFacade`, `AccountFacade`) for complex sub-system operations.
    - **Singleton:** Used for the `Bank` class to ensure a single source of truth.
- **Interfaces:** Decoupled logic using interfaces like `Profitable`, `ManagementFees`, and `AccountInterface`.
- **SOLID Principles:** Focused on Single Responsibility and Interface Segregation.

## Technologies Used
- **Language:** Java
- **Tools:** Git, Eclipse
- **Environment:** Linux / Windows
