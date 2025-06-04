package tn.esprit.author.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.author.dto.BookDto;
import tn.esprit.author.entity.Author;
import tn.esprit.author.service.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Author Management", description = "APIs for managing authors in the library system")
public class AuthorController {    
    private final AuthorService authorService;

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> getBooksForAuthor(@PathVariable Long id) {
        List<BookDto> books = authorService.getBooksByAuthorId(id);
        return ResponseEntity.ok(books);
    }


    @Operation(summary = "Create a new author", description = "Creates a new author in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Author> createAuthor(
            @Parameter(description = "Author object to be created", required = true)
            @RequestBody Author author) {
        Author savedAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors")
    @ApiResponse(responseCode = "200", description = "List of authors retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }
    
    @Operation(summary = "Get author by ID", description = "Retrieves a specific author by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(
            @Parameter(description = "ID of the author to retrieve", required = true)
            @PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        return author.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Search authors by name", description = "Searches for authors by name (case-insensitive)")
    @ApiResponse(responseCode = "200", description = "Authors found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthorsByName(
            @Parameter(description = "Name or partial name to search for", required = true)
            @RequestParam String name) {
        List<Author> authors = authorService.searchAuthorsByName(name);
        return ResponseEntity.ok(authors);
    }
    
    @Operation(summary = "Get authors by nationality", description = "Retrieves all authors of a specific nationality")
    @ApiResponse(responseCode = "200", description = "Authors found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Author>> getAuthorsByNationality(
            @Parameter(description = "Nationality to filter by", required = true)
            @PathVariable String nationality) {
        List<Author> authors = authorService.getAuthorsByNationality(nationality);
        return ResponseEntity.ok(authors);
    }
    
    @Operation(summary = "Get authors by birth year", description = "Retrieves all authors born in a specific year")
    @ApiResponse(responseCode = "200", description = "Authors found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @GetMapping("/birth-year/{birthYear}")
    public ResponseEntity<List<Author>> getAuthorsByBirthYear(
            @Parameter(description = "Birth year to filter by", required = true)
            @PathVariable Integer birthYear) {
        List<Author> authors = authorService.getAuthorsByBirthYear(birthYear);
        return ResponseEntity.ok(authors);
    }
    
    @Operation(summary = "Get authors born after year", description = "Retrieves all authors born after a specific year")
    @ApiResponse(responseCode = "200", description = "Authors found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @GetMapping("/born-after/{year}")
    public ResponseEntity<List<Author>> getAuthorsAfterYear(
            @Parameter(description = "Year to filter authors born after", required = true)
            @PathVariable Integer year) {
        List<Author> authors = authorService.getAuthorsAfterYear(year);
        return ResponseEntity.ok(authors);
    }
    
    @Operation(summary = "Update author", description = "Updates an existing author's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @Parameter(description = "ID of the author to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated author information", required = true)
            @RequestBody Author authorDetails) {
        try {
            Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
            return ResponseEntity.ok(updatedAuthor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Delete author", description = "Deletes an author from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Author deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(
            @Parameter(description = "ID of the author to delete", required = true)
            @PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
