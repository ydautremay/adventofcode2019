package dyve.aoc.day.day10;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Part1 {

    Matrix<Asteroid> asteroids;

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("10"));
        //instance.execute(InputReader.readInput("10test"));
    }

    protected void execute(List<String> input) {
        asteroids = new Matrix<>(input.get(0).length(), input.size());
        for(int i = 0; i < input.size(); i++){
            String line = input.get(i);
            for(int j = 0; j < line.length(); j++){
                if(line.charAt(j) == '#') {
                    asteroids.set(j, i, new Asteroid(j, i));
                }
            }
        }


        for(Asteroid a : asteroids){
            if(a == null)
                continue;
            a.findVisible(asteroids);
        }
        System.out.println(asteroids);
        Asteroid best = asteroids.stream().filter(Objects::nonNull).max(Comparator.comparing(a -> a.visible.size())).orElse(null);
        assert best != null;
        System.out.println(best.p);
        System.out.println(best.visible.size());
    }
}
