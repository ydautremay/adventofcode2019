package dyve.aoc.day.day9;

public class Param {

    long value;

    int mode;

    public Param(long value, int mode) {
        this.value = value;
        this.mode = mode;
    }

    public long readParam(Program p){
        return switch (mode){
            case 0 -> p.read((int) value);
            case 1 -> value;
            case 2 -> p.read((int) value + p.relativeBase);
            default -> Long.MAX_VALUE;
        };
    }

    public String toString(){
        return mode == 0 ? "#" + value : "" + value;
    }
}
