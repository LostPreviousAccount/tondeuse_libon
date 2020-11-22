package com.example.tondeuse.domaine;


import java.util.Objects;

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
        switch (commande) {
            case Gauche:
                return tournerAGauche();
            case Droite:
                return tournerADroite();
            default:
                return avance();
        }
    }

    private Position tournerADroite() {
        return Position.de(this.coordonnees.x, this.coordonnees.y, this.orientation.aDroite());
    }

    private Position tournerAGauche() {
        return Position.de(this.coordonnees.x, this.coordonnees.y, this.orientation.aGauche());
    }

    private Position avance() {
        switch (this.orientation) {
            case North:
                return Position.de(this.coordonnees.x, this.coordonnees.y + 1, orientation);
            case South:
                return Position.de(this.coordonnees.x, this.coordonnees.y - 1, orientation);
            case East:
                return Position.de(this.coordonnees.x + 1, this.coordonnees.y, orientation);
            default:
                return Position.de(this.coordonnees.x - 1, this.coordonnees.y, orientation);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(coordonnees, position.coordonnees) &&
                orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordonnees, orientation);
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordonnees=" + coordonnees +
                ", orientation=" + orientation +
                '}';
    }
}
