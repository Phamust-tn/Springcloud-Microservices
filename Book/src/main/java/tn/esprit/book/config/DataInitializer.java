package tn.esprit.book.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.book.entity.Book;
import tn.esprit.book.service.BookService;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final BookService bookService;
    
    @Override
    public void run(String... args) {
        log.info("Initializing sample book data...");
        
        // Check if data already exists
        if (bookService.getAllBooks().isEmpty()) {
            // Create sample books
            Book book1 = new Book();
            book1.setTitle("Harry Potter and the Philosopher's Stone");
            book1.setIsbn("978-0-7475-3269-9");
            book1.setPublicationYear(1997);
            book1.setGenre("Fantasy");
            book1.setAuthorId(1L); // J.K. Rowling
            
            Book book2 = new Book();
            book2.setTitle("1984");
            book2.setIsbn("978-0-452-28423-4");
            book2.setPublicationYear(1949);
            book2.setGenre("Dystopian Fiction");
            book2.setAuthorId(2L); // George Orwell
            
            Book book3 = new Book();
            book3.setTitle("To Kill a Mockingbird");
            book3.setIsbn("978-0-06-112008-4");
            book3.setPublicationYear(1960);
            book3.setGenre("Southern Gothic");
            book3.setAuthorId(3L); // Harper Lee
            
            // Save books
            bookService.saveBook(book1);
            bookService.saveBook(book2);
            bookService.saveBook(book3);
            
            log.info("Sample book data initialized successfully!");
        } else {
            log.info("Book data already exists, skipping initialization.");
        }
    }
}
