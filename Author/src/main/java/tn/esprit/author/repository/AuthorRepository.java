package tn.esprit.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.author.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    // Find authors by name (case-insensitive)
    List<Author> findByNameContainingIgnoreCase(String name);
    
    // Find authors by nationality
    List<Author> findByNationality(String nationality);
    
    // Find authors by birth year
    List<Author> findByBirthYear(Integer birthYear);
      // Find authors born after a specific year
    List<Author> findByBirthYearGreaterThan(Integer year);
    
    // Find authors by nationality and birth year range
    @Query("SELECT a FROM Author a WHERE a.nationality = :nationality AND a.birthYear BETWEEN :startYear AND :endYear")
    List<Author> findByNationalityAndBirthYearBetween(@Param("nationality") String nationality, 
                                                     @Param("startYear") Integer startYear, 
                                                     @Param("endYear") Integer endYear);
}
