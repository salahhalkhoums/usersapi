# Users API

Users API is a RESTful web service that provides endpoints for managing user data. It includes Swagger and Javadoc documentation for easy reference, and offers two main endpoints for retrieving user data by ID and creating new user objects.

## Documentation

- Swagger Documentation: The API documentation is available at [Swagger UI](http://localhost:8080/swagger-ui.html) after running the application.
- Javadoc Documentation: The Javadoc documentation for the project is available in the javadoc directory.

## Requirements

To build and run the Users API application, you will need the following:

- JDK 11: Java Development Kit version 11 or higher.
- Maven 3: Build automation tool for building Java applications.

## Endpoints

The project includes the following REST endpoints:

### Get User by ID

- URL: /api/users/{id}
- Method: GET
- Description: Retrieves a user by ID.
- Response:
  - Status Code: 200 OK if successful
  - Body: JSON representation of the retrieved user object

### Create User

- URL: /api/users
- Method: POST
- Description: Creates a new user.
- Request Body: JSON representation of the user object to be created
- Response:
  - Status Code: 201 Created if successful
  - Body: JSON representation of the created user object

## Installation and Setup

The Users API application uses the H2 in-memory database. To run the Users API application locally, you have a few options:

- Run from IDE: Execute the main method in the UsersapiApplication class from your IDE.
- Run with Maven: Open a terminal and navigate to the root directory of the project. Run the following command: mvn spring-boot:run.
- The application will start and be accessible at http://localhost:8080. You can then access the Swagger UI documentation at http://localhost:8080/swagger-ui.html to interact with the API endpoints.
