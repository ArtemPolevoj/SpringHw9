package spring.book.repository_book;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.book.model_book.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
}
