package mockserver;


import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import java.util.concurrent.TimeUnit;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class TestMockServer {

    private ClientAndServer mockServer;

    @Test
    public void createExpectationForHello() {
        new MockServerClient("localhost", 9090)
                .when(
                        request()
                                .withMethod("PUT")
                                .withPath("/hello")
                )
                .respond(
                        response()
                                .withStatusCode(401)
                                .withBody("{ message: 'incorrect username and password combination' }")
                                .withDelay(TimeUnit.SECONDS,1)
                );
    }

    /*@Test
    public void createExpectationToDo() {
        new MockServerClient("localhost", 9090)
                .when(
                request()
                        .withMethod("PUT")
                        .withPath("/hello")
        )
                .respond(
                        httpRequest -> //callback.
                        send request to another end point, hello2/server2
                );
    }*/

}
