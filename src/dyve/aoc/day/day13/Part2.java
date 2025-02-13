package dyve.aoc.day.day13;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Part2 {

    Matrix game = new Matrix(37, 22);

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("13"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        IntcodeIO io = new IntcodeIO(game);
        Program program = new Program(raw, io::giveInput, io::giveOutput);
        program.write(0, 2);
        program.execute();
        System.out.println(game);
        System.out.println("SCORE : " + io.score);
    }
}
