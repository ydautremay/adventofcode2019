package dyve.aoc.day.day3;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("3"));
        //instance.execute(List.of("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"));
    }

    protected void execute(List<String> input) {
        Trajectory t1 = new Trajectory(input.get(0));
        Trajectory t2 = new Trajectory(input.get(1));
        List<Point> intersections = t1.intersections(t2);
        System.out.println(intersections.stream().mapToInt(p -> t1.walkTo(p) + t2.walkTo(p)).min().orElse(Integer.MAX_VALUE));
    }
}
