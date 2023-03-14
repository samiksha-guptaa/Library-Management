package CrudOperations.LibraryManagement.Books.Controller;

import CrudOperations.LibraryManagement.Books.Bean.Book;
import CrudOperations.LibraryManagement.Books.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;


//http://localhost:8080/books

@RestController
public class BookController
{

    @Autowired
    private BookRepository repository;


    //Get all the books
   @GetMapping("/books")
   public List<Book> getAllBooks() {

      return repository.findAll();


      }


    //if(Book.isEmpty())
    //{
    //throw new RuntimeException("Books are not available. ");
    //  }

    //return Arrays.asList(new Book(978088878, "Atomic Habits", "James Clear", 390));



    /*
    @GetMapping("/books/1")
    public List<Book> getBookDetails() {
        return Arrays.asList(new Book(978088878, "Atomic Habits", "James Clear", 390));

    }*/


    //Get book details with Isbn
    @GetMapping("/books/{Isbn}")
    public ResponseEntity<Book> getBookById(@PathVariable long Isbn)
    {    Optional<Book> book = repository.findById(Isbn);
        if (book.isPresent())
        {
            return new ResponseEntity<Book>(book.get(), HttpStatus.FOUND);
        }
    else

    {        return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    }

/*
    @GetMapping("/books/{Isbn}")
    public Book getBookDetails(@PathVariable long Isbn) {

        Optional<Book> book = repository.findById(Isbn);

        if(book.isEmpty())
        {
            throw new RuntimeException(" Given book details are not available. " + Isbn);
            return ("The book with Isbn" + Isbn + "is not available");

        }

        return book.get();
    }

*/


    //POST - Create a new book

    @PostMapping("/books")
    public String createBook(@RequestBody Book book)
    {
        repository.save(book);
        return "Book created in database";

    }





   /* @PutMapping("/books/{Isbn}")
    public String updateBookById(@PathVariable long Isbn, @RequestBody Book book)
    {
        if(book.)

        repository.save(book);
        return "Book details updated";
    }

    */

    //Update Book by Isbn

    //PUT - Update/Replace a specific book
@PutMapping("/books/{Isbn}")
    public String updateBookById(@PathVariable long Isbn, @RequestBody Book book)
    {
        Optional <Book> Book1 = repository.findById(Isbn);
        if(Book1.isPresent()) {

            //Book existbook = Book1.get();
           // existbook.setName(book.getName());
           // existbook.setAuthor(book.getAuthor());
           // existbook.setPrice(book.getPrice());
            repository.save(book);
            return "Book details updated";

        }

        else
        {
        return "Book details for Isbn" + Isbn + "do not exist";

        }

    }


    //DELETE - Delete a book
    @DeleteMapping("/books/{Isbn}")
    public String deleteBook(@PathVariable long Isbn)
    {
        repository.deleteById(Isbn);
        return "Book with Isbn " + Isbn + " deleted";
    }


    //@GetMapping("/book/history")









}
