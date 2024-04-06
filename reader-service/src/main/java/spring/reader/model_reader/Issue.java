package spring.reader.model_reader;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class Issue {
    private Long id;
    private Long idReader;
    private Long idBook;
    private LocalDateTime issuedAt;
    private LocalDateTime returnedAt;

    public Issue(Long idReader, Long idBook){
        this.idBook = idBook;
        this.idReader = idReader;
        issuedAt = LocalDateTime.now();
        returnedAt = null;
    }
}
