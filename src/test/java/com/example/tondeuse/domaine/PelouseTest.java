package com.example.tondeuse.domaine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PelouseTest {

    @Test
    void position_invalide_quand_negative() {
        Pelouse pelouse = new Pelouse(3, 3);
        Position x_negatif = Position.de(-1, 0, Orientation.North);
        Position y_negatif = Position.de(0, -1, Orientation.North);

        assertThat(pelouse.valide(x_negatif)).isFalse();
        assertThat(pelouse.valide(y_negatif)).isFalse();
    }

    @Test
    void position_invalide_quand_hors_bornes() {
        Pelouse pelouse = new Pelouse(3, 3);
        Position x_dehors = Position.de(4, 0, Orientation.North);
        Position y_dehors = Position.de(0, 4, Orientation.North);

        assertThat(pelouse.valide(x_dehors)).isFalse();
        assertThat(pelouse.valide(y_dehors)).isFalse();
    }

    @Test
    void position_valide() {
        Pelouse pelouse = new Pelouse(3, 3);

        assertThat(pelouse.valide(Position.de(0, 3, Orientation.South))).isTrue();
        assertThat(pelouse.valide(Position.de(3, 3, Orientation.South))).isTrue();
        assertThat(pelouse.valide(Position.de(3, 2, Orientation.South))).isTrue();
        assertThat(pelouse.valide(Position.de(0, 0, Orientation.South))).isTrue();
        assertThat(pelouse.valide(Position.de(0, 2, Orientation.South))).isTrue();
        assertThat(pelouse.valide(Position.de(2, 1, Orientation.South))).isTrue();
    }
}