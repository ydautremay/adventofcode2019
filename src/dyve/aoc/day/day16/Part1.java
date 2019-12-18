package dyve.aoc.day.day16;

import dyve.aoc.input.InputReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    public List<Integer> pattern = List.of(1, 0, -1, 0);

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("16"));
        //instance.execute(List.of("69317163492948606335995924319873"));
    }

    protected void execute(List<String> inputs) {
        String inputStr = inputs.get(0);
        List<Integer> input = Arrays.stream(inputStr.split("")).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> pattern;

        for(int phase = 1; phase <= 100; phase++) {
            List<Integer> output = new ArrayList<>();
            for (int i = 1; i <= input.size(); i++) {
                long sum = 0;
                for (int j = 0; j < input.size(); j++) {
                    sum += input.get(j) * getPattern(i, j);
                }
                String s = String.valueOf(sum);
                output.add(Integer.parseInt("" + s.charAt(s.length() - 1)));
            }
            input = output;
        }
        System.out.println(input.stream().limit(8).map(String::valueOf).collect(Collectors.joining()));
    }

    private int getPattern(long mult, long rang){
        long group = BigDecimal.valueOf((rang % (4 * mult)) + 2).divide(BigDecimal.valueOf(mult), 0, RoundingMode.CEILING).longValue()%4;
        return pattern.get((int)group);
    }

    private List<Integer> createPattern(int nb){
        List<Integer> pattern = new ArrayList<>(4*nb);
        for(int i = 0; i < nb; i++){
            pattern.add(0, 0);
        }
        for(int i = 0; i < nb; i++){
            pattern.add(nb + i, 1);
        }
        for(int i = 0; i < nb; i++){
            pattern.add(2*nb + i, 0);
        }
        for(int i = 0; i < nb; i++){
            pattern.add(3*nb + i, -1);
        }

        int rem = pattern.remove(0);
        pattern.add(rem);
        return pattern;
    }
}
