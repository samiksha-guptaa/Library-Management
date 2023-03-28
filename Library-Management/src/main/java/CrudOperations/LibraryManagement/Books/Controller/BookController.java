package CrudOperations.LibraryManagement.Books.Controller;

import CrudOperations.LibraryManagement.Books.Bean.Book;
import CrudOperations.LibraryManagement.Books.Bean.Book_track;
import CrudOperations.LibraryManagement.Books.Repository.BookRepository;
import CrudOperations.LibraryManagement.Books.Repository.TrackRepository;
//import CrudOperations.LibraryManagement.Books.Service.BookService;
import CrudOperations.LibraryManagement.Books.Service.BookService;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import org.hibernate.cfg.IndexColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


//http://localhost:8080/books

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TrackRepository trackRepository;


    //Get all the books
    @GetMapping("/books")
    public List<Book> getAllBooks() {

        return bookRepository.findAll();


    }


    //Get book details with Isbn
    @GetMapping("/books/{Isbn}")
    public ResponseEntity<Book> getBookById(@PathVariable long Isbn) {
        Optional<Book> book = bookRepository.findById(Isbn);
        if (book.isPresent()) {
            return new ResponseEntity<Book>(book.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);


            //return new ResponseEntity.HttpStatus.NOT_FOUND.body("Books are not available.");
            // return ResponseEntity.noContent().build();
        }


    }


    @PostMapping("/books")
    public String createBook(@RequestBody Book book) {

        bookRepository.save(book);
        //return "Book created in database";
        return bookService.getBooks(book);

    }


    @PutMapping("/books/{Isbn}")


    public String updateBookById(@PathVariable long Isbn, @RequestBody Book book) {
        Optional<Book> Book1 = bookRepository.findById(Isbn);

        return bookService.updateBookDetails(Isbn, book);


    }

    @DeleteMapping("/books/{Isbn}")
    public String deleteBook(@PathVariable long Isbn) {
        Optional<Book> Book1 = bookRepository.findById(Isbn);

        return bookService.deletingBook(Isbn);
    }


    @GetMapping("books/history")


    public List<Book_track> getBookHistory() {

        return trackRepository.findAll();


    }
}




















