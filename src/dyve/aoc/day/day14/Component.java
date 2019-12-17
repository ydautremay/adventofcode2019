package dyve.aoc.day.day14;

public class Component {

    String name;

    Recipe recipe;

    public Component(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
