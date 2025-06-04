package tn.esprit.author.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Author entity representing an author in the library system")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the author", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    @Schema(description = "Full name of the author", example = "J.K. Rowling", required = true)
    private String name;
    
    @Column(name = "birth_year")
    @Schema(description = "Year the author was born", example = "1965")
    private Integer birthYear;
    
    @Column(name = "nationality")
    @Schema(description = "Nationality of the author", example = "British")
    private String nationality;
}
