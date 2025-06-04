# Book Service

A Spring Boot microservice for managing books.

## Features
- CRUD operations for books
- Search books by title and genre
- Filter books by author
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

The application will start on port 8081 by default.

## API Documentation

### Base URL
```
http://localhost:8081/api/books
```

### Endpoints

#### 1. Get All Books
- **Method**: GET
- **URL**: `http://localhost:8081/api/books`
- **Description**: Retrieves all books

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8081/api/books
Headers: 
  Content-Type: application/json
```

#### 2. Get Book by ID
- **Method**: GET
- **URL**: `http://localhost:8081/api/books/{id}`
- **Description**: Retrieves a specific book by ID

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8081/api/books/1
Headers: 
  Content-Type: application/json
```

#### 3. Create New Book
- **Method**: POST
- **URL**: `http://localhost:8081/api/books`
- **Description**: Creates a new book

**Postman Configuration:**
```
Method: POST
URL: http://localhost:8081/api/books
Headers: 
  Content-Type: application/json
Body (raw JSON):
{
  "title": "The Shining",
  "isbn": "978-0-385-12167-8",
  "publicationYear": 1977,
  "genre": "Horror",
  "authorId": 4
}
```

#### 4. Update Book
- **Method**: PUT
- **URL**: `http://localhost:8081/api/books/{id}`
- **Description**: Updates an existing book

**Postman Configuration:**
```
Method: PUT
URL: http://localhost:8081/api/books/1
Headers: 
  Content-Type: application/json
Body (raw JSON):
{
  "id": 1,
  "title": "Harry Potter and the Philosopher's Stone",
  "isbn": "978-0-7475-3269-9",
  "publicationYear": 1997,
  "genre": "Fantasy",
  "authorId": 1
}
```

#### 5. Delete Book
- **Method**: DELETE
- **URL**: `http://localhost:8081/api/books/{id}`
- **Description**: Deletes a book by ID

**Postman Configuration:**
```
Method: DELETE
URL: http://localhost:8081/api/books/1
Headers: 
  Content-Type: application/json
```

#### 6. Get Books by Author ID
- **Method**: GET
- **URL**: `http://localhost:8081/api/books/author/{authorId}`
- **Description**: Retrieves all books by a specific author

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8081/api/books/author/1
Headers: 
  Content-Type: application/json
```

#### 7. Search Books by Title
- **Method**: GET
- **URL**: `http://localhost:8081/api/books/search?title={title}`
- **Description**: Searches books by title (case-insensitive)

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8081/api/books/search?title=harry
Headers: 
  Content-Type: application/json
```

#### 8. Get Books by Genre
- **Method**: GET
- **URL**: `http://localhost:8081/api/books/genre/{genre}`
- **Description**: Retrieves books by genre

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8081/api/books/genre/Fantasy
Headers: 
  Content-Type: application/json
```

#### 9. Get Books by Publication Year
- **Method**: GET
- **URL**: `http://localhost:8081/api/books/year/{year}`
- **Description**: Retrieves books by publication year

**Postman Configuration:**
```
Method: GET
URL: http://localhost:8081/api/books/year/1997
Headers: 
  Content-Type: application/json
```

## Sample Data

The application automatically initializes with the following sample data:

1. **Harry Potter and the Philosopher's Stone** (ID: 1)
   - ISBN: 978-0-7475-3269-9
   - Publication Year: 1997
   - Genre: Fantasy
   - Author ID: 1 (J.K. Rowling)

2. **1984** (ID: 2)
   - ISBN: 978-0-452-28423-4
   - Publication Year: 1949
   - Genre: Dystopian Fiction
   - Author ID: 2 (George Orwell)

3. **To Kill a Mockingbird** (ID: 3)
   - ISBN: 978-0-06-112008-4
   - Publication Year: 1960
   - Genre: Southern Gothic
   - Author ID: 3 (Harper Lee)

## Response Examples

### Success Response (GET /api/books)
```json
[
  {
    "id": 1,
    "title": "Harry Potter and the Philosopher's Stone",
    "isbn": "978-0-7475-3269-9",
    "publicationYear": 1997,
    "genre": "Fantasy",
    "authorId": 1
  },
  {
    "id": 2,
    "title": "1984",
    "isbn": "978-0-452-28423-4",
    "publicationYear": 1949,
    "genre": "Dystopian Fiction",
    "authorId": 2
  },
  {
    "id": 3,
    "title": "To Kill a Mockingbird",
    "isbn": "978-0-06-112008-4",
    "publicationYear": 1960,
    "genre": "Southern Gothic",
    "authorId": 3
  }
]
```

### Error Response (404 Not Found)
```json
{
  "timestamp": "2024-01-01T12:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Book not found",
  "path": "/api/books/999"
}
```

## Integration with Author Service

To get complete book information with author details, you can:

1. First call the Book service to get book data
2. Then call the Author service using the `authorId` from the book response

Example workflow:
1. GET `http://localhost:8081/api/books/1` → Get book with `authorId: 1`
2. GET `http://localhost:8080/api/authors/1` → Get author details
