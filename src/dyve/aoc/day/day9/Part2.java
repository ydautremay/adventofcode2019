package dyve.aoc.day.day9;

import dyve.aoc.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("9"));
        //instance.execute(List.of("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"));
        //instance.execute(List.of("1102,34915192,34915192,7,4,7,99,0"));
        //instance.execute(List.of("104,1125899906842624,99"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        Program program = new Program(raw, 2);
        program.execute();
        System.out.println(program.output);
    }
}
