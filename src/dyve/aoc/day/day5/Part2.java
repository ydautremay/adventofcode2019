package dyve.aoc.day.day5;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("4"));
        //instance.execute(List.of("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        Program program = new Program(raw, 5);
        program.execute();
    }
}
