package dyve.aoc.day.day15;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Part2 {

    Matrix game = new Matrix(42, 42);

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("15"));
    }

    protected void execute(List<String> input) {
        List<String> raw = Arrays.asList(input.get(0).split(","));
        Position start = new Position(0, 0);
        IntcodeIO io = new IntcodeIO(game, start);
        Program program = new Program(raw, io::giveInput, io::giveOutput);
        program.execute();

        int counter = 0;
        int minx = game.inner.values().stream().flatMap(map -> map.entrySet().stream()).filter(e -> e.getValue() != -1).mapToInt(Map.Entry::getKey).min().orElse(Integer.MIN_VALUE);
        int maxx = game.inner.values().stream().flatMap(map -> map.entrySet().stream()).filter(e -> e.getValue() != -1).mapToInt(Map.Entry::getKey).max().orElse(Integer.MAX_VALUE);
        int miny = game.inner.entrySet().stream().filter(e -> e.getValue().entrySet().stream().anyMatch(en -> en.getValue() != -1)).mapToInt(Map.Entry::getKey).min().orElse(Integer.MIN_VALUE);
        int maxy = game.inner.entrySet().stream().filter(e -> e.getValue().entrySet().stream().anyMatch(en -> en.getValue() != -1)).mapToInt(Map.Entry::getKey).max().orElse(Integer.MAX_VALUE);
        while (game.contains(1)) {
            List<Position> o2 = new ArrayList<>();
            for (int i = miny; i <= maxy; i++) {
                for (int j = minx; j <= maxx; j++) {
                    if(game.get(j, i) != 1){
                        continue;
                    }
                    if(game.get(j+1, i) == 2 || game.get(j-1, i) == 2 || game.get(j, i+1) == 2 || game.get(j, i-1) == 2){
                        o2.add(new Position(j, i));
                    }
                }
            }
            for(Position p : o2){
                game.set(p.x, p.y, 2);
            }
            counter++;
            System.out.println("-----------------------");
            System.out.println(game);
            System.out.println(counter);
            System.out.println("-----------------------");
        }
    }
}
