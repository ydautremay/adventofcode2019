package dyve.aoc.day.day12;

import dyve.aoc.input.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Part2 {

    Set<String> states = new HashSet<>();

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("12"));
        //instance.execute(InputReader.readInput("12test"));
    }

    protected void execute(List<String> input) {
        Pattern pattern = Pattern.compile("<x=(-?[0-9]*), y=(-?[0-9]*), z=(-?[0-9]*)>");
        State currentState = new State();

        Matcher matcher = pattern.matcher(input.get(0));
        matcher.matches();
        currentState.x1 = Integer.parseInt(matcher.group(1));
        currentState.y1 = Integer.parseInt(matcher.group(2));
        currentState.z1 = Integer.parseInt(matcher.group(3));

        matcher = pattern.matcher(input.get(1));
        matcher.matches();
        currentState.x2 = Integer.parseInt(matcher.group(1));
        currentState.y2 = Integer.parseInt(matcher.group(2));
        currentState.z2 = Integer.parseInt(matcher.group(3));

        matcher = pattern.matcher(input.get(2));
        matcher.matches();
        currentState.x3 = Integer.parseInt(matcher.group(1));
        currentState.y3 = Integer.parseInt(matcher.group(2));
        currentState.z3 = Integer.parseInt(matcher.group(3));

        matcher = pattern.matcher(input.get(3));
        matcher.matches();
        currentState.x4 = Integer.parseInt(matcher.group(1));
        currentState.y4 = Integer.parseInt(matcher.group(2));
        currentState.z4 = Integer.parseInt(matcher.group(3));

        long steps = 0;
        System.out.println("Step " + steps + " : " + currentState);
        Instant start = Instant.now();
        do {
            states.add(currentState.reduce());
            currentState = currentState.step();
            steps++;
            if(steps%10000000 == 0) {
                Instant end = Instant.now();
                System.out.println(Duration.between(start, end).getSeconds());
                start = end;
                System.out.println("Step " + steps + " : " + currentState);
            }
        } while (!states.contains(currentState.reduce()));
        System.out.println("Step " + steps + " : " + currentState);
        System.out.println(steps);
    }

}
