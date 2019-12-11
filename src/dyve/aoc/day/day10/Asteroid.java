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

    public void findVisible(Matrix<Asteroid> asteroids){
        visible = new HashSet<>();
        hidden = new HashSet<>();
        for(Asteroid other : asteroids){
            if(other == null || this.equals(other)){
                continue;
            }
            Vector v = new Vector(other.p.x - this.p.x, other.p.y - this.p.y);
            v = v.reduce();
            Point current = this.p;
            Asteroid first = null;
            current = current.add(v);
            while(current.x >= 0 && current.y >= 0 && current.x < asteroids.width && current.y < asteroids.height){
                Asteroid asteroid = asteroids.get(current.x, current.y);
                if(first == null){
                    if(asteroid != null){
                        first = asteroid;
                        this.visible.add(asteroid);
                    }
                }else{
                    if(asteroid != null){
                        this.hidden.add(asteroid);
                    }
                }
                current = current.add(v);
            }
        }
    }

    public String toString(){
        return p.toString();
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
