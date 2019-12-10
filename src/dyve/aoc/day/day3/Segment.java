package dyve.aoc.day.day3;

import java.util.Objects;
import java.util.Optional;

public class Segment {

    Point point;

    Direction direction;

    int length;

    public Segment(Point p1, Direction direction, int length){
        this.point = p1;
        this.direction = direction;
        this.length = length;
    }

    boolean isHorizontal(){
        return direction == Direction.RIGHT || direction == Direction.LEFT;
    }

    boolean isVertical(){
        return direction == Direction.UP || direction == Direction.DOWN;
    }

    public boolean contains(Point p){
        return switch (direction){
            case UP -> p.x == this.point.x && p.y > this.point.y && p.y < this.point.y + length;
            case DOWN -> p.x == this.point.x && p.y < this.point.y && p.y > this.point.y - length;
            case RIGHT -> p.y == this.point.y && p.x > this.point.x && p.x < this.point.x + length;
            case LEFT -> p.y == this.point.y && p.x < this.point.x && p.x > this.point.x - length;
        };
    }

    public Optional<Point> intersection(Segment s){
        if((s.isHorizontal() && this.isHorizontal()) || (s.isVertical() && this.isVertical())){
            return Optional.empty();
        }
        Point p;
        if(s.isHorizontal()) {
            p = new Point(this.point.x, s.point.y);
        } else {
            p = new Point(s.point.x, this.point.y);
        }
        if(s.contains(p) && this.contains(p)) {
            return Optional.of(p);
        } else {
            return Optional.empty();
        }
    }

    public Point end(){
        return switch (direction){
            case UP -> new Point(point.x, point.y + length);
            case DOWN -> new Point(point.x, point.y - length);
            case RIGHT -> new Point(point.x + length, point.y);
            case LEFT -> new Point(point.x - length, point.y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return length == segment.length &&
                point.equals(segment.point) &&
                direction == segment.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, direction, length);
    }
}
