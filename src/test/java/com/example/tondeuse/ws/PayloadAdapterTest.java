package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.Orientation;
import com.example.tondeuse.domaine.Position;
import com.example.tondeuse.domaine.Tondeuse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.tondeuse.domaine.Commande.*;
import static org.assertj.core.api.Assertions.assertThat;

class PayloadAdapterTest {

    @Test
    void pelouse_with_1_tondeuse() {
        String payload = "2 3\n" +
                "1 1 N\n" +
                "AGD";

        List<Tondeuse> tondeuses = PayloadAdapter.mapPayloadToTondeuses(payload);

        Tondeuse tondeuse = tondeuses.get(0);
        assertThat(tondeuse.pelouse.parallele_max).isEqualTo(2);
        assertThat(tondeuse.pelouse.meridien_max).isEqualTo(3);
        assertThat(tondeuse.positionInitiale).isEqualTo(Position.de(1, 1, Orientation.North));
        assertThat(tondeuse.commandes).isEqualTo(List.of(Avancer, Gauche, Droite));
    }

    @Test
    void pelouse_with_2_tondeuses() {
        String payload = "11 10\n" +
                "1 1 N\n" +
                "AGD\n"+
                "10 9 E\n" +
                "DAGA";

        List<Tondeuse> tondeuses = PayloadAdapter.mapPayloadToTondeuses(payload);

        Tondeuse tondeuse = tondeuses.get(0);
        assertThat(tondeuse.pelouse.parallele_max).isEqualTo(11);
        assertThat(tondeuse.pelouse.meridien_max).isEqualTo(10);
        assertThat(tondeuse.positionInitiale).isEqualTo(Position.de(1, 1, Orientation.North));
        assertThat(tondeuse.commandes).isEqualTo(List.of(Avancer, Gauche, Droite));
        Tondeuse tondeuse2 = tondeuses.get(1);
        assertThat(tondeuse2.pelouse.parallele_max).isEqualTo(11);
        assertThat(tondeuse2.pelouse.meridien_max).isEqualTo(10);
        assertThat(tondeuse2.positionInitiale).isEqualTo(Position.de(10, 9, Orientation.East));
        assertThat(tondeuse2.commandes).isEqualTo(List.of(Droite, Avancer, Gauche, Avancer));
    }
}