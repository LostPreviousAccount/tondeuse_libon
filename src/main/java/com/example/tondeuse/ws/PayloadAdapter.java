package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.Commande;
import com.example.tondeuse.domaine.Pelouse;
import com.example.tondeuse.domaine.Position;

import java.util.List;

public class PayloadAdapter {

    private Pelouse pelouse;
    private List<Position> positions;
    private List<Commande> commandes;

    public PayloadAdapter(Pelouse pelouse, List<Position> positions, List<Commande> commandes) {
        this.pelouse = pelouse;
        this.positions = positions;
        this.commandes = commandes;
    }

    public static PayloadAdapter mapPayloadToDomainModels(String payload) {
        return null;
    }

    public Pelouse getPelouse() {
        return pelouse;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }
}
