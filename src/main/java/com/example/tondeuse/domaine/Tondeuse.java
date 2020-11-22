package com.example.tondeuse.domaine;

import org.apache.commons.collections4.ListUtils;

import java.util.List;

public class Tondeuse {

    public final Position positionInitiale;
    public final List<Commande> commandes;

    public Tondeuse(Position positionInitiale, List<Commande> commandes) {
        this.positionInitiale = positionInitiale;
        this.commandes = ListUtils.unmodifiableList(commandes);
    }

    public Position explore() {
        Position positionCourante = positionInitiale; // euh, y'a pas de fold-left en Java ???
        for (Commande commande : commandes) {
            positionCourante = positionCourante.deplace(commande);
        }
        return positionCourante;
    }
}
