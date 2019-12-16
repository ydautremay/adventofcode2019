package dyve.aoc.day.day12;

import java.util.Objects;

public class Moon {

    Vector position;

    Vector velocity;

    public void applyGravity(Moon m){
        this.velocity.x += Integer.compare(m.position.x, this.position.x);
        this.velocity.y += Integer.compare(m.position.y, this.position.y);
        this.velocity.z += Integer.compare(m.position.z, this.position.z);
    }

    public void applyVelocity(){
        position.x += velocity.x;
        position.y += velocity.y;
        position.z += velocity.z;
    }

    public int potentialEnergy(){
        return position.squaredNorm();
    }

    public int kineticEnergy(){
        return velocity.squaredNorm();
    }

    public int totalEnergy(){
        return potentialEnergy() * kineticEnergy();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return position.equals(moon.position) &&
                velocity.equals(moon.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, velocity);
    }

    @Override
    public String toString() {
        return "Moon{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }
}
