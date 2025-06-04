package tn.esprit.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error response model")
public class ErrorResponse {
    
    @Schema(description = "Timestamp when the error occurred", example = "2023-12-01T10:15:30")
    private LocalDateTime timestamp;
    
    @Schema(description = "HTTP status code", example = "404")
    private int status;
    
    @Schema(description = "Error type", example = "Not Found")
    private String error;
    
    @Schema(description = "Error message", example = "Author not found with id: 1")
    private String message;
    
    @Schema(description = "Request path where error occurred", example = "/api/authors/1")
    private String path;
}
