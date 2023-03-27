package CrudOperations.LibraryManagement.Books.Service;
import CrudOperations.LibraryManagement.Books.Bean.Book;
import CrudOperations.LibraryManagement.Books.Bean.Book_track;
import CrudOperations.LibraryManagement.Books.Repository.BookRepository;
import CrudOperations.LibraryManagement.Books.Repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class BookService {

    BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TrackRepository trackRepository;

    public String getBooks(Book book)
    {

        bookRepository.save(book);
        return "Book created in database";
    }

    public String updateBookDetails(@PathVariable long Isbn, @RequestBody Book book)
    {
        Optional<Book> Book1 = bookRepository.findById(Isbn);

        if (Book1.isPresent())
        {

            Book existbook = Book1.get();
            int PriceInitial = book.getInitialPrice();
            existbook.setFinalPrice(PriceInitial);


            //List<Integer> priceUpdates = book.getPrice();

            Book_track table = new Book_track();
            table.setPriceUpdates(PriceInitial);
            table.setId(Isbn);

            trackRepository.save(table);
            bookRepository.save(existbook);

            return "Book details updated";


        }
        else
        {
            return "Book details for Isbn " + Isbn + " do not exist";
        }

    }


    public String deletingBook(@PathVariable long Isbn) {

        Optional<Book> Book1 = bookRepository.findById(Isbn);

        if (Book1.isPresent()) {
            bookRepository.deleteById(Isbn);
            return "Book with Isbn " + Isbn + " deleted";
        } else {
            return "Invalid Isbn " + Isbn;

        }
    }

}





