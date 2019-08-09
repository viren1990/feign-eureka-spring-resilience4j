package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Viren
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class HelloClientApplication implements ApplicationRunner {

    @Autowired
    private HelloClient client;


    @RequestMapping("/")
    public String hello() {
        return client.hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        client.resetServiceCounter();
    }
}


