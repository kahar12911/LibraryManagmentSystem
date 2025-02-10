package LibraryManagementSystem.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer copyID;

    @ManyToOne
    @JsonIgnoreProperties("borrowedBooks")  // Prevent circular reference
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("bookCopies")     // Prevent circular reference
    private Book book;



    private Integer rackNumber;
    private boolean isBorrowed;
}
