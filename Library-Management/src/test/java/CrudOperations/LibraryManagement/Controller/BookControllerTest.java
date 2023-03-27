package CrudOperations.LibraryManagement.Controller;


import CrudOperations.LibraryManagement.Books.Bean.Book;
import CrudOperations.LibraryManagement.Books.Bean.Book_track;
import CrudOperations.LibraryManagement.Books.Controller.BookController;
import CrudOperations.LibraryManagement.Books.Repository.BookRepository;
import CrudOperations.LibraryManagement.Books.Repository.TrackRepository;
import CrudOperations.LibraryManagement.Books.Service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    BookController bookController;

    @MockBean
    BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private TrackRepository trackRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /*
        public void getBooksTest()
        {
            Book book = new Book();
            book.setName("Deep Work");
            book.setAuthor("Cal Newport");
            book.setInitialPrice(234);
            when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);
            String response = bookService.getBooks(book);
            Assert.assertEquals("Book created in database", response);
            verify(bookRepository).save(ArgumentMatchers.any(Book.class));
        }



     */
    @Test
    public void getBooksTest() throws Exception {
        List<Book> expectedBooks = new ArrayList<>(
                Arrays.asList(new Book("Ikigai", "Francesc Maralles", 259),
                        new Book("Think and Grow Rich", "Napoleon Hill", 400),
                        new Book("Atomic Habits", "James Clear", 390),
                        new Book("Power of Now", "Eckhart Tolle", 258)));

        when(bookRepository.findAll()).thenReturn(expectedBooks);
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedBooks.size()))
                .andDo(print());


    }


    @Test
    public void createBookTest() throws Exception {
        Book book = new Book("Deep Work", "Cal Newport", 234);
        mockMvc.perform((RequestBuilder) post("http://localhost:8080/books").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    public void deleteByIdTest() throws Exception {
        long Isbn = 9708878L;

        doNothing().when(bookRepository).deleteById(Isbn);
        mockMvc.perform(delete("http://localhost:8080/books/{Isbn}", Isbn))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void getBookByIdReturnsBookTest() {
        // Arrange
        long Isbn = 9708876;
        Book book = new Book(9708876L, "Atomic Habits", "James Clear", 390);
        when(bookRepository.findById(Isbn)).thenReturn(Optional.of(book));

        ResponseEntity<Book> response = bookController.getBookById(Isbn);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testGetBookById_ReturnsNotFound() {

        long Isbn = 12345;
        when(bookRepository.findById(Isbn)).thenReturn(Optional.empty());

        ResponseEntity<Book> response = bookController.getBookById(Isbn);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }


    @Test
    public void testGetBookById() throws Exception
    {
        long Isbn = 9708876L;
        Book book = new Book(9708876L,"Atomic Habits", "James Clear", 390);

        when(bookRepository.findById(Isbn)).thenReturn(Optional.of(book));

        mockMvc.perform(get("http://localhost:8080/books/{Isbn}", Isbn))

                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Isbn", is(9708876L)))
                .andExpect(jsonPath("$.Name", is("Atomic Habits")))
                .andExpect(jsonPath("$.Author", is("James Clear")))
                .andExpect(jsonPath("$.InitialPrice", is(390)))
                .andDo(print());

    }


    @Test
    public void testGetBookByIdNotFound() throws Exception {
        long Isbn = 1234567890L;

        when(bookRepository.findById(Isbn)).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/{Isbn}", Isbn))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getBooksHistoryTest() throws Exception {

        List<Book_track> bookExpected = new ArrayList<>(
                Arrays.asList(new Book_track(1l, 234,
                LocalDateTime.of(LocalDate.of(2023, 03, 21), LocalTime.of(17, 45)))));

        when(bookController.getBookHistory()).thenReturn(bookExpected);

        mockMvc.perform(get("http://localhost:8080/books/history")
                       .accept(MediaType.APPLICATION_JSON))
               // .andExpect(MockMvcResultMatchers.status().isOk())
              // .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(bookExpected.size()))
             //   .andDo(print());
                 .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(bookExpected.size()))
                .andDo(print());


    }


    @Test
    public void updateBookByIdTest() throws Exception{
        long Isbn = 1L;

        Book book = new Book("Atomic Habits", "James Clear", 263);
        Book updatedbook = new Book("Atomic Habits", "James Clear", 305);


        when(bookRepository.findById(Isbn)).thenReturn(Optional.of(book));
        when(bookService.updateBookDetails(1L, updatedbook)).thenReturn("Book details updated");


        mockMvc.perform(put("http://localhost:8080/books/{Isbn}", Isbn)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedbook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Name", is("Atomic Habits")))
                .andExpect(jsonPath("$.Author", is("James Clear")))
                .andExpect(jsonPath("$.InitialPrice", is(305)))
                .andDo(print());
    }





}



       /* List<Book_track> expectedBooks = new ArrayList<>(
                Arrays.asList(new Book_track(1L, 234),
                        new Book_track(2L, 217),
                        new Book_track(3L, 290)));

        when(trackRepository.findAll()).thenReturn(expectedBooks);
        mockMvc.perform(get("/books/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedBooks.size()))
                .andDo(print());

        List<Book_track> expectedBooks = new ArrayList<>(
                Arrays.asList(new Book_track(1L, 234),
                        new Book_track(2L, 217),
                        new Book_track(3L, 290)));


*/











