package dyve.aoc.day.day17;

import java.util.ArrayList;
import java.util.List;

public class IntcodeIO {

    Matrix matrix;

    String routine = "A,B,A,C,A,B,C,C,A,B";

    String fA = "R,8,L,10,R,8";
    String fB = "R,12,R,8,L,8,L,12";
    String fC = "L,12,L,10,L,8";

    int inputIndex = 0;
    int charIndex = 0 ;

    List<String> inputs = new ArrayList<>();

    int lastOutput;

//    int cursorX = 0;

//    int cursorY = 0;

    public IntcodeIO(Matrix matrix) {
        this.matrix = matrix;
        inputs.add(routine);
        inputs.add(fA);
        inputs.add(fB);
        inputs.add(fC);
        inputs.add("n");
    }

    public int giveInput(){
        String input = inputs.get(inputIndex);
        if(charIndex == input.length()){
            charIndex = 0;
            inputIndex++;
            return '\n';
        }
        int result = input.charAt(charIndex);
        charIndex++;
        return result;
    }

    public void giveOutput(int output){
        //matrix.set(cursorX, cursorY, String.valueOf((char) output));
        lastOutput = output;
        System.out.print((char) output);
    }
}
