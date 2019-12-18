package dyve.aoc.day.day15;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Matrix {

    SortedMap<Integer, SortedMap<Integer, Integer>> inner;

    public Matrix(int width, int height) {
        inner = new TreeMap<>();
        for(int i = -height/2; i <= height/2; i++){
            SortedMap<Integer, Integer> line = new TreeMap<>();
            for(int j = -width/2; j <= width/2; j++){
                line.put(j, -1);
            }
            inner.put(i, line);
        }
    }

    public Integer get(int x, int y) {
        return inner.get(y).get(x);
    }

    public void set(int x, int y, Integer element) {
        inner.get(y).put(x, element);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        int minx = inner.values().stream().flatMap(map -> map.entrySet().stream()).filter(e -> e.getValue() != -1).mapToInt(Map.Entry::getKey).min().orElse(Integer.MIN_VALUE);
        int maxx = inner.values().stream().flatMap(map -> map.entrySet().stream()).filter(e -> e.getValue() != -1).mapToInt(Map.Entry::getKey).max().orElse(Integer.MAX_VALUE);
        int miny = inner.entrySet().stream().filter(e -> e.getValue().entrySet().stream().anyMatch(en -> en.getValue() != -1)).mapToInt(Map.Entry::getKey).min().orElse(Integer.MIN_VALUE);
        int maxy = inner.entrySet().stream().filter(e -> e.getValue().entrySet().stream().anyMatch(en -> en.getValue() != -1)).mapToInt(Map.Entry::getKey).max().orElse(Integer.MAX_VALUE);
        for (int i = miny; i <= maxy; i++) {
            for (int j = minx; j <= maxx; j++) {
                Integer element = get(j, i);
                if(element == null)
                    continue;
                output.append(switch (element){
                    case -1 -> " ";
                    case 0 -> "#";
                    case 1 -> ".";
                    case 2 -> "O";
                    case 3 -> "D";
                    case 4 -> "A";
                    default -> "!";
                });
            }
            output.append("\n");
        }
        return output.toString();
    }

    public boolean contains(int value){
        return inner.values().stream().flatMap(map -> map.values().stream()).anyMatch(i -> i == value);
    }
}
