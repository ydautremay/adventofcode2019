package dyve.aoc.day.day12;

import dyve.aoc.input.InputReader;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {


    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("12"));
        //instance.execute(InputReader.readInput("12test"));
    }

    protected void execute(List<String> input) {
        Pattern pattern = Pattern.compile("<x=(-?[0-9]*), y=(-?[0-9]*), z=(-?[0-9]*)>");
        State currentXState = new State();
        State currentYState = new State();
        State currentZState = new State();

        Matcher matcher = pattern.matcher(input.get(0));
        matcher.matches();
        currentXState.a1 = Integer.parseInt(matcher.group(1));
        currentYState.a1 = Integer.parseInt(matcher.group(2));
        currentZState.a1 = Integer.parseInt(matcher.group(3));

        matcher = pattern.matcher(input.get(1));
        matcher.matches();
        currentXState.a2 = Integer.parseInt(matcher.group(1));
        currentYState.a2 = Integer.parseInt(matcher.group(2));
        currentZState.a2 = Integer.parseInt(matcher.group(3));

        matcher = pattern.matcher(input.get(2));
        matcher.matches();
        currentXState.a3 = Integer.parseInt(matcher.group(1));
        currentYState.a3 = Integer.parseInt(matcher.group(2));
        currentZState.a3 = Integer.parseInt(matcher.group(3));

        matcher = pattern.matcher(input.get(3));
        matcher.matches();
        currentXState.a4 = Integer.parseInt(matcher.group(1));
        currentYState.a4 = Integer.parseInt(matcher.group(2));
        currentZState.a4 = Integer.parseInt(matcher.group(3));

        BigInteger xCycle = BigInteger.valueOf(findCycle(currentXState));
        System.out.println(xCycle);
        BigInteger yCycle = BigInteger.valueOf(findCycle(currentYState));
        System.out.println(yCycle);
        BigInteger zCycle = BigInteger.valueOf(findCycle(currentZState));
        System.out.println(zCycle);

        BigInteger lcm1 = lcm(xCycle, yCycle);
        System.out.println(lcm(lcm1, zCycle));
    }

    private long findCycle(State state){
        Set<String> states = new HashSet<>();
        long steps = 0;
        do {
            states.add(state.toString());
            state = state.step();
            steps++;
            if(steps%1000 == 0) {
                Instant end = Instant.now();
            }
        } while (!states.contains(state.toString()));
        return steps;
    }

    public  BigInteger lcm(BigInteger n1, BigInteger n2) {
        BigInteger gcd = n1.gcd(n2);
        return (n1.multiply(n2)).divide(gcd);
    }
}
