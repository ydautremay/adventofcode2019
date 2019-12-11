package dyve.aoc.day.day10;

import java.math.BigInteger;

public class Vector {

    int x;

    int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector reduce(){
        BigInteger num = BigInteger.valueOf(y);
        int gcd = num.gcd(BigInteger.valueOf(x)).intValue();
        return new Vector(x/gcd, y/gcd);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
