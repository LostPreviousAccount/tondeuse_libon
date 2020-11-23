package com.example.tondeuse.ws;

import com.example.tondeuse.domaine.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.tondeuse.domaine.Orientation.*;

public class PayloadAdapter {

    public static final String LINE_SEP = "\n";
    public static final String PELOUSE_SEP = " ";
    public static final String POSITION_SEP = " ";

    private static final EnumMap<Orientation, String> ORIENTATION_MAP = new EnumMap<>(Orientation.class);
    private static final Map<String, Commande> COMMANDE_MAP = new HashMap<>();
    public static final int NB_LINES_TO_PARSE_FOR_TONDEUSE = 2;
    public static final int RADIX = 10;


    static {
        ORIENTATION_MAP.put(North, "N");
        ORIENTATION_MAP.put(South, "S");
        ORIENTATION_MAP.put(East, "E");
        ORIENTATION_MAP.put(West, "W");
        COMMANDE_MAP.put("A", Commande.Avancer);
        COMMANDE_MAP.put("G", Commande.Gauche);
        COMMANDE_MAP.put("D", Commande.Droite);
    }

    public static List<Tondeuse> mapPayloadToTondeuses(String payload) {
        String[] lines = payload.split(LINE_SEP);
        Pelouse pelouse = extractPelouse(lines[0]);

        int nbTondeusesToParse = (lines.length - 1) / NB_LINES_TO_PARSE_FOR_TONDEUSE;
        List<Tondeuse> tondeuses = new ArrayList<>();
        for (int i = 0; i < nbTondeusesToParse; i++) {
            Position position = extractPosition(lines[NB_LINES_TO_PARSE_FOR_TONDEUSE * i + 1]);
            List<Commande> commandes = extractCommandes(lines[NB_LINES_TO_PARSE_FOR_TONDEUSE * i + 2]);
            tondeuses.add(new Tondeuse(pelouse, position, commandes));
        }
        return tondeuses;
    }

    private static Pelouse extractPelouse(String line) {
        List<Integer> tuple_pelouse = Arrays.stream(line.split(PELOUSE_SEP))
                .map(s -> Integer.parseInt(s, RADIX))
                .collect(Collectors.toList());
        return new Pelouse(tuple_pelouse.get(0), tuple_pelouse.get(1));
    }

    private static List<Commande> extractCommandes(String line) {
        return line.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .map(COMMANDE_MAP::get)
                .collect(Collectors.toList());
    }

    private static Position extractPosition(String line) {
        String[] positionToExtract = line.split(POSITION_SEP);
        Orientation orientation = ORIENTATION_MAP.entrySet().stream()
                .filter(e -> e.getValue().equals(positionToExtract[2]))
                .findFirst().get()
                .getKey();
        return Position.de(
                Integer.parseInt(positionToExtract[0], RADIX),
                Integer.parseInt(positionToExtract[1], RADIX),
                orientation);
    }

    public static String mapPositionsToStringResponse(List<Position> positions) {
        return positions.stream()
                .map(p -> p.coordonnees.x + " " + p.coordonnees.y + " "+ ORIENTATION_MAP.get(p.orientation))
                .collect(Collectors.joining(LINE_SEP));
    }
}
