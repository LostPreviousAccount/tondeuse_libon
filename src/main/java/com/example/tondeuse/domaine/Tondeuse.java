package com.example.tondeuse.domaine;

import org.apache.commons.collections4.ListUtils;

import java.util.List;

public class Tondeuse {

    public final Pelouse pelouse;
    public final Position positionInitiale;
    public final List<Commande> commandes;

    public Tondeuse(Pelouse pelouse, Position positionInitiale, List<Commande> commandes) {
        this.pelouse = pelouse;
        this.positionInitiale = positionInitiale;
        this.commandes = ListUtils.unmodifiableList(commandes);
    }

    public Position explore() {
        Position positionCourante = positionInitiale; // euh, y'a pas de fold-left en Java ???
        for (Commande commande : commandes) {
            Position nouvellePosition = positionCourante.deplace(commande);
            if (pelouse.valide(nouvellePosition.coordonnees)) {
                positionCourante = nouvellePosition;
            }
        }
        return positionCourante;
    }
}
