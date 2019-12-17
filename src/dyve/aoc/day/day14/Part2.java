package dyve.aoc.day.day14;

import dyve.aoc.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    Map<String, Component> components = new HashMap<>();

    boolean enoughOre = true;

    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("14"));
        //instance.execute(InputReader.readInput("14test"));
        //instance.execute(List.of("10 ORE => 3 A", "1 ORE => 7 B", "7 A, 1 B => 1 FUEL"));
    }

    protected void execute(List<String> input) {
        readInput(input);
        Stock stock = new Stock();
        stock.put("ORE", 1000000000000L);
        Component fuel = components.get("FUEL");
        long fuelQuantity = 1000000L;
        while(enoughOre || fuelQuantity != 0) {
            System.out.println(stock);
            Stock copy = new Stock(stock);
            cook(fuel, fuelQuantity, copy);
            if(!enoughOre){
                recycle(stock);
                if(fuelQuantity == 1){
                    fuelQuantity = 0;
                }else{
                    fuelQuantity /= 10;
                    System.out.println("Fuel quantity : " + fuelQuantity);
                    enoughOre = true;
                }
            }else{
                stock = copy;
            }
        }
        System.out.println(stock.get("FUEL"));
    }

    private void cook(Component component, long quantity, Stock stock){
        component.recipe.cook(quantity, stock).ifPresent(ingredient -> {
            if(ingredient.component.name.equals("ORE")){
                enoughOre = false;
            } else {
                cook(ingredient.component, ingredient.quantity, stock);
            }
        });
    }

    private void recycle(Stock stock){
        Stock copy = new Stock();
        while(!copy.equals(stock)) {
            copy = new Stock(stock);
            System.out.println("RECYCLING : " + stock);
            stock.keySet().stream().filter(k -> !k.equals("ORE") && !k.equals("FUEL")).forEach(k -> components.get(k).recipe.recycle(stock.get(k), stock));
            System.out.println("RECYCLED : " + stock);
        }
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
            //System.out.println(recipe);
        }
    }
}
