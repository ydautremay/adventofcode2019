package dyve.aoc.day.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trajectory {

    List<Segment> segments;

    public Trajectory(String desc){
        segments = new ArrayList<>();
        Arrays.stream(desc.split(",")).forEach(this::go);
    }

    public void go(String command){
        String direction = command.substring(0, 1);
        int distance = Integer.parseInt(command.substring(1));
        Point p;
        if(segments.isEmpty()){
            p = new Point(0, 0);
        }else{
            p = segments.get(segments.size() - 1).end();
        }
        switch (direction){
            case "U"-> up(p, distance);
            case "D"-> down(p, distance);
            case "R"-> right(p, distance);
            case "L"-> left(p, distance);
        }
    }

    public void up(Point p, int distance){
        segments.add(new Segment(p, Direction.UP, distance));
    }

    public void down(Point p, int distance){
        segments.add(new Segment(p, Direction.DOWN, distance));
    }

    public void right(Point p, int distance){
        segments.add(new Segment(p, Direction.RIGHT, distance));
    }

    public void left(Point p, int distance){
        segments.add(new Segment(p, Direction.LEFT, distance));
    }

    public int walkTo(Point p){
        int steps = 0;
        boolean reached = false;
        for(Segment s : segments){
            if(reached){
                break;
            }
            if(s.contains(p)){
                steps += s.point.distanceFrom(p);
                reached = true;
            }else{
                steps += s.length;
            }
        }
        return reached ? steps : Integer.MAX_VALUE;
    }

    public List<Point> intersections(Trajectory t){
        List<Point> intersections = new ArrayList<>();
        for(Segment s1 : segments){
            for(Segment s2 : t.segments){
                s1.intersection(s2).ifPresent(intersections::add);
            }
        }
        return intersections;
    }
}
