package com.example.tondeuse.ws;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TondeuseControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testResponse() {
        String payload = "5 5\n" +
                "1 2 N\n" +
                "GAGAGAGAA\n" +
                "3 3 E\n" +
                "AADAADADDA";

        String response = client.toBlocking()
                .retrieve(HttpRequest.POST("/", payload));

        String expected = "1 3 N\n" +
                "5 1 E";
        assertEquals(expected, response);
    }
}