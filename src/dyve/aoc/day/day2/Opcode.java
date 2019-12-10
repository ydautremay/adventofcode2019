package dyve.aoc.day.day2;

public class Opcode {

    int operationId;

    int operand1;

    int operand2;

    int outputPosition;

    public Opcode(int operationId, int operand1, int operand2, int outputPosition) {
        this.operationId = operationId;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.outputPosition = outputPosition;
    }

    public int compute(){
        return switch (operationId){
            case 1 -> operand1 + operand2;
            case 2 -> operand1 * operand2;
            default -> -1;
        };
    }

    public String toString(){
        return operationId + ", " + operand1 + ", " + operand2 + ", " + outputPosition;
    }
}
