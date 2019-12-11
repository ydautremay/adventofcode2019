package dyve.aoc.day.day10;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Vector {

    int x;

    int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Point A, Point B){
        this.x = B.x - A.x;
        this.y = B.y - A.y;
    }

    public double slope(){
        if(x == 0){
            return Math.signum(y)*Double.POSITIVE_INFINITY;
        }
        return (double)y/(double)x;
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
