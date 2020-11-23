package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.Commande;
import com.example.tondeuse.domaine.Orientation;
import com.example.tondeuse.domaine.Pelouse;
import com.example.tondeuse.domaine.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.tondeuse.domaine.Orientation.*;

public class PayloadAdapter {

    public static final String LINE_SEP = "\n";
    public static final String PELOUSE_SEP = " ";
    public static final String POSITION_SEP = " ";
    private final Pelouse pelouse;
    private final List<Position> positions;
    private final List<List<Commande>> commandes;

    private static final EnumMap<Orientation, String> ORIENTATION_MAP = new EnumMap<>(Orientation.class);
    private static final EnumMap<Commande, String> COMMANDE_MAP = new EnumMap<>(Commande.class);

    static {
        ORIENTATION_MAP.put(North, "N");
        ORIENTATION_MAP.put(South, "S");
        ORIENTATION_MAP.put(East, "E");
        ORIENTATION_MAP.put(West, "W");
        COMMANDE_MAP.put(Commande.Avancer, "A");
        COMMANDE_MAP.put(Commande.Gauche, "G");
        COMMANDE_MAP.put(Commande.Droite, "D");
    }

    public PayloadAdapter(Pelouse pelouse, List<Position> positions, List<List<Commande>> commandes) {
        this.pelouse = pelouse;
        this.positions = positions;
        this.commandes = commandes;
    }

    public static PayloadAdapter mapPayloadToDomainModels(String payload) {
        String[] lines = payload.split(LINE_SEP);
        Pelouse pelouse = extractPelouse(lines[0]);

        int nbTondeuseToParse = (lines.length - 1) / 2;
        List<Position> positions = new ArrayList<>();
        List<List<Commande>> commandesList = new ArrayList<>();
        for (int i = 0; i < nbTondeuseToParse; i++) {
            Position position = extractPosition(lines[2 * i + 1]);
            positions.add(position);
            List<Commande> commandes = extractCommandes(lines[2 * i + 2]);
            positions.add(position);
            commandesList.add(commandes);
        }
        return new PayloadAdapter(pelouse, positions, commandesList);
    }

    private static Pelouse extractPelouse(String line) {
        List<Integer> tuple_pelouse = Arrays.stream(line.split(PELOUSE_SEP))
                .map(s -> Integer.parseInt(s, 10))
                .collect(Collectors.toList());
        return new Pelouse(tuple_pelouse.get(0), tuple_pelouse.get(1));
    }

    private static List<Commande> extractCommandes(String line) {
        return line.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .map(c -> COMMANDE_MAP.entrySet().stream()
                        .filter(e -> e.getValue().equals(c))
                        .findFirst().get()
                        .getKey()).collect(Collectors.toList());
    }

    private static Position extractPosition(String line) {
        String[] positionToExtract = line.split(POSITION_SEP);
        Orientation orientation = ORIENTATION_MAP.entrySet().stream()
                .filter(e -> e.getValue().equals(positionToExtract[2]))
                .findFirst().get()
                .getKey();
        return Position.de(
                Integer.parseInt(positionToExtract[0], 10),
                Integer.parseInt(positionToExtract[1], 10),
                orientation);
    }

    public Pelouse getPelouse() {
        return pelouse;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<List<Commande>> getCommandes() {
        return commandes;
    }
}
