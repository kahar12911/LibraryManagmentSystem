package LibraryManagementSystem.library.controller;

import LibraryManagementSystem.library.exception.CustomException;
import LibraryManagementSystem.library.model.Book;
import LibraryManagementSystem.library.model.BookCopy;
import LibraryManagementSystem.library.model.User;
import LibraryManagementSystem.library.repository.BookRepository;
import LibraryManagementSystem.library.service.LibraryService;
import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/bookcopy")
    public BookCopy addBookCopy(@RequestParam Integer bookId, @RequestParam Integer rackNumber) {
        try{
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
            return libraryService.addBookCopy(book, rackNumber);
        }catch (Exception e){
            throw new CustomException("Failed to add BookCopy");
        }
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        try{
            return libraryService.getAllUsers();
        }catch (Exception e){
            throw new CustomException("Failed to fetach Users",e);
        }
    }


    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){
        try{
            return libraryService.addBook(book);
        }catch (Exception e){
            throw new CustomException("Failed to fetch Book",e);
        }

    }
    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        try {
            return libraryService.addUser(user);
        }catch (Exception e){
            throw new CustomException("Failed to register User",e);
        }
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Integer userId,@RequestParam Integer bookId){
        try{
            return libraryService.borrowBook(userId,bookId);
        }catch (Exception e){
            throw new CustomException("Failed to borrow Book",e);
        }

    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Integer bookId){
        try{
            return libraryService.returnBook(bookId);
    }catch (Exception e){
            throw new CustomException("Failed to return Book",e);
        }
    }

    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam String query){
        try{
            return libraryService.searchBook(query);
        }catch (Exception e){
            throw new CustomException("Failed to search Book",e);
        }
    }

}
