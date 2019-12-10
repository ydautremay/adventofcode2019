package dyve.aoc.day.day3;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part1 {

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("3"));
        //instance.execute(List.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
    }

    protected void execute(List<String> input) {
        Trajectory t1 = new Trajectory(input.get(0));
        Trajectory t2 = new Trajectory(input.get(1));
        List<Point> intersections = t1.intersections(t2);
        intersections.forEach(p -> System.out.println(p.x + ", " + p.y));
        System.out.println(intersections.stream().mapToInt(Point::distanceFromO).min().orElse(Integer.MAX_VALUE));
    }
}
