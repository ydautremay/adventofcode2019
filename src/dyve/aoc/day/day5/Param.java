package dyve.aoc.day.day5;

public class Param {

    int value;

    int mode;

    public Param(int value, int mode) {
        this.value = value;
        this.mode = mode;
    }

    public int readParam(Program p){
        return switch (mode){
            case 0 -> p.read(value);
            case 1 -> value;
            default -> Integer.MAX_VALUE;
        };
    }

    public String toString(){
        return mode == 0 ? "#" + value : "" + value;
    }
}
