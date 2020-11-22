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
        if (commande == Commande.Gauche) {
            return tournerAGauche();
        }
        if (commande == Commande.Droite) {
            return tournerADroite();
        }
        else {
            switch (this.orientation) {
                case North:
                    return Position.de(this.coordonnees.x, this.coordonnees.y + 1, orientation);
                case East:
                    return Position.de(this.coordonnees.x + 1, this.coordonnees.y, orientation);
            }
            return this; //en attendant que le switch soit complet
        }
    }

    private Position tournerADroite() {
        return Position.de(this.coordonnees.x, this.coordonnees.y, this.orientation.aDroite());
    }

    private Position tournerAGauche() {
        return Position.de(this.coordonnees.x, this.coordonnees.y, this.orientation.aGauche());
    }

}
