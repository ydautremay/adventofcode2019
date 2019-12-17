package dyve.aoc.day.day14;

public class Ingredient {

    long quantity;

    Component component;

    public Ingredient(Component component, long quantity) {
        this.quantity = quantity;
        this.component = component;
    }

    @Override
    public String toString() {
        return quantity + " " + component;
    }
}
