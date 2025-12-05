package com.theriot.oaelevator;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    private static final String USAGE = "Command line arguments must be provided to indicate start floor and floors " +
            "traveled.\ne.g. java -jar ~.jar start=12 floor=2,9,1,32";
    private static final String START = "start";
    private static final String FLOOR = "floor";

    public static void main(String[] args) {
        try {
            OAElevator elevator = new OAElevator(parseArguments(args));
            System.out.println(elevator.evaluateResults());
        } catch (Throwable t) {
            System.out.println(USAGE);
        }
    }

    static List<Integer> parseArguments(String[] args) {
        Map<String, String> pairs = collectArguments(args);
        String startExpr = pairs.get(START);
        String floorExpr = pairs.get(FLOOR);
        if (startExpr == null || floorExpr == null) {
            throw new RuntimeException();
        }

        List<Integer> parsedArguments = new ArrayList<Integer>();
        parsedArguments.add(Integer.valueOf(startExpr));
        parsedArguments.addAll(parseIntList(floorExpr));

        return parsedArguments;
    }

    static Map<String, String> collectArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();

        Arrays.stream(args).map(arg -> {
            String[] pair = arg.split("=");
            if (pair.length != 2) {
                throw new RuntimeException();
            }
            return pair;
        }).forEach(pair -> arguments.put(pair[0], pair[1]));

        return arguments;
    }

    static List<Integer> parseIntList(String expr) {
        return Arrays.stream(expr.split(",")).map(Integer::valueOf).collect(Collectors.toList());
    }
}
