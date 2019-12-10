package dyve.aoc.day.day2;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        List<String> input = InputReader.readInput("2");
        instance.execute(input);
    }

    protected void execute(List<String> input) {
        List<Integer> raw = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
        int output = 0;
        int noun = 0;
        int verb = 0;
        outer:for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                System.out.println("noun = " + i + ", verb = " + j);
                Program program = new Program(new ArrayList<>(raw));
                try {
                    output = program.execute(i, j);
                    System.out.println("output : " + output);
                    if (output == 19690720) {
                        noun = i;
                        verb = j;
                        break outer;
                    }
                }catch(Exception e){
                    System.out.println("BOOM");
                }
            }
        }
        System.out.println("noun = " + noun + ", verb = " + verb);
        System.out.println(100 * noun + verb);
    }
}
