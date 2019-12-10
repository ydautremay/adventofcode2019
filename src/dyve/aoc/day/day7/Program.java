package dyve.aoc.day.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    List<String> program;

    int cursor = 0;

    public Program(List<String> program){
        this.program = program;
    }

    public void execute(Amplifier a){
        while(hasNext()){
            Opcode opcode = next();
            opcode.compute(this, a);
        }
    }

    public boolean hasNext(){
        return read(cursor) != 99;
    }

    public Opcode next(){
        String operationCode = program.get(cursor);
        //System.out.println(operationCode);
        cursor++;
        if(operationCode.length() == 1){
            operationCode = "0" + operationCode;
        }
        List<Integer> operationAndModes = operationCode.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
        int operationId = operationAndModes.get(operationAndModes.size() - 2) * 10 + operationAndModes.get(operationAndModes.size() - 1);
        int nbParams = Opcode.nbParams(operationId);
        int delta = nbParams - operationAndModes.size() + 2;
        for(int i = 0; i < delta; i++){
            operationAndModes.add(0, 0);
        }
        Param[] params = new Param[nbParams];
        for(int i = operationAndModes.size() - 3; i >= 0; i--){
            params[params.length - i - 1] = new Param(read(cursor), operationAndModes.get(i));
            cursor++;
        }
        Opcode opcode =  new Opcode(operationId, params);
        //System.out.println("Opcode : " + opcode);
        return opcode;
    }

    public void write(int position, int value){
        if(position < program.size()) {
            program.set(position, String.valueOf(value));
        }
    }

    public int read(int position){
        return Integer.parseInt(program.get(position));
    }
}
