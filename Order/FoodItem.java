public class FoodItem extends MenuItem {
    private int calories;
    private boolean isVegetarian;
    private boolean isGlutenFree;

    public FoodItem(String name, double price, boolean isAvailable,
                    int calories, boolean isVegetarian, boolean isGlutenFree) {
        super(name, price, isAvailable);
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.isGlutenFree = isGlutenFree;
    }

    public int getCalories()        { return calories; }
    public boolean isVegetarian()   { return isVegetarian; }
    public boolean isGlutenFree()   { return isGlutenFree; }

    // Returns dietary tags as a compact string like "[V][GF]"
    public String getDietaryTags() {
        String tags = "";
        if (isVegetarian) tags += "[V]";
        if (isGlutenFree) tags += "[GF]";
        if (tags.isEmpty()) tags = "[none]";
        return tags;
    }

    // Checks whether this item fits within a calorie budget
    public boolean fitsCalorieBudget(int budget) {
        return calories <= budget;
    }

    @Override
    public String describe() {
        // Partial override: calls superclass describe(), then adds food-specific info
        return super.describe() + " | " + calories + " cal " + getDietaryTags();
    }

    @Override
    public String toString() {
        return super.toString() + " | " + calories + " cal " + getDietaryTags();
    }
}