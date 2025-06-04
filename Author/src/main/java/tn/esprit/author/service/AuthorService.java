package tn.esprit.author.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tn.esprit.author.dto.BookDto;
import tn.esprit.author.entity.Author;
import tn.esprit.author.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;

    // Create a new author (ID should be null)
    public Author createAuthor(Author author) {
        log.info("Creating new author: {}", author.getName());
        // Ensure ID is null for new entities
        author.setId(null);
        return authorRepository.save(author);
    }

    public List<BookDto> getBooksByAuthorId(Long authorId) {
        log.info("Fetching books for author with ID: {}", authorId);
        ResponseEntity<List<BookDto>> response = restTemplate.exchange(
                "http://BOOK-SERVICE/api/books/author/" + authorId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDto>>() {}
        );

        return response.getBody();
    }
    
    // Save author (for internal use, like data initialization)
    public Author saveAuthor(Author author) {
        log.info("Saving author: {}", author.getName());
        return authorRepository.save(author);
    }
    
    // Get all authors
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        log.info("Fetching all authors");
        return authorRepository.findAll();
    }
    
    // Get author by ID
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        log.info("Fetching author with ID: {}", id);
        return authorRepository.findById(id);
    }
    
    // Search authors by name
    @Transactional(readOnly = true)
    public List<Author> searchAuthorsByName(String name) {
        log.info("Searching authors by name: {}", name);
        return authorRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Get authors by nationality
    @Transactional(readOnly = true)
    public List<Author> getAuthorsByNationality(String nationality) {
        log.info("Fetching authors by nationality: {}", nationality);
        return authorRepository.findByNationality(nationality);
    }
    
    // Get authors by birth year
    @Transactional(readOnly = true)
    public List<Author> getAuthorsByBirthYear(Integer birthYear) {
        log.info("Fetching authors by birth year: {}", birthYear);
        return authorRepository.findByBirthYear(birthYear);
    }
    
    // Get authors born after a specific year
    @Transactional(readOnly = true)
    public List<Author> getAuthorsAfterYear(Integer year) {
        log.info("Fetching authors born after year: {}", year);
        return authorRepository.findByBirthYearGreaterThan(year);
    }
    
    // Update author
    public Author updateAuthor(Long id, Author authorDetails) {
        log.info("Updating author with ID: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        
        author.setName(authorDetails.getName());
        author.setBirthYear(authorDetails.getBirthYear());
        author.setNationality(authorDetails.getNationality());
        
        return authorRepository.save(author);
    }
    
    // Delete author by ID
    public void deleteAuthor(Long id) {
        log.info("Deleting author with ID: {}", id);
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }


}
