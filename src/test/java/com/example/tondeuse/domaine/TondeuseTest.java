package com.example.tondeuse.domaine;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.tondeuse.domaine.Commande.*;
import static org.assertj.core.api.Assertions.assertThat;

class TondeuseTest {

    @Test
    void tondeuse_sans_commande_ne_bouge_pas() {
        Position positionInitiale = Position.de(0, 0, Orientation.North);
        List<Commande> commandes = new ArrayList<>();

        Position position = new Tondeuse(positionInitiale, commandes).explore();

        assertThat(position).isEqualTo(positionInitiale);
    }

    @Test
    void tondeuse_une_seule_commande_avancer() {
        Position positionInitiale = Position.de(0, 0, Orientation.North);
        List<Commande> commandes = List.of(Avancer);

        Position position = new Tondeuse(positionInitiale, commandes).explore();

        assertThat(position).isEqualTo(Position.de(0, 1, Orientation.North));
    }

    @Test
    void tondeuse_serie_de_commandes() {
        Position positionInitiale = Position.de(1, 2, Orientation.North);
        List<Commande> commandes = List.of(Gauche, Avancer, Gauche, Avancer, Gauche, Avancer, Gauche, Avancer, Avancer);

        Position position = new Tondeuse(positionInitiale, commandes).explore();

        assertThat(position).isEqualTo(Position.de(1, 3, Orientation.North));
    }

    @Test
    void tondeuse_serie_de_commandes2() {
        Position positionInitiale = Position.de(3, 3, Orientation.East);
        List<Commande> commandes = List.of(Avancer, Avancer, Droite, Avancer, Avancer, Droite,
                Avancer, Droite, Droite, Avancer);

        Position position = new Tondeuse(positionInitiale, commandes).explore();

        assertThat(position).isEqualTo(Position.de(5, 1, Orientation.East));
    }
}