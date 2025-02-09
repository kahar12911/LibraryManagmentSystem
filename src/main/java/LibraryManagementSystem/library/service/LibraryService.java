package LibraryManagementSystem.library.service;

import LibraryManagementSystem.library.model.Book;
import LibraryManagementSystem.library.model.BookCopy;
import LibraryManagementSystem.library.repository.BookCopyRepository;
import LibraryManagementSystem.library.repository.BookRepository;
import LibraryManagementSystem.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private UserRepository userRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public BookCopy addBookCopy(Book book, int rackNumber){
        BookCopy copy = BookCopy.builder().book(book).rackNumber(rackNumber).isBorrowed(false).build();
        return bookCopyRepository.save(copy);
    }

}
