package spring.reader.repository_reader;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.reader.model_reader.Reader;


public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByName(String name);
}
