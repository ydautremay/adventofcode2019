package dyve.aoc.day.day2;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("2"));
    }

    protected void execute(List<String> input) {
        List<Integer> raw = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
        Program program = new Program(raw);
        System.out.println(program.execute(12, 2));
    }
}
