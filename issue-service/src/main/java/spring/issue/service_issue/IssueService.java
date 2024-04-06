package spring.issue.service_issue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.issue.controller_issue.IssueRequest;
import spring.issue.model_issue.Issue;
import spring.issue.repository_issue.IssueRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    @Value("${application.issue.max-allowed-books:1}")
    private int maxBook;
    private final BookProvider bookProvider;
    private final IssueRepository issueRepository;
    private final ReaderProvider readerProvider;

    public Issue createIssue(IssueRequest request) {
        if (bookProvider.findById(request.getBookId()) == null) {
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerProvider.findById(request.getIdReader()) == null) {
            log.info("Не удалось найти читателя с id " + request.getIdReader());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getIdReader());
        }

        if (issueRepository.findCountBooksByIdReader(request.getIdReader()) >= maxBook) {
            log.info("У читателя с id " + request.getIdReader() + " максимальное количество книг на руках.");
            return new Issue(-1L, -1L);
        }

        Issue issue = new Issue(request.getIdReader(), request.getBookId());
        issueRepository.save(issue);
        return issue;
    }

    public TreeMap<String, String> findById(Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TreeMap<String, String> map = new TreeMap<>();
        Issue issue = issueRepository.findById(id).orElse(null);
        Long idReader = issue.getIdReader();
        Long idBook = issue.getIdBook();
        String nameReader = readerProvider.findById(idReader).getName();
        String nameBook = bookProvider.findById(idBook).getName();
        String issuedAt = issue.getIssuedAt().format(formatter);
        String returnedAt = "-";
        if (issue.getReturnedAt() != null) {
            returnedAt = issue.getReturnedAt().format(formatter);
        }
        map.put("Reader", nameReader);
        map.put("Book", nameBook);
        map.put("issued_at", issuedAt);
        map.put("returned_at", returnedAt);
        return map;
    }

    public void setReturnedAt(Long id) {
        issueRepository.setReturnedAt(id);
    }

    public List<Issue> findIssueAllByIdReader(Long readerId){
        return issueRepository.findIssueAllByIdReader(readerId);
    }

}
