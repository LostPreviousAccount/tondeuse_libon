package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.Orientation;
import com.example.tondeuse.domaine.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PayloadAdapterTest {

    @Test
    void pelouse_only() {
        String payload = "5 4";

        PayloadAdapter adapter = PayloadAdapter.mapPayloadToDomainModels(payload);

        assertThat(adapter.getPelouse().parallele_max).isEqualTo(5);
        assertThat(adapter.getPelouse().meridien_max).isEqualTo(4);
    }

    @Test
    void pelouse_with_1_position() {
        String payload = "2 3\n" +
                "1 1 N";

        PayloadAdapter adapter = PayloadAdapter.mapPayloadToDomainModels(payload);

        assertThat(adapter.getPelouse().parallele_max).isEqualTo(2);
        assertThat(adapter.getPelouse().meridien_max).isEqualTo(3);
        assertThat(adapter.getPositions().get(0)).isEqualTo(Position.de(1, 1, Orientation.North));
    }
}