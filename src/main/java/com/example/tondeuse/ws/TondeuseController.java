package com.example.tondeuse.ws;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class TondeuseController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public String tondeuse(String payload) {
        return "CA MARCHE";
    }
}
