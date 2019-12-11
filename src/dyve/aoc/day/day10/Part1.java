package dyve.aoc.day.day10;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.List;

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
            for(Asteroid other : asteroids){
                if(a == null || other == null || a == other){
                    continue;
                }
                if(a.hidden.contains(other)){
                    other.hidden.add(a);
                    continue;
                }
                if(a.visible.contains(other)){
                    other.visible.add(a);
                    continue;
                }
                if(other.hidden.contains(a)){
                    a.hidden.add(other);
                    continue;
                }
                if(other.visible.contains(a)){
                    a.visible.add(other);
                    continue;
                }
                Vector v = new Vector(other.p.x - a.p.x, other.p.y - a.p.y);
                v = v.reduce();
                Point current = a.p;
                Asteroid first = null;
                current = current.add(v);
                while(current.x >= 0 && current.y >= 0 && current.x < asteroids.width && current.y < asteroids.height){
                    Asteroid asteroid = asteroids.get(current.x, current.y);
                    if(first == null){
                        if(asteroid != null){
                            first = asteroid;
                            a.visible.add(asteroid);
                        }
                    }else{
                        if(asteroid != null){
                            a.hidden.add(asteroid);
                        }
                    }
                    current = current.add(v);
                }
            }
        }
        System.out.println(asteroids);
        System.out.println(asteroids.stream().mapToInt(a -> a == null ? 0 : a.visible.size()).max().orElse(0));
    }
}
