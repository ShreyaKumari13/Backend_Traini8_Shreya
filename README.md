# Backend Training Project

## Overview

This project is a Spring Boot-based backend application that handles operations related to training centers, courses, and addresses. It follows a layered architecture with controllers, services, and repositories. DTOs (Data Transfer Objects) are used for communication between layers, and exception handling is managed globally.

## Features

- **Centers Management**: Create, update, and delete training centers.
- **Courses Management**: Add or modify courses related to the training centers.
- **Address Management**: Handle address details of the centers.
- **Global Exception Handling**: Custom handling for application-wide exceptions.



## Prerequisites

To run this project, ensure you have the following installed:

- **Java 8 or above**
- **Gradle 6.0 or above** (Alternatively, use the Gradle wrapper provided)
- **MySQL** or any other relational database (configure in `application.properties`)

## Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-repo/backend-training.git


2. **Navigate to the project directory:**

   ```bash
   cd Backend_Traini8_Shreya


3. **Update database configuration:**

   Open src/main/resources/application.properties and update the following properties with your database details:

   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password


4. **Build the project:**
You can use the Gradle wrapper to build the project:

   ```bash
   ./gradlew build  # For Unix-based systems
gradlew.bat build  # For Windows

5. **Run the application:**
```bash
./gradlew bootRun

6. **Access the API:**
You can test the API endpoints using Postman or any API testing tool. Some sample endpoints are:

http://localhost:8080/centers -> use this as localhost


GET /centers/all - Get all centers
GET /centers/{id} - Get centers by id
POST /centers - Create a new center(take this example)
    {
        "centerName": "AI Innovation Hub",
        "centerCode": "AIH013256789",
        "address": {
            "detailedAddress": "789 Innovation Drive, Floor 3",
            "city": "Pune",
            "state": "Maharashtra",
            "pinCode": 411001
        },
        "studentCapacity": 250,
        "courses": [
            {
                "name": "Artificial Intelligence"
            },
            {
                "name": "Machine Learning with TensorFlow"
            },
            {
                "name": "Deep Learning Specialization"
            }
        ],
        "createdAt": "2024-10-09T15:30:12.293182",
        "email": "support@aihub.com",
        "phone": "0987123456"
    }
PUT /centers/{id} - Update an existing center
DELETE /centers/{id} - Delete a center
GET /centers/exists/{centerName} - To check whether a centre exist with returns boolean value

## Testing

To run the tests:

```bash
./gradlew bootRun
 
