package demo;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class HelloClientConfiguration {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    private static final String SERVER_SERVICE_ID = "HelloServer";
    private static final String DEFAULT_CIRCUIT_BREAKER = "default";


    @Bean
    public HelloClient client() {

        final URI uri = discoveryClient.getInstances(SERVER_SERVICE_ID).iterator().next().getUri();

        final CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(DEFAULT_CIRCUIT_BREAKER);
        final FeignDecorators decorators = FeignDecorators.builder()
                .withCircuitBreaker(circuitBreaker)
                .withFallbackFactory(HelloClientFallBackFactory::new)
                .build();
        return Resilience4jFeign.builder(decorators).target(HelloClient.class, uri.toString());
    }
}
