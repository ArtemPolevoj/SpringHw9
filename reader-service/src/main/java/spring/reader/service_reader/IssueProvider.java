package spring.reader.service_reader;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import spring.reader.model_reader.Issue;

import java.util.List;

@Component
public class IssueProvider {
    private final WebClient webClient;
    public IssueProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public List<Issue> findIssueAllByIdReader(Long readerId) {
        return webClient.get()
                .uri("http://issue-service/issue/reader/{id}", readerId)
                .retrieve()
                .bodyToFlux(Issue.class)
                .collectList()
                .block();
      }
}
