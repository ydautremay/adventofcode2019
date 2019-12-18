package dyve.aoc.day.day15;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    Matrix game = new Matrix(42, 42);

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("15"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        Position start = new Position(0, 0);
        IntcodeIO io = new IntcodeIO(game, start);
        Program program = new Program(raw, io::giveInput, io::giveOutput);
        program.execute();

    }
}
