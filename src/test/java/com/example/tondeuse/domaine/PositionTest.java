package com.example.tondeuse.domaine;

import org.junit.jupiter.api.Test;

import static com.example.tondeuse.domaine.Orientation.North;
import static com.example.tondeuse.domaine.Orientation.West;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void north_avance() {
        Position position = Position.de(0, 0, North);

        Position newPosition = position.deplace(Commande.Avancer);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(0, 1));
        assertThat(newPosition.orientation).isEqualTo(North);
    }

    @Test
    void north_gauche() {
        Position position = Position.de(0, 0, North);

        Position newPosition = position.deplace(Commande.Gauche);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(0, 0));
        assertThat(newPosition.orientation).isEqualTo(West);
    }
}