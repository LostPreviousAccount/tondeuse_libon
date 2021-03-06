package com.example.tondeuse.domaine;

import java.util.Objects;

public class Coordonnees {

    public final int x;
    public final int y;

    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordonnees allerNord() {
        return new Coordonnees(x, y + 1);
    }

    public Coordonnees allerSud() {
        return new Coordonnees(x, y - 1);
    }

    public Coordonnees allerEst() {
        return new Coordonnees(x + 1, y);
    }

    public Coordonnees allerOuest() {
        return new Coordonnees(x - 1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnees that = (Coordonnees) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordonnees{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
