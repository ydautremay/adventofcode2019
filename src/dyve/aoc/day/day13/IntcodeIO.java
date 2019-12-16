package dyve.aoc.day.day13;

import java.util.ArrayList;
import java.util.List;

public class IntcodeIO {

    List<Integer> outputs = new ArrayList<>(3);

    int score;

    Matrix matrix;

    Position paddle;

    Position ball;

    public IntcodeIO(Matrix matrix) {
        this.matrix = matrix;
    }

    public void update(){
    }

    public int giveInput(){
        paddle = matrix.find(3);
        ball = matrix.find(4);
        System.out.println(matrix);
        System.out.println("SCORE : " + score);
        System.out.println("------------------------------------------------------------");
        return Integer.compare(ball.x, paddle.x);
    }

    public void giveOutput(long output){
        outputs.add((int)output);
        if(outputs.size() == 3){
            int x = outputs.get(0);
            int y = outputs.get(1);
            int element = outputs.get(2);
            if(x == -1 && y == 0){
                score = element;
            }else {
                matrix.set(x, y, element);
            }
            outputs.clear();
        }
    }
}
