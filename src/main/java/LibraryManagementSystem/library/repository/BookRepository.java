package LibraryManagementSystem.library.repository;

import LibraryManagementSystem.library.exception.CustomException;
import LibraryManagementSystem.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
