package dyve.aoc.day.day1;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("1"));
        //instance.execute(List.of("100756"));
    }

    private void execute(List<String> input) {
        int sumFuelModule = input.stream().mapToInt(Integer::parseInt).map(this::totalFuel).sum();
        System.out.println(sumFuelModule);
    }

    private int totalFuel(int mass){
        int total = 0;
        while (mass > 0){
            mass = fuel(mass);
            if(mass > 0) {
                total += mass;
            }
        }
        return total;
    }

    private int fuel(int mass){
        return (mass/3) - 2;
    }
}
