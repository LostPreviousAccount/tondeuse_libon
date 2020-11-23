package com.example.tondeuse.domaine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PelouseTest {

    @Test
    void position_invalide_quand_negative() {
        Pelouse pelouse = new Pelouse(3, 3);
        Coordonnees x_negatif = new Coordonnees(-1, 0);
        Coordonnees y_negatif = new Coordonnees(0, -1);

        assertThat(pelouse.valide(x_negatif)).isFalse();
        assertThat(pelouse.valide(y_negatif)).isFalse();
    }

    @Test
    void position_invalide_quand_hors_bornes() {
        Pelouse pelouse = new Pelouse(3, 3);
        Coordonnees x_dehors = new Coordonnees(4, 0);
        Coordonnees y_dehors = new Coordonnees(0, 4);

        assertThat(pelouse.valide(x_dehors)).isFalse();
        assertThat(pelouse.valide(y_dehors)).isFalse();
    }

    @Test
    void position_valide() {
        Pelouse pelouse = new Pelouse(3, 3);

        assertThat(pelouse.valide(new Coordonnees(0, 3))).isTrue();
        assertThat(pelouse.valide(new Coordonnees(3, 3))).isTrue();
        assertThat(pelouse.valide(new Coordonnees(3, 2))).isTrue();
        assertThat(pelouse.valide(new Coordonnees(0, 0))).isTrue();
        assertThat(pelouse.valide(new Coordonnees(0, 2))).isTrue();
        assertThat(pelouse.valide(new Coordonnees(2, 1))).isTrue();
    }
}