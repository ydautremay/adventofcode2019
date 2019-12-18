package dyve.aoc.day.day17;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Opcode {

    int operationId;

    Param[] params;

    public Opcode(int operationId, Param... params) {
        this.operationId = operationId;
        this.params = params;
    }

    public void compute(Program p, Supplier<Integer> input, Consumer<Long> output){
        switch (operationId){
            case 1 -> {
                p.write(params[2].readPosition(p), params[0].readParam(p) + params[1].readParam(p));
//                System.out.println("write " + (params[0].readParam(p) + params[1].readParam(p)) + " at " + params[2].readPosition(p));
            }
            case 2 -> {
                p.write((int) params[2].readPosition(p), params[0].readParam(p) * params[1].readParam(p));
//                System.out.println("write " + (params[0].readParam(p) * params[1].readParam(p)) + " at " + params[2].readPosition(p));
            }
            case 3 -> {
                int in = input.get();
                if(in == Integer.MAX_VALUE){
                    p.write(p.cursor, 99);
                }else {
                    p.write((int) params[0].readPosition(p), in);
//                System.out.println("Write input " + input.get() + " at " + p.read((int) params[0].readPosition(p)));
                }
            }
            case 4 -> {
                output.accept(params[0].readParam(p));
//                System.out.println("Output " + params[0].readParam(p));
            }
            case 5 -> {
                if(params[0].readParam(p) != 0){
                    p.cursor = (int) params[1].readParam(p);
//                    System.out.println(params[0].readParam(p) + " != 0, jump to " + params[1].readParam(p));
                }else{
//                    System.out.println(params[0].readParam(p) + " == 0, do nothing");
                }
            }
            case 6 -> {
                if(params[0].readParam(p) == 0){
//                    System.out.println(params[0].readParam(p) + " == 0, jump to " + params[1].readParam(p));
                    p.cursor = (int) params[1].readParam(p);
                }else{
//                    System.out.println(params[0].readParam(p) + " != 0, do nothing");
                }
            }
            case 7 -> {
                if(params[0].readParam(p) < params[1].readParam(p)){
//                    System.out.println(params[0].readParam(p) + " < " + params[1].readParam(p) + " : write 1 at " + params[2].readPosition(p));
                    p.write((int) params[2].readPosition(p), 1);
                }else{
//                    System.out.println(params[0].readParam(p) + " >= " + params[1].readParam(p) + " : write 0 at " + params[2].readPosition(p));
                    p.write((int) params[2].readPosition(p), 0);
                }
            }
            case 8 -> {
                if(params[0].readParam(p) == params[1].readParam(p)){
//                    System.out.println(params[0].readParam(p) + " == " + params[1].readParam(p) + " : write 1 at " + params[2].readPosition(p));
                    p.write((int) params[2].readPosition(p), 1);
                }else{
//                    System.out.println(params[0].readParam(p) + " != " + params[1].readParam(p) + " : write 0 at " + params[2].readPosition(p));
                    p.write((int) params[2].readPosition(p), 0);
                }
            }
            case 9 -> {
                p.relativeBase += params[0].readParam(p);
//                System.out.println("Add " + params[0].readParam(p) + " to relative base. Now " + p.relativeBase);
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
