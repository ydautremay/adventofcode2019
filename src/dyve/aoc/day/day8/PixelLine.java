package dyve.aoc.day.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PixelLine {

    List<Integer> pixels = new ArrayList<>();

    public String toString(){
        return pixels.stream().map(String::valueOf).collect(Collectors.joining(""));
    }
}
