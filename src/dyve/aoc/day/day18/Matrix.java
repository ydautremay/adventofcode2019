package dyve.aoc.day.day18;

import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class Matrix {

    SortedMap<Integer, SortedMap<Integer, String>> inner;

    public Matrix() {
        inner = new TreeMap<>();
    }

    public String get(int x, int y) {
        return inner.getOrDefault(y, new TreeMap<>()).get(x);
    }

    public void set(int x, int y, String element) {
        inner.computeIfAbsent(y, k -> new TreeMap<>()).put(x, element);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        int minx = inner.values().stream().flatMap(map -> map.entrySet().stream()).filter(e -> !e.getValue().equals("")).mapToInt(Map.Entry::getKey).min().orElse(Integer.MAX_VALUE);
        int maxx = inner.values().stream().flatMap(map -> map.entrySet().stream()).filter(e -> !e.getValue().equals("")).mapToInt(Map.Entry::getKey).max().orElse(Integer.MIN_VALUE);
        int miny = inner.entrySet().stream().filter(e -> e.getValue().entrySet().stream().anyMatch(en -> !en.getValue().equals(""))).mapToInt(Map.Entry::getKey).min().orElse(Integer.MAX_VALUE);
        int maxy = inner.entrySet().stream().filter(e -> e.getValue().entrySet().stream().anyMatch(en -> !en.getValue().equals(""))).mapToInt(Map.Entry::getKey).max().orElse(Integer.MIN_VALUE);
        for (int i = miny; i <= maxy; i++) {
            for (int j = minx; j <= maxx; j++) {
                String element = get(j, i);
                if(element == null)
                    continue;
                output.append(element);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public boolean contains(String value){
        return inner.values().stream().flatMap(map -> map.values().stream()).anyMatch(s -> s.equals(value));
    }

    public Optional<Position> find(String s){
        for(Integer i : inner.keySet()){
            for(Integer j : inner.get(i).keySet()){
                if(inner.get(i).get(j).equals(s)){
                    return Optional.of(new Position(j, i));
                }
            }
        }
        return Optional.empty();
    }
}
