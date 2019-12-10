package dyve.aoc.day.day5;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("4"));
        //instance.execute(List.of("1002,4,3,4,99"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        Program program = new Program(raw, 1);
        program.execute();
    }
}
