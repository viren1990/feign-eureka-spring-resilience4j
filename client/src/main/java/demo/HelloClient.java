package demo;

import feign.RequestLine;

public interface HelloClient {

    @RequestLine("GET /service-instance/HelloServer")
    String hello();

    @RequestLine("GET /service-instance/resetCounter")
    void resetServiceCounter();
}
