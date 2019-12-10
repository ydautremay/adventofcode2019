package dyve.aoc.day.day8;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part2 {

    static int WIDTH = 25;
    static int HEIGHT = 6;

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("8"));
    }

    protected void execute(List<String> input) {
        String code = input.get(0);
        Image image = new Image(WIDTH, HEIGHT);
        Layer currentLayer = new Layer();
        image.layers.add(currentLayer);
        PixelLine currentLine = new PixelLine();
        currentLayer.lines.add(currentLine);
        int widthCursor = 0;
        int heightCursor = 0;
        for(char c : code.toCharArray()){
            Integer nb = Integer.valueOf("" + c);
            currentLine.pixels.add(nb);
            widthCursor++;
            if(widthCursor == WIDTH){
                widthCursor = 0;
                currentLine = new PixelLine();
                heightCursor++;
                if(heightCursor == HEIGHT){
                    heightCursor = 0;
                    currentLayer = new Layer();
                    image.layers.add(currentLayer);
                }
                currentLayer.lines.add(currentLine);
            }
        }
        image.layers.remove(image.layers.size() - 1);
        image.squash();
        System.out.println(image.decodedLayer);

    }
}
