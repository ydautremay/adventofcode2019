package dyve.aoc.day.day8;

import dyve.aoc.input.InputReader;

import java.util.List;

public class Part1 {

    static int WIDTH = 25;
    static int HEIGHT = 6;

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
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
        System.out.println(image);

        Layer min0Layer = null;
        int min0 = Integer.MAX_VALUE;
        for(Layer layer : image.layers){
            int zeroCount = 0 ;
            for (PixelLine line : layer.lines){
                zeroCount += line.pixels.stream().mapToInt(Integer::intValue).filter(i -> i == 0).count();
            }
            if(zeroCount < min0){
                min0 = zeroCount;
                min0Layer = layer;
            }
        }
        int oneCount = 0;
        int twoCount = 0;
        for(PixelLine line : min0Layer.lines){
            oneCount += line.pixels.stream().mapToInt(Integer::intValue).filter(i -> i == 1).count();
            twoCount += line.pixels.stream().mapToInt(Integer::intValue).filter(i -> i == 2).count();
        }
        System.out.println(oneCount * twoCount);
    }
}
