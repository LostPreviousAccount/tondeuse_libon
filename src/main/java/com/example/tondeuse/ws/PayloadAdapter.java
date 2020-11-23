package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.Commande;
import com.example.tondeuse.domaine.Pelouse;
import com.example.tondeuse.domaine.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        String[] lines = payload.split("\n");
        List<Integer> tuple_pelouse = Arrays.stream(lines[0].split(" "))
                .map(s -> Integer.parseInt(s, 10))
                .collect(Collectors.toList());
        Pelouse pelouse = new Pelouse(tuple_pelouse.get(0), tuple_pelouse.get(1));
        return new PayloadAdapter(pelouse, null, null);
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
