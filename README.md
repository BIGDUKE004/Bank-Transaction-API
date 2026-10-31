# Bank Transaction API

A RESTful banking transaction management system built with **Java 21 and Spring Boot**. The application provides a backend foundation for managing banking transactions, validating financial operations, and maintaining transaction records through a structured service-oriented architecture.

## Overview

The Bank Transaction API is a backend application designed to handle core banking transaction operations through RESTful endpoints.

It focuses on transaction processing, account management, business rule enforcement, and reliable financial calculations using Java's `BigDecimal` for monetary precision.

The application follows a layered architecture that separates HTTP request handling, business logic, data access, and persistence responsibilities.

## Key Features

- **Transaction Management:** Supports banking transaction operations and transaction record management.
- **Fund Transfers:** Implements account-to-account fund transfer processing.
- **Amount Validation:** Enforces transaction amount rules, including positive amount validation.
- **Account Validation:** Validates account information before processing transactions.
- **Discount Calculations:** Handles transaction and loyalty discount calculations according to business rules.
- **Database Persistence:** Uses Spring Data JPA to manage and persist application data.
- **Input Validation:** Applies Bean Validation to validate incoming request data.
- **Exception Handling:** Uses structured exception handling for business validation and transaction errors.
- **Monetary Precision:** Uses Java `BigDecimal` for accurate financial calculations.
- **Parameterized Testing:** Uses JUnit 5 parameterized tests to validate multiple input scenarios and business rules.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Primary programming language |
| Spring Boot 4.1.1 | Application framework |
| Spring Web MVC | REST API development |
| Spring Data JPA | Database interaction and ORM |
| PostgreSQL | Relational database |
| Hibernate | ORM implementation |
| Bean Validation | Request and data validation |
| JUnit 5 | Unit and parameterized testing |
| Maven | Dependency and build management |
| Lombok | Boilerplate code reduction |

## Architecture

The application follows a layered architecture:

```text
Client
  |
  v
Controller Layer
  |
  v
Service Layer
  |
  v
Repository Layer
  |
  v
PostgreSQL Database
```

### Layer Responsibilities

**Controller Layer**
- Exposes RESTful API endpoints.
- Receives and processes HTTP requests.
- Delegates business operations to the service layer.
- Returns appropriate HTTP responses.

**Service Layer**
- Contains core banking business logic.
- Handles transaction processing.
- Enforces transaction validation rules.
- Performs financial calculations.
- Coordinates interactions between repositories.

**Repository Layer**
- Handles database operations.
- Provides data access through Spring Data JPA.
- Manages persistence and retrieval of application entities.

**Entity and DTO Layer**
- Entities represent persistent database records.
- Data Transfer Objects (DTOs) define the structure of incoming requests and outgoing responses.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java Development Kit (JDK) 21 or later.
- PostgreSQL.
- Maven (or use the included Maven Wrapper).
- Git.

### 1. Clone the Repository

```bash
git clone https://github.com/BIGDUKE004/Bank-Transaction-API.git
```

### 2. Navigate into the Project

```bash
cd Bank-Transaction-API
```

### 3. Configure the Database

Create a PostgreSQL database for the application.

```sql
CREATE DATABASE bankdb;
```

Configure your database connection in `src/main/resources/application.properties` or through environment variables, depending on your application configuration.

Example database configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bankdb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

Set your database credentials before starting the application.

### 4. Build the Application

Using the Maven Wrapper:

```bash
./mvnw clean install
```

### 5. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start using its configured server port.

## Running Tests

The project uses JUnit 5 for automated testing.

Run the complete test suite:

```bash
./mvnw test
```

Run a clean build with tests:

```bash
./mvnw clean verify
```

### Testing Approach

The testing strategy covers:

- Transaction amount validation.
- Positive, zero, and negative transaction amounts.
- Account validation.
- Successful and unsuccessful transfers.
- Discount calculation scenarios.
- Exception handling.
- Transaction business rules.

Parameterized testing with JUnit 5 allows multiple input combinations to be tested efficiently using annotations such as `@ParameterizedTest`, `@CsvSource`, and `@MethodSource`.

## Financial Calculation

The application uses Java's BigDecimal for monetary operations.

This provides precise decimal arithmetic and avoids the rounding inaccuracies associated with floating-point types such as `float` and `double`.

Example:

java
BigDecimal amount = new BigDecimal("1000.00");
BigDecimal balance = new BigDecimal("5000.00");

BigDecimal remainingBalance = balance.subtract(amount);


## Development Goals

This project demonstrates practical backend engineering concepts, including:

- Designing RESTful APIs with Spring Boot.
- Applying layered architecture and separation of concerns.
- Implementing business logic in service classes.
- Working with relational databases using Spring Data JPA.
- Applying validation and exception handling.
- Performing accurate financial calculations.
- Writing maintainable unit tests and parameterized tests.

## Author

**Elijah Miracle**

Full-Stack Software Engineer

GitHub: [@BIGDUKE004](https://github.com/BIGDUKE004)

---

If you find this project useful, feel free to explore the repository and follow its development.

**Built with Java and Spring Boot.**
