package CrudOperations.LibraryManagement.Books.Repository;

import CrudOperations.LibraryManagement.Books.Bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long>
{


}
