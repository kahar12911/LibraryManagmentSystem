package LibraryManagementSystem.library.controller;

import LibraryManagementSystem.library.model.Book;
import LibraryManagementSystem.library.model.BookCopy;
import LibraryManagementSystem.library.model.User;
import LibraryManagementSystem.library.repository.BookRepository;
import LibraryManagementSystem.library.service.LibraryService;
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
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
        return libraryService.addBookCopy(book, rackNumber);
    }



    @GetMapping("/user")
    public List<User> getAllUsers() {
        return libraryService.getAllUsers();
    }


    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }
    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return libraryService.addUser(user);
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Integer userId,@RequestParam Integer bookId){
        return libraryService.borrowBook(userId,bookId);
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Integer bookId){
        return libraryService.returnBook(bookId);
    }

    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam String query){
        return libraryService.searchBook(query);
    }

}
