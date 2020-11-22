package com.example.tondeuse.domaine;

import org.junit.jupiter.api.Test;

import static com.example.tondeuse.domaine.Orientation.*;
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

    @Test
    void north_droite() {
        Position position = Position.de(0, 0, North);

        Position newPosition = position.deplace(Commande.Droite);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(0, 0));
        assertThat(newPosition.orientation).isEqualTo(East);
    }

    @Test
    void east_avance() {
        Position position = Position.de(0, 0, East);

        Position newPosition = position.deplace(Commande.Avancer);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(1, 0));
        assertThat(newPosition.orientation).isEqualTo(East);
    }

    @Test
    void east_gauche() {
        Position position = Position.de(0, 0, East);

        Position newPosition = position.deplace(Commande.Gauche);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(0, 0));
        assertThat(newPosition.orientation).isEqualTo(North);
    }

    @Test
    void east_droite() {
        Position position = Position.de(0, 0, East);

        Position newPosition = position.deplace(Commande.Droite);

        assertThat(newPosition.coordonnees).isEqualTo(new Coordonnees(0, 0));
        assertThat(newPosition.orientation).isEqualTo(South);
    }
}