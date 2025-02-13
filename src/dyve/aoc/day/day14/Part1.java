package dyve.aoc.day.day14;

import dyve.aoc.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    Map<String, Component> components = new HashMap<>();

    int nbOre = 0;

    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("14"));
        //instance.execute(InputReader.readInput("14test"));
        //instance.execute(List.of("10 ORE => 5 A", "1 ORE => 2 B", "7 A, 1 B => 1 FUEL"));
    }

    protected void execute(List<String> input) {
        readInput(input);
        Stock stock = new Stock();
        Component fuel = components.get("FUEL");
        while(stock.computeIfAbsent("FUEL", k -> 0L) == 0) {
            cook(fuel, 1, stock);
        }
        System.out.println(nbOre);
    }

    private void cook(Component component, long quantity, Stock stock){
        component.recipe.cook(quantity, stock).ifPresent(ingredient -> {
            if(ingredient.component.name.equals("ORE")){
                nbOre += ingredient.quantity;
                stock.compute(ingredient.component.name, (name, qty) -> qty == null ? ingredient.quantity : qty + ingredient.quantity);
                cook(component, quantity, stock);
            } else {
                cook(ingredient.component, ingredient.quantity, stock);
            }
        });
    }

    private void readInput(List<String> input){
        Pattern pattern = Pattern.compile("(([0-9]+)\\s([A-Z]+)+?)(?:,|$)");
        for(String line : input){
            String[] sep = line.split(" => ");
            Matcher matcherEnd = pattern.matcher(sep[1]);
            matcherEnd.matches();
            Component product = components.computeIfAbsent(matcherEnd.group(3), k -> new Component(matcherEnd.group(3)));
            Recipe recipe = new Recipe(product, Integer.parseInt(matcherEnd.group(2)));
            product.recipe = recipe;
            Matcher matcherBegin = pattern.matcher(sep[0]);
            while(matcherBegin.find()){
                Component part = components.computeIfAbsent(matcherBegin.group(3), k -> new Component(matcherBegin.group(3)));
                Ingredient ingredient = new Ingredient(part, Integer.parseInt(matcherBegin.group(2)));
                recipe.ingredients.put(part.name, ingredient);
            }
            System.out.println(recipe);
        }
    }
}
