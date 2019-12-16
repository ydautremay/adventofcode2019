package dyve.aoc.day.day12;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Part1 {

    List<Moon> moons = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("12"));
        //instance.execute(InputReader.readInput("12test"));
    }

    protected void execute(List<String> input) {
        Pattern pattern = Pattern.compile("<x=(-?[0-9]*), y=(-?[0-9]*), z=(-?[0-9]*)>");
        for(String line : input){
            Moon moon = new Moon();
            Matcher matcher = pattern.matcher(line);
            int x = 0, y = 0, z = 0;
            if(matcher.matches()){
                x = Integer.parseInt(matcher.group(1));
                y = Integer.parseInt(matcher.group(2));
                z = Integer.parseInt(matcher.group(3));
            }
            moon.position = new Vector(x, y, z);
            moon.velocity = new Vector(0, 0, 0);
            moons.add(moon);
        }
        int steps = 1000;
        System.out.println("Step " + 0 + " : " + moons);
        IntStream.rangeClosed(1, steps).forEach(i -> {
                moons.forEach(m1 -> {
                    moons.stream().filter(m2 -> !m1.equals(m2)).forEach(m1::applyGravity);
                });
                moons.forEach(Moon::applyVelocity);
            System.out.println("Step " + i + " : " + moons);
        });
        System.out.println(moons.stream().mapToInt(Moon::totalEnergy).sum());
    }

}
