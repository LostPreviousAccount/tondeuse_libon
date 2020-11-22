package com.example.tondeuse.domaine;


public class Position {

    public final Coordonnees coordonnees;
    public final Orientation orientation;

    private Position(Coordonnees coordonnees, Orientation orientation) {
        this.coordonnees = coordonnees;
        this.orientation = orientation;
    }

    public static Position de(int x, int y, Orientation orientation) {
        return new Position(new Coordonnees(x, y), orientation);
    }

    public Position deplace(Commande commande) {
        return Position.de(this.coordonnees.x, this.coordonnees.y + 1, orientation);
    }
}
