package CrudOperations.LibraryManagement.Books.Repository;

import CrudOperations.LibraryManagement.Books.Bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository <Book, Long>
{

   @Query(value = "SELECT Book_track.Serial, Book.Isbn, Book.Name, Book.InitialPrice, Book.FinalPrice, Book.updatedAt, Book.PriceUpdates FROM Book LEFT JOIN Book_track ON Book.Isbn = Book_track.Id;", nativeQuery = true)
   List<Book> findBookHistory(@Param("Isbn")Long Isbn);

}
