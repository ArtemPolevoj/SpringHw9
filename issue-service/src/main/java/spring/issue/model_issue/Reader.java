package spring.issue.model_issue;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Reader {
    private Long id;
    private  String name;

    public Reader(String name) {
        this.name = name;
    }
}
