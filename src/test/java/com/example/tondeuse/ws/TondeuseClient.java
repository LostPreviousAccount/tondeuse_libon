package com.example.tondeuse.ws;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/")
public interface TondeuseClient {
    @Post(consumes = MediaType.TEXT_PLAIN)
    Single<String> tondeuse(@Body String payload);
}
