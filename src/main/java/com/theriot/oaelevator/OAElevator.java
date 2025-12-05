package com.theriot.oaelevator;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OAElevator {

    private static final int SINGLE_FLOOR_TRAVEL_TIME = 10;

    private List<Integer> floors;

    public OAElevator(List<Integer> floors) {
        this.floors = floors;
    }

    public String evaluateResults() {
        int totalFloorsTraveled = floors.stream().collect(FloorsTraveledCollector.collector());
        int totalTime = SINGLE_FLOOR_TRAVEL_TIME * totalFloorsTraveled;
        String floorList = floors.stream().map(String::valueOf).collect(Collectors.joining(","));

        return String.format("%d %s", totalTime, floorList);
    }

    static final class FloorsTraveledCollector {

        private int first;
        private int second;
        private int totalFloorsTraveled;

        public void accept(int next) {
            first = second;
            second = next;
            if (first != 0) {
                totalFloorsTraveled += Math.abs(first - second);
            }
        }

        public FloorsTraveledCollector combine(FloorsTraveledCollector other) {
            throw new UnsupportedOperationException("Parallel Stream not supported");
        }

        public int finish() {
            return totalFloorsTraveled;
        }

        public static Collector<Integer, ?, Integer> collector() {
            return Collector.of(FloorsTraveledCollector::new, FloorsTraveledCollector::accept, FloorsTraveledCollector::combine, FloorsTraveledCollector::finish);
        }
    }
}
