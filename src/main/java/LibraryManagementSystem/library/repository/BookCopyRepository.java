package LibraryManagementSystem.library.repository;

import LibraryManagementSystem.library.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy,Integer> {
    List<BookCopy> findByBookBookIDAndIsBorrowedFalse(Integer bookId);
}
