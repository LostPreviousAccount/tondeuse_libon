package com.example.tomdeuse.domain;


public class Position {

    public final Coordonnees coordonnees;
    public final Orientation orientation;

    public Position(Coordonnees coordonnees, Orientation orientation) {
        this.coordonnees = coordonnees;
        this.orientation = orientation;
    }
}
