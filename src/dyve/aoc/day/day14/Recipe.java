package dyve.aoc.day.day14;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Recipe {

    Map<String, Ingredient> ingredients = new HashMap<>();

    long nbProducts;

    Component product;

    public Recipe(Component product, int quantity) {
        this.product = product;
        this.nbProducts = quantity;
    }

    public Optional<Ingredient> cook(long quantity, Stock stock){
        long nbRecipe = BigDecimal.valueOf(quantity).divide(BigDecimal.valueOf(nbProducts), 0, RoundingMode.CEILING).longValueExact();
        for (Ingredient ingredient : ingredients.values()){
            long ingStock = stock.getOrDefault(ingredient.component.name, 0L);
            long ingRequired = ingredient.quantity * nbRecipe;
            if(ingStock < ingRequired){
//                if(ingredient.component.name.equals("ORE")){
                    System.out.println(ingredient.component.name + " required : " + ingRequired + " but only " + ingStock + " in Stock to produce " + nbRecipe * nbProducts + " " + product.name);
//                }
                return Optional.of(new Ingredient(ingredient.component, ingRequired - ingStock));
            }
        }
        for (Ingredient ingredient : ingredients.values()){
            stock.computeIfPresent(ingredient.component.name, (name, qty) -> qty - nbRecipe * ingredient.quantity);
//            if(ingredient.component.name.equals("ORE")){
//                System.out.println("Remaining Ore : " + stock.get("ORE"));
//            }
        }
        System.out.println("Produced " + nbRecipe * nbProducts + " " + product.name + " with " + ingredients.values().stream().map(ingredient -> nbRecipe * ingredient.quantity + " " + ingredient.component.name).collect(Collectors.joining(", ")));
        if(product.name.equals("FUEL")){
            System.out.println("PRODUCED " + nbRecipe * nbProducts + " FUEL");
        }
        stock.compute(product.name, (name, qty) -> qty == null ? nbRecipe * nbProducts : qty + nbRecipe * nbProducts);
        return Optional.empty();
    }

    public void recycle(long quantity, Stock stock){
        long nbRecycle = BigDecimal.valueOf(quantity).divide(BigDecimal.valueOf(nbProducts), 0, RoundingMode.DOWN).longValueExact();
        for (Ingredient ingredient : ingredients.values()) {
            stock.computeIfPresent(ingredient.component.name, (name, qty) -> qty + nbRecycle * ingredient.quantity);
        }
        stock.compute(product.name, (name, qty) -> qty == null ? nbRecycle * nbProducts : qty - nbRecycle * nbProducts);
    }

    @Override
    public String toString(){
        return ingredients.values().stream().map(Ingredient::toString).collect(Collectors.joining(", ")) + " => " + nbProducts + " " + product;
    }
}
