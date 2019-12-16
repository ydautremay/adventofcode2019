package dyve.aoc.day.day13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Matrix implements Iterable<Integer> {

    int width;

    int height;

    List<List<Integer>> inner;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        inner = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                temp.add(null);
            }
            inner.add(temp);
        }
    }

    public Integer get(int x, int y) {
        return inner.get(y).get(x);
    }

    public void set(int x, int y, Integer element) {
        inner.get(y).set(x, element);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (List<Integer> line : inner) {
            for (Integer element : line) {
                if(element == null)
                    continue;
                output.append(switch (element){
                    case 0 -> " ";
                    case 1 -> "8";
                    case 2 -> "#";
                    case 3 -> "_";
                    case 4 -> "O";
                    default -> "!";
                });
            }
            output.append("\n");
        }
        return output.toString();
    }

    public Position find(int search){
        for (int i = 0; i < height; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                if(get(j, i) == null)
                    continue;
                if(search == get(j, i))
                    return new Position(j, i);
            }
        }
        return null;
    }

    public Stream<Integer> stream(){
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Integer> {

        int cursorX;

        int cursorY;

        @Override
        public boolean hasNext() {
            return cursorY != height && cursorX != width;
        }

        @Override
        public Integer next() {
            if(cursorY >= height && cursorX >= width){
                throw new NoSuchElementException();
            }
            Integer result = inner.get(cursorY).get(cursorX);
            cursorX++;
            if(cursorX == width){
                cursorX = 0;
                cursorY++;
            }
            return result;
        }
    }
}
