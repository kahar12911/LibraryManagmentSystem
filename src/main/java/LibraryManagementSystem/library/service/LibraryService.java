package LibraryManagementSystem.library.service;

import LibraryManagementSystem.library.model.Book;
import LibraryManagementSystem.library.model.BookCopy;
import LibraryManagementSystem.library.model.User;
import LibraryManagementSystem.library.repository.BookCopyRepository;
import LibraryManagementSystem.library.repository.BookRepository;
import LibraryManagementSystem.library.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public BookCopy addBookCopy(Book book, int rackNumber) {
        BookCopy copy = BookCopy.builder()
                .book(book)
                .rackNumber(rackNumber)
                .isBorrowed(false)
                .build();
        return bookCopyRepository.save(copy);
    }


    public User addUser(User user){
        return userRepository.save(user);
    }

    public String borrowBook(Integer userID, Integer bookID) {
        Optional<Book> book = bookRepository.findById(bookID);
        User user = userRepository.findById(userID).orElseThrow();

        System.out.println("User: " + user.getName());
        System.out.println("Borrowed books: " + user.getBorrowedBooks().size());

        List<BookCopy> availableCopies = bookCopyRepository.findByBookBookIDAndIsBorrowedFalse(bookID);
        System.out.println("Available Copies: " + availableCopies.size());

        if (!availableCopies.isEmpty()) {
            BookCopy copy = availableCopies.get(0);
            copy.setBorrowed(true);
            copy.setUser(user);
            bookCopyRepository.save(copy);

            user.getBorrowedBooks().add(copy);
            userRepository.save(user);

            System.out.println("Book borrowed successfully!");
            return "Book borrowed successfully";
        }
        return "Can't borrow Books";
    }




    public String returnBook(Integer copyId){
        BookCopy copy = bookCopyRepository.findById(copyId).orElseThrow();
        copy.setBorrowed(false);
        bookCopyRepository.save(copy);
        return "Book returned Successfully";
    }

    public List<Book> searchBook(String query){
        return bookRepository.findAll().stream().filter(b -> b.getTitle().contains(query) | b.getAuthors().contains(query) | b.getPublishers().contains(query)).toList();
    }

}
