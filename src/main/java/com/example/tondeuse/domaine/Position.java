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
        switch (this.orientation) {
            case North:
                switch (commande) {
                    case Gauche:
                        return Position.de(this.coordonnees.x, this.coordonnees.y, Orientation.West);
                    case Droite:
                        return Position.de(this.coordonnees.x, this.coordonnees.y, Orientation.East);
                }
                return Position.de(this.coordonnees.x, this.coordonnees.y + 1, orientation);
            case East:
                switch (commande) {
                    case Gauche:
                        return Position.de(this.coordonnees.x, this.coordonnees.y, Orientation.North);
                    case Droite:
                        return Position.de(this.coordonnees.x, this.coordonnees.y, Orientation.South);
                }
                return Position.de(this.coordonnees.x +1, this.coordonnees.y, orientation);
        }
        return null;
    }
}
