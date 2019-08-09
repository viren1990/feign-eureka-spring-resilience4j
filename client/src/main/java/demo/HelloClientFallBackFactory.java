package demo;

import feign.FeignException;
import org.apache.commons.lang.exception.ExceptionUtils;

public class HelloClientFallBackFactory implements HelloClient {

    private Exception cause;

    public HelloClientFallBackFactory(final Exception cause){
        this.cause = cause;
    }

    @Override
    public String hello() {

        System.out.println(ExceptionUtils.getMessage(cause));

        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : cause.getMessage();
        System.out.println(httpStatus);
        return "status received is " + httpStatus;
    }

    @Override
    public void resetServiceCounter() {
        System.out.println("Reset couldn't be done");
    }
}