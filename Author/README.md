# Author Service

A Spring Boot microservice for managing authors.

## Features
- CRUD operations for authors
- Search authors by name
- Filter authors by nationality
- RESTful API endpoints

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Database (configured in application.yml)

### Running the Application
```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

## API Documentation

### Base URL
```
http://localhost:8080/api/authors
```

### Endpoints

#### 1. Get All Authors
- **Method**: GET
- **URL**: `http://localhost:8080/api/authors`
- **Description**: Retrieves all authors

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8080/api/authors
Headers: 
  Content-Type: application/json
```

#### 2. Get Author by ID
- **Method**: GET
- **URL**: `http://localhost:8080/api/authors/{id}`
- **Description**: Retrieves a specific author by ID

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8080/api/authors/1
Headers: 
  Content-Type: application/json
```

#### 3. Create New Author
- **Method**: POST
- **URL**: `http://localhost:8080/api/authors`
- **Description**: Creates a new author

**Postman Configuration:**
```
Method: POST
URL: http://localhost:8080/api/authors
Headers: 
  Content-Type: application/json
Body (raw JSON):
{
  "name": "Stephen King",
  "birthYear": 1947,
  "nationality": "American"
}
```

#### 4. Update Author
- **Method**: PUT
- **URL**: `http://localhost:8080/api/authors/{id}`
- **Description**: Updates an existing author

**Postman Configuration:**
```
Method: PUT
URL: http://localhost:8080/api/authors/1
Headers: 
  Content-Type: application/json
Body (raw JSON):
{
  "id": 1,
  "name": "J.K. Rowling",
  "birthYear": 1965,
  "nationality": "British"
}
```

#### 5. Delete Author
- **Method**: DELETE
- **URL**: `http://localhost:8080/api/authors/{id}`
- **Description**: Deletes an author by ID

**Postman Configuration:**
```
Method: DELETE
URL: http://localhost:8080/api/authors/1
Headers: 
  Content-Type: application/json
```

#### 6. Search Authors by Name
- **Method**: GET
- **URL**: `http://localhost:8080/api/authors/search?name={name}`
- **Description**: Searches authors by name (case-insensitive)

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8080/api/authors/search?name=rowling
Headers: 
  Content-Type: application/json
```

#### 7. Get Authors by Nationality
- **Method**: GET
- **URL**: `http://localhost:8080/api/authors/nationality/{nationality}`
- **Description**: Retrieves authors by nationality

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8080/api/authors/nationality/British
Headers: 
  Content-Type: application/json
```

## Sample Data

The application automatically initializes with the following sample data:

1. **J.K. Rowling** (ID: 1)
   - Birth Year: 1965
   - Nationality: British

2. **George Orwell** (ID: 2)
   - Birth Year: 1903
   - Nationality: British

3. **Harper Lee** (ID: 3)
   - Birth Year: 1926
   - Nationality: American

## Response Examples

### Success Response (GET /api/authors)
```json
[
  {
    "id": 1,
    "name": "J.K. Rowling",
    "birthYear": 1965,
    "nationality": "British"
  },
  {
    "id": 2,
    "name": "George Orwell",
    "birthYear": 1903,
    "nationality": "British"
  },
  {
    "id": 3,
    "name": "Harper Lee",
    "birthYear": 1926,
    "nationality": "American"
  }
]
```

### Error Response (404 Not Found)
```json
{
  "timestamp": "2024-01-01T12:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Author not found",
  "path": "/api/authors/999"
}
```
