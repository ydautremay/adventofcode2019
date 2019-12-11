package dyve.aoc.day.day11;

public class Panel {

    final Point p;

    int color = 0;

    public Panel(Point p) {
        this.p = p;
    }

    public String toString(){
        return color == 0 ? "." : "#";
    }
}
