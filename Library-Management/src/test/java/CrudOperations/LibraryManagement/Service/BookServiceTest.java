package CrudOperations.LibraryManagement.Service;


import CrudOperations.LibraryManagement.Books.Bean.Book;
import CrudOperations.LibraryManagement.Books.Repository.BookRepository;
import CrudOperations.LibraryManagement.Books.Repository.TrackRepository;
import CrudOperations.LibraryManagement.Books.Service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    TrackRepository trackRepository;

    @Test

    public void getBooksTest()
    {
        Book book = new Book();
        book.setName("Deep Work");
        book.setAuthor("Cal Newport");
        book.setInitialPrice(234);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        String response = bookService.getBooks(book);
        assertEquals("Book created in database", response);
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void updateBookDetailsTest() {

        Book book =new Book();
        book.setIsbn(1L);
        book.setInitialPrice(145);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        String result= bookService.updateBookDetails(1, book);
        assertEquals("Book details updated",result);
        verify(bookRepository).save(book);

    }

    @Test
    public void updateBookDetailsFailureTest()
    {

        Book book = new Book();
        book.setIsbn(1L);
        book.setInitialPrice(145);
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        String respond =bookService.updateBookDetails(2L,book);
        assertEquals("Book details for Isbn 2 do not exist",respond);
        verify(bookRepository, Mockito.never()).save(any());
    }

    @Test
    public void deletingBookValidIsbnTest(){

        Book book = new Book();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // When
        String result = bookService.deletingBook(1);

        // Then
        assertEquals("Book with Isbn 1 deleted", result);
        verify(bookRepository, times(1)).deleteById(1L);



    }


    @Test
    public void deletingBookInvalidIsbnTest()
    {

        Book book = new Book();
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        // When
        String result = bookService.deletingBook(2);

        // Then
        assertEquals("Invalid Isbn 2", result);
        verify(bookRepository, never()).deleteById(2L);
    }

    }


















