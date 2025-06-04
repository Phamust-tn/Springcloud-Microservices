package tn.esprit.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.book.entity.Book;
import tn.esprit.book.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {
    
    private final BookRepository bookRepository;
      // Create a new book (ID should be null)
    public Book createBook(Book book) {
        log.info("Creating new book: {}", book.getTitle());
        // Ensure ID is null for new entities
        book.setId(null);
        return bookRepository.save(book);
    }
    
    // Save book (for internal use, like data initialization)
    public Book saveBook(Book book) {
        log.info("Saving book: {}", book.getTitle());
        return bookRepository.save(book);
    }
    
    // Get all books
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll();
    }
    
    // Get book by ID
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        log.info("Fetching book with ID: {}", id);
        return bookRepository.findById(id);
    }
    
    // Get books by author ID
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthorId(Long authorId) {
        log.info("Fetching books for author with ID: {}", authorId);
        return bookRepository.findByAuthorId(authorId);
    }
    
    // Search books by title
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String title) {
        log.info("Searching books by title: {}", title);
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Get books by publication year
    @Transactional(readOnly = true)
    public List<Book> getBooksByPublicationYear(Integer publicationYear) {
        log.info("Fetching books by publication year: {}", publicationYear);
        return bookRepository.findByPublicationYear(publicationYear);
    }
    
    // Get books by genre
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre) {
        log.info("Fetching books by genre: {}", genre);
        return bookRepository.findByGenre(genre);
    }
    
    // Get book by ISBN
    @Transactional(readOnly = true)
    public Book getBookByIsbn(String isbn) {
        log.info("Fetching book by ISBN: {}", isbn);
        return bookRepository.findByIsbn(isbn);
    }
    
    // Update book
    public Book updateBook(Long id, Book bookDetails) {
        log.info("Updating book with ID: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setGenre(bookDetails.getGenre());
        book.setAuthorId(bookDetails.getAuthorId());
        
        return bookRepository.save(book);
    }
    
    // Delete book by ID
    public void deleteBook(Long id) {
        log.info("Deleting book with ID: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
