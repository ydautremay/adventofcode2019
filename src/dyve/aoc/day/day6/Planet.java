package dyve.aoc.day.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Planet {

    String name;

    Set<Planet> orbitingPlanets = new HashSet<>();

    Planet parent;

    public Planet(String name) {
        this.name = name;
    }

    public int computeOrbits(int currentOrbits){
        if(parent == null){
            return currentOrbits;
        }
        currentOrbits++;
        return parent.computeOrbits(currentOrbits);
    }

    public void pathToHead(List<Planet> currentPath){
        if(parent == null){
            return;
        }
        currentPath.add(parent);
        parent.pathToHead(currentPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return name.equals(planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                '}';
    }
}
