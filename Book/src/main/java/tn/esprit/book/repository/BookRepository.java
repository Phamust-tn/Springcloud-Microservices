package tn.esprit.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.book.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by title (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Find books by author ID
    List<Book> findByAuthorId(Long authorId);
    
    // Find books by publication year
    List<Book> findByPublicationYear(Integer publicationYear);
    
    // Find books by genre
    List<Book> findByGenre(String genre);
    
    // Find books by ISBN
    Book findByIsbn(String isbn);
}
