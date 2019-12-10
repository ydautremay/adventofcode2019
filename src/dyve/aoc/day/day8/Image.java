package dyve.aoc.day.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Image {

    private int width;

    private int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
    }

    List<Layer> layers = new ArrayList<>();

    Layer decodedLayer;

    public String toString(){
        return layers.stream().map(Layer::toString).collect(Collectors.joining("********************\n"));
    }

    public void squash(){
        decodedLayer = new Layer();
        for(int i = 0; i < height; i++){
            decodedLayer.lines.add(new PixelLine());
        }
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                //For each pixel get value in each layer
                for(Layer layer : layers){
                    int value = layer.lines.get(j).pixels.get(i);
                    if(value != 2){
                        decodedLayer.lines.get(j).pixels.add(value);
                        break;
                    }
                }
            }
        }
    }

}
