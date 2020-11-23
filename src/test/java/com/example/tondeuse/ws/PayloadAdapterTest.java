package com.example.tondeuse.ws;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PayloadAdapterTest {

    @Test
    void pelouse_only() {
        String payload = "5 4";

        PayloadAdapter adapter = PayloadAdapter.mapPayloadToDomainModels(payload);

        assertThat(adapter.getPelouse().parallele_max).isEqualTo(5);
        assertThat(adapter.getPelouse().meridien_max).isEqualTo(4);
    }
}