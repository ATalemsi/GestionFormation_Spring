# GestionFormation

## Project Overview
**GestionFormation** is a Spring Boot application designed to manage educational formations, including classes, instructors, and students. This project provides a structured approach to handling various aspects of a learning management system.

## Key Features
- Manage **Classes** with attributes such as name, classroom number, and associated students.
- Manage **Students (Apprenants)** enrolled in each class.
- Manage **Instructors (Formateurs)** associated with each class.
- Utilizes RESTful API endpoints for interaction with the application.
- Employs JSON serialization and deserialization for consistent frontend-backend communication.

## Technologies Used
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **Dependencies**:
    - Spring Web
    - Spring Data JPA
    - Spring Boot Validation
    - Lombok
    - Jackson for JSON processing
    - JUnit for testing

## Project Setup

### 1. Configure PostgreSQL Database
Create a PostgreSQL database for the project. Update the database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
spring.jpa.hibernate.ddl-auto=update
```

### 2. Install Dependencies
Ensure all dependencies are installed using Maven:

```bash
mvn clean install
```

### 3. Run the Application
Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will be available at [http://localhost:8080](http://localhost:8080).

## Project Structure
### Models
**Classe**: Represents a class with attributes:
- id: Unique identifier for the class.
- nom: Name of the class.
- numSalle: Room number for the class.
- formateur: One-to-one relationship with the Formateur (instructor).
- apprenants: One-to-many relationship with Apprenant (students), initialized to avoid NullPointerException.

Example:

```java
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String numSalle;

    @OneToOne(mappedBy = "classe")
    private Formateur formateur;

    @OneToMany(mappedBy = "classe")
    private List<Apprenant> apprenants = new ArrayList<>();
}
```

**Apprenant**: Represents a student with attributes such as id, nom, prenom, and a many-to-one relationship with Classe.

**Formateur**: Represents an instructor, associated one-to-one with a Classe.

## REST API Endpoints
### Classe Endpoints
- **POST** /classe/add: Create a new class.
- **GET** /classe/all: Retrieve all classes.
- **GET** /classe/{id}: Retrieve a specific class by ID.
- **PUT** /classe/update/{id}: Update an existing class.
- **DELETE** /classe/delete/{id}: Delete a class.

### Apprenant Endpoints
- **POST** /apprenant/add: Add a new student.
- **GET** /apprenant/all: Retrieve all students.
- **PUT** /apprenant/update/{id}: Update student details.
- **DELETE** /apprenant/delete/{id}: Remove a student.

### Formateur Endpoints
- **POST** /formateur/add: Add a new instructor.
- **GET** /formateur/all: Retrieve all instructors.
- **PUT** /formateur/update/{id}: Update instructor details.
- **DELETE** /formateur/delete/{id}: Remove an instructor.

## Error Handling
Errors encountered during processing, such as null values for apprenants, are managed by initializing lists to prevent NullPointerException. If serialization issues arise, default empty lists are used.

## Sample JSON for Classe Creation
When creating a Classe, ensure all required fields are provided:

```json
{
  "nom": "Math Class",
  "numSalle": "101",
  "formateur": {
    "id": 1
  },
  "apprenants": [
    {
      "id": 1,
      "nom": "Doe",
      "prenom": "John"
    },
    {
      "id": 2,
      "nom": "Smith",
      "prenom": "Jane"
    }
  ]
}
```

## Common Issues
- **NullPointerException in JSON Serialization**: Ensure that apprenants is initialized with new ArrayList<>() in the Classe model to avoid serialization errors when the list is empty.
- **Database Connection Error**: Check your PostgreSQL connection settings in application.properties. Ensure the database server is running, and credentials are correct.
- **Validation Errors**: This project uses Spring Boot validation for required fields like nom and numSalle. Invalid data will result in a 400 Bad Request with details in the response.

## Tests
Run unit and integration tests using Maven:

```bash
mvn test
```

### 1. Clone the Repository
```bash
git clone https://github.com/JavaAura/ABDELLAH_TALEMSI_S3_B2_GestionFormation.git
cd ABDELLAH_TALEMSI_S3_B2_GestionFormation
```

## Conclusion
**GestionFormation** aims to streamline the management of educational formations, making it easier for institutions to manage their classes, students, and instructors effectively.
