import java.util.ArrayList;

public class Entree extends FoodItem {
    private String entreeType;   // e.g. "Burrito", "Bowl", "Tacos", "Salad"
    private String protein;
    private ArrayList<String> toppings;

    public Entree(String name, double price, boolean isAvailable, int calories, boolean isVegetarian, boolean isGlutenFree, String entreeType, String protein) {
        super(name, price, isAvailable, calories, isVegetarian, isGlutenFree);
        this.entreeType = entreeType;
        this.protein = protein;
        this.toppings = new ArrayList<>();
    }

    public String getEntreeType() { return entreeType; }
    public String getProtein()    { return protein; }

    // Adds a topping to this entree
    public void addTopping(String topping) {
        toppings.add(topping);
    }

    // Returns number of toppings
    public int toppingCount() {
        return toppings.size();
    }
    
    // Returns toppings in menu
    public ArrayList<String> getToppings() {
        return toppings;
    }

    // Estimates total calories including common topping additions
    public int estimateTotalCalories() {
        int extra = 0;
        for (String t : toppings) {
            switch (t.toLowerCase()) {
                case "guac":       extra += 230; break;
                case "cheese":     extra += 110; break;
                case "sour cream": extra += 120; break;
                case "rice":       extra += 210; break;
                case "beans":      extra += 130; break;
                default:           extra += 20;  break;
            }
        }
        return getCalories() + extra;
    }

    @Override
    public String describe() {
        // Partial override: builds on FoodItem's describe()
        String base = super.describe();
        StringBuilder sb = new StringBuilder(base);
        sb.append(" | ").append(entreeType).append(" with ").append(protein);
        if (!toppings.isEmpty()) {
            sb.append(" + ").append(String.join(", ", toppings));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += "\n   " + entreeType + " | Protein: " + protein;
        if (!toppings.isEmpty()) {
            result += "\n   Toppings: " + String.join(", ", toppings);
            result += "\n   Est. total calories: " + estimateTotalCalories();
        }
        return result;
    }
}