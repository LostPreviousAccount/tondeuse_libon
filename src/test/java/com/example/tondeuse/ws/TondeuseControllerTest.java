package com.example.tondeuse.ws;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TondeuseControllerTest {

    @Inject
    TondeuseClient client;

    @Test
    void testTondeuse() {
        String payload = "5 5\n" +
                "1 2 N\n" +
                "GAGAGAGAA\n" +
                "3 3 E\n" +
                "AADAADADDA";

        String actual = client.tondeuse(payload).blockingGet();

        String expected = "1 3 N\n" +
                "5 1 E";
        assertEquals(expected, actual);
    }
}