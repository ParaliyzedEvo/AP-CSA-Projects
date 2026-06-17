import java.util.ArrayList;

public class ChipotleOrderTester {

    public static void main(String[] args) {

        // Build a menu: ArrayList of MenuItem references holding subclass objects
        ArrayList<MenuItem> menu = new ArrayList<>();

        // FoodItem (chips & drinks — not full entrees) 
        FoodItem chips = new FoodItem("Chips & Guac", 4.25, true, 490, true, true);
        FoodItem lemonade = new FoodItem("Lemonade", 2.75, true, 140, true, true);

        // Entree (burrito) 
        Entree burrito = new Entree(
            "Steak Burrito", 9.25, true,
            700, false, false,
            "Burrito", "Steak"
        );
        burrito.addTopping("rice");
        burrito.addTopping("beans");
        burrito.addTopping("sour cream");
        burrito.addTopping("cheese");

        // Bowl objects (two layers deep) 
        Bowl chickenBowl = new Bowl("Chicken", false);
        chickenBowl.setRice("brown");
        chickenBowl.setBeans("pinto");
        chickenBowl.addTopping("medium salsa");
        chickenBowl.addTopping("cheese");
        chickenBowl.addTopping("guac");

        Bowl veggiBowl = new Bowl("Sofritas", true);
        veggiBowl.setRice("white");
        veggiBowl.setBeans("black");
        veggiBowl.addTopping("hot salsa");
        veggiBowl.addTopping("jalapeño");
        veggiBowl.setExtraChiptle(true);

        // Add all to menu (using superclass reference type)
        menu.add(chips);
        menu.add(lemonade);
        menu.add(burrito);
        menu.add(chickenBowl);
        menu.add(veggiBowl);

        // Loop: toString() + describe() for every item
        System.out.println("====================================");
        System.out.println("      CHIPOTLE MENU");
        System.out.println("====================================");

        for (MenuItem item : menu) {
            System.out.println(item.toString());          // overridden toString
            System.out.println("  >> " + item.describe()); // overridden describe
            System.out.println("------------------------------------");
        }

        // Utility: calorie budget filter
        int budget = 600;
        System.out.println("\n== Items under " + budget + " calories ==");
        for (MenuItem item : menu) {
            if (item instanceof FoodItem fi) {
                if (fi.fitsCalorieBudget(budget)) {
                    System.out.println("  " + fi.getName() + " (" + fi.getCalories() + " cal)");
                }
            }
        }

        // Utility: show full estimated calories for entrees
        System.out.println("\n== Entree calorie estimates (with toppings) ==");
        for (MenuItem item : menu) {
            if (item instanceof Entree e) {
                System.out.printf("  %-20s base: %d cal  |  est. total: %d cal%n",
                    e.getName(), e.getCalories(), e.estimateTotalCalories());
            }
        }

        // Utility: heat levels for bowls specifically
        System.out.println("\n== Bowl heat levels ==");
        for (MenuItem item : menu) {
            if (item instanceof Bowl b) {
                System.out.println("  " + b.getName() + " → " + b.heatLabel()
                    + " (score: " + b.spiceScore() + ")");
            }
        }

        // Utility: total order price with tax
        double TAX = 0.09;
        double subtotal = 0;
        for (MenuItem item : menu) subtotal += item.getPrice();
        System.out.printf("%n== Order Total ==%n");
        System.out.printf("  Subtotal:  $%.2f%n", subtotal);
        System.out.printf("  Tax (9%%): $%.2f%n", subtotal * TAX);
        System.out.printf("  Total:     $%.2f%n", subtotal * (1 + TAX));
    }
}