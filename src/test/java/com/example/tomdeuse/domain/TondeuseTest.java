package com.example.tomdeuse.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TondeuseTest {

    @Test
    void tondeuse_sans_commande_ne_bouge_pas() {
        Position positionInitiale = new Position(new Coordonnees(0, 0), Orientation.North);
        List<Commande> commandes = new ArrayList<>();

        Position position = new Tondeuse(positionInitiale, commandes).explore();

        assertThat(position).isEqualTo(positionInitiale);
    }
}