package tn.esprit.author.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.author.entity.Author;
import tn.esprit.author.service.AuthorService;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final AuthorService authorService;
    
    @Override
    public void run(String... args) {
        log.info("Initializing sample author data...");
        
        // Check if data already exists
        if (authorService.getAllAuthors().isEmpty()) {
            // Create sample authors
            Author author1 = new Author();
            author1.setName("J.K. Rowling");
            author1.setBirthYear(1965);
            author1.setNationality("British");
            
            Author author2 = new Author();
            author2.setName("George Orwell");
            author2.setBirthYear(1903);
            author2.setNationality("British");
            
            Author author3 = new Author();
            author3.setName("Harper Lee");
            author3.setBirthYear(1926);
            author3.setNationality("American");
            
            // Save authors
            authorService.saveAuthor(author1);
            authorService.saveAuthor(author2);
            authorService.saveAuthor(author3);
            
            log.info("Sample author data initialized successfully!");
        } else {
            log.info("Author data already exists, skipping initialization.");
        }
    }
}
