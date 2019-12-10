package dyve.aoc.day.day9;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Program {

    int cursor = 0;

    int input;

    int relativeBase = 0;

    String output = "";

    long[] program = new long[1024];

    public Program(List<String> program, int input){
        for(int i = 0; i < program.size(); i++){
            this.program[i] = Long.parseLong(program.get(i));
        }
        this.input = input;
    }

    public void execute(){
        while(hasNext()){
            Opcode opcode = next();
            System.out.println(opcode);
            opcode.compute(this);
            //System.out.println(Arrays.stream(program).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    public boolean hasNext(){
        return program[cursor] != 99;
    }

    public Opcode next(){
        String operationCode = String.valueOf(program[cursor]);
        //System.out.println(operationCode);
        cursor++;
        if(operationCode.length() == 1){
            operationCode = "0" + operationCode;
        }
        List<Integer> operationAndModes = operationCode.toString().chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
        int operationId = operationAndModes.get(operationAndModes.size() - 2) * 10 + operationAndModes.get(operationAndModes.size() - 1);
        int nbParams = Opcode.nbParams(operationId);
        int delta = nbParams - operationAndModes.size() + 2;
        for(int i = 0; i < delta; i++){
            operationAndModes.add(0, 0);
        }
        Param[] params = new Param[nbParams];
        for(int i = operationAndModes.size() - 3; i >= 0; i--){
            params[params.length - i - 1] = new Param(program[cursor], operationAndModes.get(i));
            cursor++;
        }
        Opcode opcode =  new Opcode(operationId, params);
        //System.out.println("Opcode : " + opcode);
        return opcode;
    }

    public void write(int position, long value){
        this.program[position] = value;
    }

    public long read(int position){
        return program[position];
    }
}
