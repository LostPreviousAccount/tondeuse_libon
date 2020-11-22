package com.example.tomdeuse.domain;

import org.apache.commons.collections4.ListUtils;

import java.util.List;

public class Tondeuse {

    public final Position positionInitiale;
    public final List<Commande> commandes;

    public Tondeuse(Position positionInitiale, List<Commande> commandes) {
        this.positionInitiale = positionInitiale;
        this.commandes = ListUtils.unmodifiableList(commandes);
    }
}
