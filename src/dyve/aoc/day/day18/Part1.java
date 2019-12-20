package dyve.aoc.day.day18;

import dyve.aoc.input.InputReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    Matrix grotto = new Matrix();

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("18"));
    }

    protected void execute(List<String> input) {
        Set<Character> letters = new HashSet<>();
        for(int i = 0; i < input.size(); i++){
            String line = input.get(i);
            for(int j = 0; j < line.length(); j++){
                grotto.set(j, i, "" + line.charAt(j));
                letters.add(line.charAt(j));
            }
        }
        System.out.println(grotto);
        System.out.println(letters);

        Explorer explorer = new Explorer(grotto);
        explorer.explore();
    }
}
