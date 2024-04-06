package spring.issue.controller_issue;

import lombok.Data;

@Data
public class IssueRequest {
    private Long idReader;
    private Long bookId;
}
