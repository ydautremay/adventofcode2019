package dyve.aoc.day.day6;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    private Map<String, Planet> planets = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("6"));
    }

    protected void execute(List<String> input) {
        input.forEach(s -> {
            String[] split = s.split("\\)");
            planets.computeIfAbsent(split[0], Planet::new).orbitingPlanets.add(planets.computeIfAbsent(split[1], Planet::new));
            planets.get(split[1]).parent = planets.get(split[0]);
        });
        List<Planet> myPath = new ArrayList<>();
        planets.get("YOU").pathToHead(myPath);
        System.out.println(myPath);

        List<Planet> santaPath = new ArrayList<>();
        planets.get("SAN").pathToHead(santaPath);
        System.out.println(santaPath);

        List<Planet> copy = new ArrayList<>(myPath);
        myPath.removeAll(santaPath);
        System.out.println(myPath);
        santaPath.removeAll(copy);
        System.out.println(santaPath);

        System.out.println(myPath.size() + santaPath.size());
    }
}
