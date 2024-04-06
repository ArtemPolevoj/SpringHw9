package spring.issue.model_issue;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
