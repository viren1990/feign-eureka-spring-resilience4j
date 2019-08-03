package demo;

import feign.RequestLine;

public interface HelloClient {
    @RequestLine("GET /service-instance/HelloServer")
    String hello();
}
