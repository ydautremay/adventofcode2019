package dyve.aoc.day.day10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Matrix<T> implements Iterable<T> {

    int width;

    int height;

    List<List<T>> inner;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        inner = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<T> temp = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                temp.add(null);
            }
            inner.add(temp);
        }
    }

    public T get(int x, int y) {
        return inner.get(y).get(x);
    }

    public void set(int x, int y, T element) {
        inner.get(y).set(x, element);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (List<T> line : inner) {
            for (T element : line) {
                output.append(element == null ? " .. " : element);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public Stream<T> stream(){
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        int cursorX;

        int cursorY;

        @Override
        public boolean hasNext() {
            return cursorY != height && cursorX != width;
        }

        @Override
        public T next() {
            if(cursorY >= height && cursorX >= width){
                throw new NoSuchElementException();
            }
            T result = inner.get(cursorY).get(cursorX);
            cursorX++;
            if(cursorX == width){
                cursorX = 0;
                cursorY++;
            }
            return result;
        }
    }
}
