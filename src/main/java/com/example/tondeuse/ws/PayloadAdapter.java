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

    private Pelouse pelouse;
    private List<Position> positions;
    private List<List<Commande>> commandes;

    private static final EnumMap<Orientation, String> ORIENTATION_MAP = new EnumMap<>(Orientation.class);
    //private static final EnumMap<Commande, String> COMMANDE_MAP;

    static {
        ORIENTATION_MAP.put(North, "N");
        ORIENTATION_MAP.put(South, "S");
        ORIENTATION_MAP.put(East, "E");
        ORIENTATION_MAP.put(West, "W");
    }

    public PayloadAdapter(Pelouse pelouse, List<Position> positions, List<List<Commande>> commandes) {
        this.pelouse = pelouse;
        this.positions = positions;
        this.commandes = commandes;
    }

    public static PayloadAdapter mapPayloadToDomainModels(String payload) {
        String[] lines = payload.split("\n");
        Pelouse pelouse = extractPelouse(lines[0]);

        int nb_tondeuse_to_parse = lines.length - 1;
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < nb_tondeuse_to_parse; i++) {
            Position position = extractPosition(lines[2 * i + 1]);
            positions.add(position);
        }
        return new PayloadAdapter(pelouse, positions, null);
    }

    private static Pelouse extractPelouse(String line) {
        List<Integer> tuple_pelouse = Arrays.stream(line.split(" "))
                .map(s -> Integer.parseInt(s, 10))
                .collect(Collectors.toList());
        return new Pelouse(tuple_pelouse.get(0), tuple_pelouse.get(1));
    }

    private static Position extractPosition(String line) {
        String[] position_to_extract = line.split(" ");
        Orientation orientation = ORIENTATION_MAP.entrySet().stream()
                .filter(e -> e.getValue().equals(position_to_extract[2]))
                .findFirst().get()
                .getKey();
        return Position.de(Integer.parseInt(position_to_extract[0], 10), Integer.parseInt(position_to_extract[1], 10), orientation);
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
