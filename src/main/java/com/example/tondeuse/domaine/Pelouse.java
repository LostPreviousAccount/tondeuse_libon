package com.example.tondeuse.domaine;

public class Pelouse {

    public final int parallele_max; //x
    public final int meridien_max; //y

    public Pelouse(int max_x, int max_y) {
        this.parallele_max = max_x;
        this.meridien_max = max_y;
    }

    public boolean valide(Coordonnees coordonnees) {
        return coordonnees.x >= 0 && coordonnees.x <= parallele_max &&
                coordonnees.y >= 0 && coordonnees.y <= meridien_max;
    }
}
