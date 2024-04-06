package spring.reader.service_reader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.reader.model_reader.Issue;
import spring.reader.model_reader.Reader;
import spring.reader.repository_reader.ReaderRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueProvider issueProvider;
    public Reader findById(Long id){
        return readerRepository.findById(id).orElse(null);
    }
    public void addNewReader(String name){
        readerRepository.save(new Reader(name));
    }
    public void deleteById(Long id){
        readerRepository.deleteById(id);
    }
    public List<Reader> getAll(){
        return  readerRepository.findAll();
    }
    public Reader findByName(String name){
        return readerRepository.findByName(name);
    }

    public List<Issue> findIssue(Long id) {
        return issueProvider.findIssueAllByIdReader(id);
    }
}
