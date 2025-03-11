![Screenshot 2025-03-11 154031](https://github.com/user-attachments/assets/c069a420-58b1-4db0-8f31-029becda90b2)# Employee Performance Bell Curve

## Overview

This project evaluates employee performance and assigns ratings using a bell curve model. It fetches employee data, calculates actual performance distribution, compares it with the expected bell curve, and suggests necessary adjustments. The frontend visually displays performance data in a bell curve chart.

### Project UI

![Screenshot 2025-03-11 154031](https://github.com/user-attachments/assets/e9e7becf-af39-42d6-ace1-4764ee221d5d)


## Features

- Fetch employee data from the database.
- Calculate actual and expected performance percentages.
- Identify differences and suggest rating adjustments.
- Provide REST API endpoints for performance data.
- Display performance distribution using Chart.js.

## Installation & Setup

### Prerequisites

- Java 17 or later
- Spring Boot
- MySQL Database
- Maven
- postman(Testing)

### Steps to Run Backend

1. Clone the repository:
   ```sh
   git clone https://github.com/maheshasn93/Employee_performance.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Employee-performance
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employeeperformance
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   # Hibernate Properties
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
4. Build the project:
   ```sh
   mvn clean install
   ```
5. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Steps to Run Frontend

1. Open the `index.html` file in a browser.
2. The frontend will fetch and display performance data from the backend.

## API Endpoints

- **Total Employees:** `GET /api/performance/total-employees`
- **Actual Performance Percentage:** `GET /api/performance/actual-percentage`
- **Deviation Calculation:** `GET /api/performance/deviation`
- **Suggested Rating Adjustments:** `GET /api/performance/suggested-changes`

## Formulas Used

### Actual Performance Percentage

To find the percentage of employees in each rating category:

```
Actual Percentage = (Number of employees in a category / Total number of employees) * 100
```

### Expected Performance Percentage (Bell Curve Model)

Each rating category has a predefined expected percentage.

### Deviation Calculation

To find how much the actual percentage differs from the expected:

```
Deviation = Actual Percentage - Expected Percentage
```

### Standard Deviation

To measure how much ratings vary from the average:

```
Standard Deviation = Square root of (Sum of (Each Rating - Average Rating)² / Total Employees)
```

### Z-Score Calculation

To determine how far an employee's rating is from the average:

```
Z-Score = (Employee Rating - Average Rating) / Standard Deviation
```

## Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.7.0</version>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.7.0</version>
    </dependency>
</dependencies>
```

## Test Cases

- Check actual performance percentage calculations.
- Verify expected performance percentage matches predefined values.
- Validate deviation calculations.
- Ensure rating adjustments are correct.
- Test API responses for accuracy.
- Confirm frontend displays correct percentage and employee count.


