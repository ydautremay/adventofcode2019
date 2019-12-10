package dyve.aoc.day.day2;

import java.util.List;

public class Program {

    List<Integer> program;

    int cursor = 0;

    public Program(List<Integer> program){
        this.program = program;
    }

    public int execute(int noun, int verb){
        write(1, noun);
        write(2, verb);
        while(hasNext()){
            Opcode opcode = next();
            int value = opcode.compute();
            write(opcode.outputPosition, value);
        }
        return read(0);
    }

    public boolean hasNext(){
        return program.get(cursor) != 99;
    }

    public Opcode next(){
        Integer operationId = program.get(cursor);
        Integer operand1 = program.get(program.get(cursor + 1));
        Integer operand2 = program.get(program.get(cursor + 2));
        Integer outputPosition = program.get(cursor + 3);
        Opcode opcode =  new Opcode(operationId, operand1, operand2, outputPosition);
        cursor += 4;
        return opcode;
    }

    public void write(int position, int value){
        if(position < program.size()) {
            program.set(position, value);
        }
    }

    public int read(int position){
        return program.get(position);
    }

    public String toString(){
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int value : program) {
            index++;
            sb.append(value + ",");
            if(index%4 == 0){
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
