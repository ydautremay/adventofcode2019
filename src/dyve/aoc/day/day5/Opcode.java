package dyve.aoc.day.day5;

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
            case 1 -> p.write(params[2].value, params[0].readParam(p) + params[1].readParam(p));
            case 2 -> p.write(params[2].value, params[0].readParam(p) * params[1].readParam(p));
            case 3 -> p.write(params[0].value, p.input);
            case 4 -> System.out.println("Output : " + params[0].readParam(p));
            case 5 -> {
                if(params[0].readParam(p) != 0){
                    p.cursor = params[1].readParam(p);
                }
            }
            case 6 -> {
                if(params[0].readParam(p) == 0){
                    p.cursor = params[1].readParam(p);
                }
            }
            case 7 -> {
                if(params[0].readParam(p) < params[1].readParam(p)){
                    p.write(params[2].value, 1);
                }else{
                    p.write(params[2].value, 0);
                }
            }
            case 8 -> {
                if(params[0].readParam(p) == params[1].readParam(p)){
                    p.write(params[2].value, 1);
                }else{
                    p.write(params[2].value, 0);
                }
            }
        }
    }

    public static int nbParams(int operationId){
        return switch (operationId){
            case 1, 2, 7, 8 -> 3;
            case 3, 4 -> 1;
            case 5, 6 -> 2;
            default -> -1;
        };
    }

    public String toString(){
        return operationId + ", " + Arrays.stream(params).map(Param::toString).collect(Collectors.joining(", "));
    }
}
