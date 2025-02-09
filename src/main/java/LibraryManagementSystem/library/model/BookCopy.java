package LibraryManagementSystem.library.model;

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
    private Book book;

    private Integer rackNumber;
    private boolean isBorrowed;
}
