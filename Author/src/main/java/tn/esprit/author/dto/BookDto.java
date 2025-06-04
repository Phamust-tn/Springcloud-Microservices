package tn.esprit.author.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;

    private String title;

    private String isbn;

    private Integer publicationYear;

    private String genre;

    private Long authorId;

}