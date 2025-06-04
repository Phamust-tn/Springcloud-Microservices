# Microservices with Containerized Databases

This project contains two Spring Boot microservices (Author and Book) that run locally and connect to containerized PostgreSQL databases.

## Architecture

- **Applications**: Run natively on your machine (outside containers)
- **Databases**: Run in Docker containers for easy setup and isolation

## Project Structure

```
Microservice Springboot/
├── docker-compose.yml          # Database containers only
├── Author/
│   ├── docker-compose.yml      # Author database only
│   └── src/...
└── Book/
    ├── docker-compose.yml      # Book database only
    └── src/...
```

## Database Configuration

### Author Service
- **Database**: `author_db` (container)
- **Port**: `5432`
- **Service Port**: `8080` (local)

### Book Service
- **Database**: `book_db` (container)
- **Port**: `5433` (mapped to avoid conflict)
- **Service Port**: `8081` (local)

## Quick Start

### 1. Start Database Containers

From the root directory (`Microservice Springboot/`):

```bash
# Start both database containers
docker-compose up -d

# Verify databases are running
docker ps
```

### 2. Run Applications Locally

#### Author Service

```bash
cd Author
./mvnw spring-boot:run
```

#### Book Service

```bash
cd Book
./mvnw spring-boot:run
```

## Alternative: Individual Database Setup

### Author Database Only

```bash
cd Author
docker-compose up -d
```

### Book Database Only

```bash
cd Book
docker-compose up -d
```

## API Endpoints

### Author Service (http://localhost:8080)
- `GET /api/authors` - Get all authors
- `POST /api/authors` - Create new author
- `GET /api/authors/{id}` - Get author by ID
- `PUT /api/authors/{id}` - Update author
- `DELETE /api/authors/{id}` - Delete author
- `GET /api/authors/search?name={name}` - Search authors by name

### Book Service (http://localhost:8081)
- `GET /api/books` - Get all books
- `POST /api/books` - Create new book
- `GET /api/books/{id}` - Get book by ID
- `PUT /api/books/{id}` - Update book
- `DELETE /api/books/{id}` - Delete book
- `GET /api/books/author/{authorId}` - Get books by author ID
- `GET /api/books/search?title={title}` - Search books by title

## Database Access

### Author Database
- **Host**: localhost
- **Port**: 5432
- **Database**: author_db
- **Username**: postgres
- **Password**: password

### Book Database
- **Host**: localhost
- **Port**: 5433
- **Database**: book_db
- **Username**: postgres
- **Password**: password

## Development Workflow

1. **Start databases**: `docker-compose up -d`
2. **Run Author service**: `cd Author && ./mvnw spring-boot:run`
3. **Run Book service**: `cd Book && ./mvnw spring-boot:run`
4. **Stop databases**: `docker-compose down`

## Benefits of This Setup

✅ **Fast Development**: Applications run natively (faster startup)  
✅ **Easy Database Management**: Containerized databases  
✅ **No Port Conflicts**: Book DB uses port 5433  
✅ **Persistent Data**: Data survives container restarts  
✅ **Simple Debugging**: Full IDE support for applications  

## Troubleshooting

1. **Port Conflicts**: Ensure ports 5432, 5433, 8080, and 8081 are available
2. **Database Connection**: Wait for containers to fully start before running apps
3. **Clean Restart**: `docker-compose down -v && docker-compose up -d`

## Health Checks

```bash
# Check database containers
docker ps

# Test applications
curl http://localhost:8080/api/authors
curl http://localhost:8081/api/books
```
