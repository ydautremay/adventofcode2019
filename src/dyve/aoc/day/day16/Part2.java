package dyve.aoc.day.day16;

import dyve.aoc.input.InputReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public List<Integer> pattern = List.of(1, 0, -1, 0);

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("16"));
        //instance.execute(List.of("03081770884921959731165446850517"));
    }

    protected void execute(List<String> inputs) {
        String inputStr = inputs.get(0);
        List<Integer> input = Arrays.stream(inputStr.split("")).map(Integer::valueOf).collect(Collectors.toList());
        int offset = Integer.parseInt(input.stream().limit(7).map(String::valueOf).collect(Collectors.joining()));
        List<Integer> copy = new ArrayList<>(input);
        for(int r = 1; r < 10000; r++){
            input.addAll(copy);
        }
        System.out.println("Init ok. Size : " + input.size());

        if(offset < input.size()/2){
            throw new IllegalStateException("Offset too small");
        }
        System.out.println("offset : " + offset);
        input = input.subList(offset, input.size());

        for(int phase = 1; phase <= 100; phase++) {
            List<Integer> output = new ArrayList<>(input);
            long sum = 0;
            for(int i = 0; i < input.size(); i++){
                sum += input.get(input.size() - 1 - i);
                String s = String.valueOf(sum);
                output.set(input.size() - 1 - i, Integer.parseInt("" + s.charAt(s.length() - 1)));
            }
//            System.out.println("Phase " + phase + " : " + output);
            input = output;
        }
        System.out.println(input.stream().limit(8).map(String::valueOf).collect(Collectors.joining()));
    }

    private int getPattern(long mult, long rang){
        long group = BigDecimal.valueOf((rang % (4 * mult)) + 2).divide(BigDecimal.valueOf(mult), 0, RoundingMode.CEILING).longValue()%4;
        return pattern.get((int)group);
    }
}
