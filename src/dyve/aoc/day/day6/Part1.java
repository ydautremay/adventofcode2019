package dyve.aoc.day.day6;

import dyve.aoc.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {

    private Map<String, Planet> planets = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("6"));
    }

    protected void execute(List<String> input) {
        input.forEach(s -> {
            String[] split = s.split("\\)");
            planets.computeIfAbsent(split[0], Planet::new).orbitingPlanets.add(planets.computeIfAbsent(split[1], Planet::new));
            planets.get(split[1]).parent = planets.get(split[0]);
        });
        int accumulator = 0;
        for(Planet planet : planets.values()){
            accumulator += planet.computeOrbits(0);
        }
        System.out.println(accumulator);
    }
}
