package dyve.aoc.day.day11;

import java.util.HashSet;
import java.util.Set;

public class Robot {

    Point p;

    Direction direction = Direction.UP;

    Matrix surface;

    Set<Point> passedPanels = new HashSet<>();

    boolean firstOutput = true;

    public Robot(int width, int height, int color) {
        surface = new Matrix(width, height);
        p = new Point(width/2, height/2);
        Panel panel = new Panel(new Point(p.x, p.y));
        panel.color = color;
        surface.set(p.x, p.y, panel);
        passedPanels.add(new Point(p.x, p.y));
    }

    public int getInput(){
        return surface.get(p.x, p.y).color;
    }

    public void readOutput(int output){
        if(firstOutput){
            surface.get(p.x, p.y).color = output;
            firstOutput = false;
        }else{
            direction = direction.turn(output);
            switch (direction){
                case UP -> p.y++;
                case DOWN -> p.y--;
                case LEFT -> p.x--;
                case RIGHT -> p.x++;
            }
            passedPanels.add(new Point(p.x, p.y));
            firstOutput = true;
        }
    }
}
