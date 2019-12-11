package dyve.aoc.day.day11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Matrix implements Iterable<Panel> {

    List<List<Panel>> inner;

    public Matrix(int width, int height) {
        inner = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<Panel> temp = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                temp.add(new Panel(new Point(j, i)));
            }
            inner.add(temp);
        }
    }

    public Panel get(int x, int y) {
        return inner.get(y).get(x);
    }

    public void set(int x, int y, Panel panel) {
        if(y >= inner.size()){
            while(inner.size() <= y){
                inner.add(new ArrayList<>());
            }
        }
        if(x >= inner.get(y).size()){
            while(inner.get(y).size() <= x){
                inner.get(y).add(new Panel(new Point(x, y)));
            }
        }
        inner.get(y).set(x, panel);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (List<Panel> line : inner) {
            for (Panel element : line) {
                output.append(element);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public Stream<Panel> stream(){
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    public Iterator<Panel> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Panel> {

        int cursorX;

        int cursorY;

        @Override
        public boolean hasNext() {
            return cursorY != inner.size() && cursorX != inner.get(inner.size() - 1).size();
        }

        @Override
        public Panel next() {
            if(cursorY >= inner.size() && cursorX >= inner.get(inner.size() - 1).size()){
                throw new NoSuchElementException();
            }
            Panel result = inner.get(cursorY).get(cursorX);
            cursorX++;
            if(cursorX == inner.get(cursorY).size()){
                cursorX = 0;
                cursorY++;
            }
            return result;
        }
    }
}
