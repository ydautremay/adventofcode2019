package dyve.aoc.day.day17;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part2 {

    Matrix game = new Matrix();

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("17"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        IntcodeIO io = new IntcodeIO(game);
        Program program = new Program(raw, io::giveInput, io::giveOutput);
        program.write(0, 2);
        program.execute();
        System.out.println(io.lastOutput);
    }
}
