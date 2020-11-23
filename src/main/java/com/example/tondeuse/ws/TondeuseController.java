package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.Position;
import com.example.tondeuse.domaine.Tondeuse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/")
public class TondeuseController {

    @Post(produces = MediaType.TEXT_PLAIN)
    public Single<String> tondeuse(@Body String payload) {
        List<Tondeuse> tondeuses = PayloadAdapter.mapPayloadToTondeuses(payload);
        List<Position> positions = tondeuses.stream().map(Tondeuse::explore).collect(Collectors.toList());
        return Single.just(PayloadAdapter.mapPositionsToStringResponse(positions));
    }
}
