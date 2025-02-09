package LibraryManagementSystem.library.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer rackNumber;
    Book bookId;
}
