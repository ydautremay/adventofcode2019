package dyve.aoc.day.day18;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part1 {

    Matrix grotto = new Matrix();

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("18test"));
    }

    protected void execute(List<String> input) {
        for(int i = 0; i < input.size(); i++){
            String line = input.get(i);
            for(int j = 0; j < line.length(); j++){
                grotto.set(j, i, "" + line.charAt(j));
            }
        }
        System.out.println(grotto);
    }
}
