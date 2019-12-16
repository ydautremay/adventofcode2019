package dyve.aoc.day.day12;

import java.util.Objects;

import static java.lang.Integer.compare;

public class State {

    int x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4, vx1, vy1, vz1, vx2, vy2, vz2, vx3, vy3, vz3, vx4, vy4, vz4;

    public State step(){
        State state = new State();
        state.vx1 = vx1 + compare(x2, x1) + compare(x3, x1) + compare(x4, x1);
        state.vx2 = vx2 + compare(x1, x2) + compare(x3, x2) + compare(x4, x2);
        state.vx3 = vx3 + compare(x1, x3) + compare(x2, x3) + compare(x4, x3);
        state.vx4 = vx4 + compare(x1, x4) + compare(x2, x4) + compare(x3, x4);

        state.vy1 = vy1 + compare(y2, y1) + compare(y3, y1) + compare(y4, y1);
        state.vy2 = vy2 + compare(y1, y2) + compare(y3, y2) + compare(y4, y2);
        state.vy3 = vy3 + compare(y1, y3) + compare(y2, y3) + compare(y4, y3);
        state.vy4 = vy4 + compare(y1, y4) + compare(y2, y4) + compare(y3, y4);

        state.vz1 = vz1 + compare(z2, z1) + compare(z3, z1) + compare(z4, z1);
        state.vz2 = vz2 + compare(z1, z2) + compare(z3, z2) + compare(z4, z2);
        state.vz3 = vz3 + compare(z1, z3) + compare(z2, z3) + compare(z4, z3);
        state.vz4 = vz4 + compare(z1, z4) + compare(z2, z4) + compare(z3, z4);
        
        state.x1 = x1 + state.vx1;
        state.x2 = x2 + state.vx2;
        state.x3 = x3 + state.vx3;
        state.x4 = x4 + state.vx4;

        state.y1 = y1 + state.vy1;
        state.y2 = y2 + state.vy2;
        state.y3 = y3 + state.vy3;
        state.y4 = y4 + state.vy4;

        state.z1 = z1 + state.vz1;
        state.z2 = z2 + state.vz2;
        state.z3 = z3 + state.vz3;
        state.z4 = z4 + state.vz4;
        return state;
    }

    public String reduce(){
        return "" + x1 + y1 + z1 + x2 + y2 + z2 + x3 + y3 + z3 + x4 + y4 + z4 + vx1 + vy1 + vz1 + vx2 + vy2 + vz2 + vx3 + vy3 + vz3 + vx4 + vy4 + vz4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x1 == state.x1 &&
                y1 == state.y1 &&
                z1 == state.z1 &&
                x2 == state.x2 &&
                y2 == state.y2 &&
                z2 == state.z2 &&
                x3 == state.x3 &&
                y3 == state.y3 &&
                z3 == state.z3 &&
                x4 == state.x4 &&
                y4 == state.y4 &&
                z4 == state.z4 &&
                vx1 == state.vx1 &&
                vy1 == state.vy1 &&
                vz1 == state.vz1 &&
                vx2 == state.vx2 &&
                vy2 == state.vy2 &&
                vz2 == state.vz2 &&
                vx3 == state.vx3 &&
                vy3 == state.vy3 &&
                vz3 == state.vz3 &&
                vx4 == state.vx4 &&
                vy4 == state.vy4 &&
                vz4 == state.vz4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4, vx1, vy1, vz1, vx2, vy2, vz2, vx3, vy3, vz3, vx4, vy4, vz4);
    }

    @Override
    public String toString() {
        return "State{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", z1=" + z1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", z2=" + z2 +
                ", x3=" + x3 +
                ", y3=" + y3 +
                ", z3=" + z3 +
                ", x4=" + x4 +
                ", y4=" + y4 +
                ", z4=" + z4 +
                ", vx1=" + vx1 +
                ", vy1=" + vy1 +
                ", vz1=" + vz1 +
                ", vx2=" + vx2 +
                ", vy2=" + vy2 +
                ", vz2=" + vz2 +
                ", vx3=" + vx3 +
                ", vy3=" + vy3 +
                ", vz3=" + vz3 +
                ", vx4=" + vx4 +
                ", vy4=" + vy4 +
                ", vz4=" + vz4 +
                '}';
    }
}
