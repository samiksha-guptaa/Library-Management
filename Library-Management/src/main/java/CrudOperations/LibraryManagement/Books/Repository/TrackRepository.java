package CrudOperations.LibraryManagement.Books.Repository;

import CrudOperations.LibraryManagement.Books.Bean.Book_track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;



@Repository
public interface TrackRepository extends JpaRepository <Book_track, Integer>
{



    //@Query(value = "SELECT BOOK_TRACK.SERIAL, BOOK.ISBN, BOOK.INITIAL_PRICE, BOOK.FINAL_PRICE, BOOK.UPDATED_AT, BOOK_TRACK.PRICE_UPDATES\n" +
   //         "FROM BOOK\n" +
    //        "LEFT JOIN BOOK_TRACK ON BOOK.ISBN = BOOK_TRACK.ID;\n");


}
//public List<>Book_track;





