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


}
