package tn.esprit.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "publication_year")
    private Integer publicationYear;
    
    @Column(name = "genre")
    private String genre;
    
    @Column(name = "author_id", nullable = false)
    private Long authorId;
}
