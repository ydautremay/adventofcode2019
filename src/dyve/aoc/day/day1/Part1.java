package dyve.aoc.day.day1;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part1 {

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("1"));
    }

    protected void execute(List<String> input) {
        int sum = input.stream().mapToInt(Integer::parseInt).map(i -> i/3).map(i -> i - 2).sum();
        System.out.println(sum);
    }
}
