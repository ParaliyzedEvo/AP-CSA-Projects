import java.util.ArrayList;
import java.util.Scanner;

public class ChipotleOrderTester {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<MenuItem> order = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("       Welcome to Chipotle!");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("  1 - View full menu");
            System.out.println("  2 - Build a Bowl");
            System.out.println("  3 - Build a Burrito/Entree");
            System.out.println("  4 - Add a side or drink");
            System.out.println("  5 - View my order");
            System.out.println("  6 - Checkout");
            System.out.println("  7 - Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> viewMenu();
                case "2" -> buildBowl();
                case "3" -> buildEntree();
                case "4" -> addSideOrDrink();
                case "5" -> viewOrder();
                case "6" -> { checkout(); running = false; }
                case "7" -> { System.out.println("Goodbye!"); running = false; }
                default  -> System.out.println("Invalid option, try again.");
            }
        }
        sc.close();
    }

    // View a static menu of all available items
    static void viewMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("--- Entrees ---");
        System.out.println("  Bowl       $8.50  (choose protein + toppings)");
        System.out.println("  Burrito    $9.25  (choose protein + toppings)");
        System.out.println("  Tacos      $8.75  (choose protein + toppings)");
        System.out.println("--- Proteins ---");
        System.out.println("  Chicken, Steak, Carnitas, Barbacoa, Sofritas (V)");
        System.out.println("--- Toppings ---");
        System.out.println("  rice, beans, cheese, sour cream, guac (+$1.00),");
        System.out.println("  mild salsa, medium salsa, hot salsa, jalapeño, lettuce");
        System.out.println("--- Sides & Drinks ---");
        System.out.println("  Chips & Guac  $4.25  | 490 cal  [V][GF]");
        System.out.println("  Chips & Salsa $2.50  | 390 cal  [V][GF]");
        System.out.println("  Lemonade      $2.75  | 140 cal  [V][GF]");
        System.out.println("  Water         $0.00  |   0 cal  [V][GF]");
        System.out.println("==========================");
    }

    // Build a Bowl interactively
    static void buildBowl() {
        System.out.println("\n-- Build Your Bowl --");

        String protein = chooseProtein();
        boolean isVeg = protein.equalsIgnoreCase("sofritas");
        Bowl bowl = new Bowl(protein, isVeg);

        System.out.print("Rice? (white / brown / none): ");
        bowl.setRice(sc.nextLine().trim().toLowerCase());

        System.out.print("Beans? (black / pinto / none): ");
        bowl.setBeans(sc.nextLine().trim().toLowerCase());

        addToppings(bowl);

        System.out.print("Extra chipotle sauce? (yes / no): ");
        bowl.setExtraChiptle(sc.nextLine().trim().equalsIgnoreCase("yes"));

        order.add(bowl);
        System.out.println("\nAdded to order: " + bowl.getName());
        System.out.println("Heat level: " + bowl.heatLabel());
        System.out.println("Est. calories: " + bowl.estimateTotalCalories());
    }

    // Build a generic Entree interactively
    static void buildEntree() {
        System.out.println("\n-- Build Your Entree --");
        System.out.print("Type (Burrito / Tacos / Salad): ");
        String type = capitalize(sc.nextLine().trim());

        String protein = chooseProtein();
        boolean isVeg = protein.equalsIgnoreCase("sofritas");

        double price = type.equalsIgnoreCase("Burrito") ? 9.25 : 8.75;
        boolean isGF = !type.equalsIgnoreCase("Burrito"); // burritos have a flour tortilla

        Entree entree = new Entree(
            protein + " " + type, price, true,
            500, isVeg, isGF,
            type, protein
        );

        addToppings(entree);

        order.add(entree);
        System.out.println("\nAdded to order: " + entree.getName());
        System.out.println("Est. calories: " + entree.estimateTotalCalories());
    }

    // Add a side or drink
    static void addSideOrDrink() {
        System.out.println("\n-- Sides & Drinks --");
        System.out.println("  1 - Chips & Guac  $4.25");
        System.out.println("  2 - Chips & Salsa $2.50");
        System.out.println("  3 - Lemonade      $2.75");
        System.out.println("  4 - Water         $0.00");
        System.out.print("Choice: ");
        String choice = sc.nextLine().trim();

        FoodItem item = switch (choice) {
            case "1" -> new FoodItem("Chips & Guac",  4.25, true, 490, true, true);
            case "2" -> new FoodItem("Chips & Salsa", 2.50, true, 390, true, true);
            case "3" -> new FoodItem("Lemonade",      2.75, true, 140, true, true);
            case "4" -> new FoodItem("Water",         0.00, true,   0, true, true);
            default  -> null;
        };

        if (item != null) {
            order.add(item);
            System.out.println("Added: " + item.getName());
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // View current order
    static void viewOrder() {
        if (order.isEmpty()) {
            System.out.println("\nYour order is empty!");
            return;
        }
        System.out.println("\n========== YOUR ORDER ==========");
        for (int i = 0; i < order.size(); i++) {
            System.out.println((i + 1) + ". " + order.get(i).toString());
            System.out.println("   >> " + order.get(i).describe());
        }
        System.out.println("================================");
    }

    // Checkout with tax
    static void checkout() {
        if (order.isEmpty()) {
            System.out.println("\nNothing to checkout — order is empty!");
            return;
        }
        double TAX = 0.10;
        double subtotal = 0;
        for (MenuItem item : order) subtotal += item.getPrice();

        // guac upcharge
        for (MenuItem item : order) {
            if (item instanceof Entree e && e.getToppings().contains("guac")) {
                subtotal += 1.00;
            }
        }

        System.out.println("\n========== RECEIPT ==========");
        for (MenuItem item : order) {
            System.out.printf("  %-25s %s%n", item.getName(), item.formattedPrice());
        }
        System.out.println("------------------------------");
        System.out.printf("  Subtotal:   $%.2f%n", subtotal);
        System.out.printf("  Tax (10%%):  $%.2f%n", subtotal * TAX);
        System.out.printf("  TOTAL:      $%.2f%n", subtotal * (1 + TAX));
        System.out.println("==============================");
        System.out.println("Thanks for your order!");
    }

    // Helper: choose a protein
    static String chooseProtein() {
        System.out.println("Protein options: Chicken, Steak, Carnitas, Barbacoa, Sofritas");
        System.out.print("Choose protein: ");
        return capitalize(sc.nextLine().trim());
    }

    // Helper: add toppings to any Entree (or Bowl, which extends Entree)
    static void addToppings(Entree entree) {
        System.out.println("Available toppings: rice, beans, cheese, sour cream, guac,");
        System.out.println("                    mild salsa, medium salsa, hot salsa, jalapeño, lettuce");
        System.out.println("Type toppings one at a time. Press Enter with nothing to stop.");
        while (true) {
            System.out.print("  Add topping: ");
            String topping = sc.nextLine().trim().toLowerCase();
            if (topping.isEmpty()) break;
            entree.addTopping(topping);
            System.out.println("  + " + topping + " added");
        }
    }
    
    //Helper: capitalize first letter
    static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
    }
}