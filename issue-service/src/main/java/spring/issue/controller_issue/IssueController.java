package spring.issue.controller_issue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.issue.model_issue.Issue;
import spring.issue.service_issue.IssueService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService service;

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
        try {
            Issue issue = service.createIssue(issueRequest);
            if (issue.getIdReader() != -1L && issue.getIdBook() != -1L) {
                return ResponseEntity.status(HttpStatus.CREATED).body(issue);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TreeMap<String, String>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{issueId}")
    public ResponseEntity<TreeMap<String, String>> returnedAt(@PathVariable Long issueId) {
        try {
            service.setReturnedAt(issueId);
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(issueId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("reader/{id}")
    public ResponseEntity<List<Issue>> findIssueAllByIdReader(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findIssueAllByIdReader(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
