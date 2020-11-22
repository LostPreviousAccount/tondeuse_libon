package com.example.tomdeuse.domain;


public class Position {

    public final Coordonnees coordonnees;
    public final Orientation orientation;

    public Position(Coordonnees coordonnees, Orientation orientation) {
        this.coordonnees = coordonnees;
        this.orientation = orientation;
    }

    public Position deplace(Commande commande) {
        return new Position(new Coordonnees(this.coordonnees.x, this.coordonnees.y + 1), orientation);
    }
}
