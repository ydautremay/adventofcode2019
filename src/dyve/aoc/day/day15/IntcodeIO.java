package dyve.aoc.day.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntcodeIO {

    int score;

    Matrix matrix;

    Position droid;

    Position start;

    Position o2 = new Position(-1, -1);

    int counter = 0;

    //Scanner scanner = new Scanner(System.in);

    int lastInput = 2;

    boolean bumped = true;

    public IntcodeIO(Matrix matrix, Position droid) {
        this.matrix = matrix;
        this.droid = droid;
        this.start = new Position(droid.x, droid.y);
        matrix.set(start.x, start.y, 4);
        //scanner.useDelimiter("");
    }

    public int giveInput(){
        boolean direction = false;
        String input = "";
//        while(!direction) {
//            input = scanner.next();
//            direction = input.equals("q") || input.equals("s") || input.equals("d") || input.equals("z") || input.equals("x");
//        }
        switch (lastInput){
            case 1 -> {
                if(bumped){
                    lastInput = 4;
                }else{
                    lastInput = 3;
                }
            }
            case 2 -> {
                if(bumped){
                    lastInput = 3;
                }else{
                    lastInput = 4;
                }
            }
            case 3 -> {
                if(bumped){
                    lastInput = 1;
                }else{
                    lastInput = 2;
                }
            }
            case 4 -> {
                if(bumped){
                    lastInput = 2;
                }else{
                    lastInput = 1;
                }
            }
        }
        counter++;
        if(counter == 3500){
            return Integer.MAX_VALUE;
        }
//        lastInput = switch (input){
//            case "z" -> 1;
//            case "s" -> 2;
//            case "q" -> 3;
//            case "d" -> 4;
//            default -> Integer.MAX_VALUE;
//        };
        return lastInput;
    }

    public void giveOutput(long output){
        Position newPosition = new Position(droid.x, droid.y);
        switch (lastInput) {
            case 1 -> newPosition.y += -1;
            case 2 -> newPosition.y += 1;
            case 3 -> newPosition.x += -1;
            case 4 -> newPosition.x += 1;
        }
        matrix.set(newPosition.x, newPosition.y, (int)output);
        if(output == 1){
            bumped = false;
            if(droid.equals(o2)){
                matrix.set(droid.x, droid.y, 2);
            }else if(droid.equals(start)){
                matrix.set(droid.x, droid.y, 4);
            }
            droid = newPosition;
//            matrix.set(droid.x, droid.y, 3);
        }else if (output == 2){
            bumped = false;
            o2.x = newPosition.x;
            o2.y = newPosition.y;
            droid = newPosition;
            matrix.set(droid.x, droid.y, 2);
//            matrix.set(droid.x, droid.y, 3);
            System.out.println("O2 Canister at " + o2);
        }else{
            bumped = true;
            matrix.set(newPosition.x, newPosition.y, 0);
        }
//        System.out.println("-----------------------");
//        System.out.println(matrix);
//        System.out.println("-----------------------");
    }
}
