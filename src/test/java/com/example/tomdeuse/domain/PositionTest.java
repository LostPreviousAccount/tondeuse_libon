package com.example.tomdeuse.domain;

import org.junit.jupiter.api.Test;

import static com.example.tomdeuse.domain.Orientation.North;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void north_avance() {
        Position position = new Position(new Coordonnees(0, 0), North);

        Position newPosition = position.deplace(Commande.Avancer);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(0, 1));
        assertThat(newPosition.orientation).isEqualTo(North);
    }
}