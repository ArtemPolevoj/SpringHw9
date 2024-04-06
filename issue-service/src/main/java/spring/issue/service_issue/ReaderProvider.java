package spring.issue.service_issue;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import spring.issue.model_issue.Reader;

@Component
public class ReaderProvider {
    private final WebClient webClient;
    public ReaderProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public Reader findById(Long readerId) {
        return webClient.get()
                .uri("http://reader-service/reader/{id}",readerId)
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
    }
}
