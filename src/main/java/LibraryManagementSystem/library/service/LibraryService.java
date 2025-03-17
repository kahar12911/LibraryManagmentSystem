package LibraryManagementSystem.library.service;

import LibraryManagementSystem.library.exception.CustomException;
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
        try{
            return userRepository.findAll();
        }catch (Exception e){
            throw new CustomException("Failed to retrive Users",e);
        }
    }

    public Book addBook(Book book){
        try{
            return bookRepository.save(book);
        }catch (Exception e){
            throw new CustomException("Failed to Add Book",e);
        }

    }

    public BookCopy addBookCopy(Book book, int rackNumber) {
        try{
            BookCopy copy = BookCopy.builder()
                    .book(book)
                    .rackNumber(rackNumber)
                    .isBorrowed(false)
                    .build();
            return bookCopyRepository.save(copy);
        }catch (Exception e){
            throw new CustomException("Failed to add Book Copy",e);
        }
    }


    public User addUser(User user){
        try{
            return userRepository.save(user);
        }catch (Exception e){
            throw new CustomException("Failed to Add User",e);
        }
    }

    public String borrowBook(Integer userID, Integer bookID) {
        try{
            Optional<Book> book = bookRepository.findById(bookID);
            User user = userRepository.findById(userID).orElseThrow();

            System.out.println("User: " + user.getName());
            System.out.println("Borrowed books: " + user.getBorrowedBooks().size());

            List<BookCopy> availableCopies = bookCopyRepository.findByBookBookIDAndIsBorrowedFalse(bookID);
            System.out.println("Available Copies: " + availableCopies.size());

            if(user.getBorrowedBooks().size() > 5){
                if (!availableCopies.isEmpty()) {
                    BookCopy copy = availableCopies.get(0);
                    copy.setBorrowed(true);
                    copy.setUser(user);
                    bookCopyRepository.save(copy);

                    user.getBorrowedBooks().add(copy);
                    userRepository.save(user);

                    System.out.println("Book borrowed successfully!");
                    return "Book borrowed successfully";
                }else{
                    return "No Available Copy of this Book";
                }

            }else{
                return "User has already borrowed 5 books";
            }
        }catch (Exception e){
            throw new CustomException("Failed to Borrow Book",e);
        }
    }

    public String returnBook(Integer copyId){
        try{
            BookCopy copy = bookCopyRepository.findById(copyId).orElseThrow();
            copy.setBorrowed(false);
            bookCopyRepository.save(copy);
            return "Book returned Successfully";
        }catch (Exception e){
            throw new CustomException("Failed to return Book",e);
        }
    }

    public List<Book> searchBook(String query){
        try{
            return bookRepository.findAll().stream().filter(
                    b -> b.getTitle().contains(query) |
                            b.getAuthors().contains(query) |
                            b.getPublishers().contains(query))
                    .toList();
        }catch (Exception e){
            throw new CustomException("Failed to search Book",e);
        }

    }

}
