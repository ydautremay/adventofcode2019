package dyve.aoc.day.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Layer {

    List<PixelLine> lines = new ArrayList<>();

    public String toString(){
        return lines.stream().map(PixelLine::toString).collect(Collectors.joining("\n")) + "\n";
    }
}
