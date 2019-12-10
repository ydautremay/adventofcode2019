package dyve.aoc.day.day3;

import java.util.Objects;

public class Point {

    int x;

    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int distanceFromO(){
        return distanceFrom(new Point(0, 0));
    }

    public int distanceFrom(Point p){
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
