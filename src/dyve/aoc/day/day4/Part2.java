package dyve.aoc.day.day4;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute("284639-748759");
    }

    protected void execute(String input) {
        String[] strBounds = input.split("-");
        int result = 0;
        int min = Integer.parseInt(strBounds[0]);
        int max = Integer.parseInt(strBounds[1]);
        for(int i = min; i <= max; i++){
            if(doubleDigits(i) && noDecrease(i)){
                result++;
            }
        }
        System.out.println(result);
    }

    private boolean doubleDigits(int input){
        String rep = String.valueOf(input);
        char c = rep.charAt(0);
        int length = 1;
        for(int i = 1; i < rep.length(); i++){
            char t = rep.charAt(i);
            if(t == c){
                length++;
            }else{
                if(length == 2){
                    return true;
                }
                c = t;
                length = 1;
            }
        }
        if(length == 2){
            return true;
        }
        return false;
    }

    private boolean noDecrease(int input){
        String rep = String.valueOf(input);
        for(int i = 0; i < rep.length() - 1; i++){
            String string;
            int i1 = Integer.parseInt(String.valueOf(rep.charAt(i)));
            int i2 = Integer.parseInt(String.valueOf(rep.charAt(i + 1)));
            if(i1 > i2){
                return false;
            }
        }
        return true;
    }
}
