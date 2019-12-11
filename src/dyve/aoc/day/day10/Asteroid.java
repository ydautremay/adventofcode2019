package dyve.aoc.day.day10;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Asteroid {

    Point p;

    Set<Asteroid> visible = new HashSet<>();

    Set<Asteroid> hidden = new HashSet<>();

    public Asteroid(int x, int y) {
        this.p = new Point(x, y);
    }

    public String toString(){
        return " " + visible.size() + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asteroid asteroid = (Asteroid) o;
        return p.equals(asteroid.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }
}
