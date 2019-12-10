package dyve.aoc.day.day9;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Opcode {

    int operationId;

    Param[] params;

    public Opcode(int operationId, Param... params) {
        this.operationId = operationId;
        this.params = params;
    }

    public void compute(Program p){
        switch (operationId){
            case 1 -> {
                p.write((int) params[2].value, params[0].readParam(p) + params[1].readParam(p));
                System.out.println("write " + p.read((int) params[2].value) + " at " + params[2].value);
            }
            case 2 -> {
                p.write((int) params[2].value, params[0].readParam(p) * params[1].readParam(p));
                System.out.println("write " + p.read((int) params[2].value) + " at " + params[2].value);
            }
            case 3 -> {
                p.write((int) params[0].value, p.input);
                System.out.println("Write input " + p.input + " at " + p.read((int) params[0].value));
            }
            case 4 -> {
                p.output += params[0].readParam(p);
                System.out.println("Output " + params[0].readParam(p));
            }
            case 5 -> {
                if(params[0].readParam(p) != 0){
                    p.cursor = (int) params[1].readParam(p);
                    System.out.println(params[0].readParam(p) + " != 0, jump to " + params[1].readParam(p));
                }
            }
            case 6 -> {
                if(params[0].readParam(p) == 0){
                    p.cursor = (int) params[1].readParam(p);
                }
            }
            case 7 -> {
                if(params[0].readParam(p) < params[1].readParam(p)){
                    p.write((int) params[2].value, 1);
                }else{
                    p.write((int) params[2].value, 0);
                }
            }
            case 8 -> {
                if(params[0].readParam(p) == params[1].readParam(p)){
                    p.write((int) params[2].value, 1);
                }else{
                    p.write((int) params[2].value, 0);
                }
            }
            case 9 -> {
                p.relativeBase += params[0].readParam(p);
            }
        }
    }

    public static int nbParams(int operationId){
        return switch (operationId){
            case 1, 2, 7, 8 -> 3;
            case 3, 4, 9 -> 1;
            case 5, 6 -> 2;
            default -> -1;
        };
    }

    public String toString(){
        return operationId + ", " + Arrays.stream(params).map(Param::toString).collect(Collectors.joining(", "));
    }
}