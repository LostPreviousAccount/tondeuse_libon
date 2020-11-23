package com.example.tondeuse.domaine;

public class Pelouse {

    public final int parallele_max; //x
    public final int meridien_max; //y

    public Pelouse(int max_x, int max_y) {
        this.parallele_max = max_x;
        this.meridien_max = max_y;
    }

    public boolean valide(Position position) {
        return position.coordonnees.x >= 0 && position.coordonnees.x <= parallele_max &&
                position.coordonnees.y >= 0 && position.coordonnees.y <= meridien_max;
    }
}
