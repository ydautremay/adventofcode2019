package dyve.aoc.day.day10;

import dyve.aoc.input.InputReader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part2 {

    Matrix<Asteroid> asteroids;

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
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

        List<Asteroid> destroyed = new ArrayList<>();

        Asteroid best = asteroids.get(11, 13);
        best.findVisible(asteroids);
        while(best.visible.size() > 0) {
            SortedMap<Double, Asteroid> bySlope = new TreeMap<>();
            for(Asteroid a : best.visible){
                Vector v = new Vector(best.p, a.p);
                if(v.x < 0){
                    continue;
                }
                bySlope.put(v.slope(), a);
            }
            for(Asteroid a : bySlope.values()){
                destroyed.add(a);
                asteroids.set(a.p.x, a.p.y, null);
            }
            bySlope.clear();
            for(Asteroid a : best.visible){
                Vector v = new Vector(best.p, a.p);
                if(v.x >= 0){
                    continue;
                }
                bySlope.put(v.slope(), a);
            }
            for(Asteroid a : bySlope.values()){
                destroyed.add(a);
                asteroids.set(a.p.x, a.p.y, null);
            }
            best.findVisible(asteroids);
        }
        System.out.println(destroyed.get(199));
    }
}
