package dyve.aoc.day.day11;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("11"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        Robot robot = new Robot(100, 100, 1);
        Program program = new Program(raw, robot);
        program.execute();
        System.out.println(robot.surface);
    }
}
