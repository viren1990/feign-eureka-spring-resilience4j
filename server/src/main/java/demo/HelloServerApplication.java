package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Viren
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class HelloServerApplication {
    @Autowired
    DiscoveryClient client;

    private LongAdder adder = new LongAdder();

    @RequestMapping(value = "/service-instance/{applicationName}", method = GET)
    public String hello(@PathVariable(value = "applicationName") String applicationName) {
        adder.increment();

        final int value = adder.intValue();
        if (IntStream.rangeClosed(11, 12).anyMatch(e -> e == value)) {
            throw new RuntimeException("Purposely broken");
        }

        final ServiceInstance localInstance = client.getInstances(applicationName).iterator().next();
        return "Hello World: " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":" + localInstance.getPort();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
    }
}
