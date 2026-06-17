public class Bowl extends Entree {
    private String riceType;    // "white", "brown", or "none"
    private String beanType;    // "black", "pinto", or "none"
    private boolean extraChiptle;

    public Bowl(String protein, boolean isVegetarian) {
        super(
            protein + " Bowl",       // name
            8.50,                    // base price
            true,                    // available
            400,                     // base calories (before toppings)
            isVegetarian,
            true,                    // bowls are gluten-free
            "Bowl",
            protein
        );
        this.riceType = "white";
        this.beanType = "black";
        this.extraChiptle = false;
    }

    public void setRice(String riceType)     { this.riceType = riceType; }
    public void setBeans(String beanType)    { this.beanType = beanType; }
    public void setExtraChiptle(boolean val) { this.extraChiptle = val; }

    // Calculates a "spice score" based on toppings + chiptle
    public int spiceScore() {
        int score = 0;
        for (String t : getToppings()) {
            switch (t.toLowerCase()) {
                case "hot salsa":    score += 3; break;
                case "medium salsa": score += 2; break;
                case "mild salsa":   score += 1; break;
                case "jalapeno":     score += 2; break;
            }
        }
        if (extraChiptle) score += 1;
        return score;
    }

    // Returns a heat label based on spice score
    public String heatLabel() {
        int s = spiceScore();
        if (s == 0) return "Mild";
        if (s <= 2) return "Medium";
        if (s <= 4) return "Hot";
        return "FIRE";
    }

    @Override
    public String describe() {
        // Partial override: extends Entree's describe()
        return super.describe()
            + " | Rice: " + riceType
            + ", Beans: " + beanType
            + " | Heat: " + heatLabel();
    }

    @Override
    public String toString() {
        return super.toString()
            + "\n   Rice: " + riceType + " | Beans: " + beanType
            + "\n   Heat level: " + heatLabel() + " (score: " + spiceScore() + ")"
            + (extraChiptle ? "\n   + Extra chipotle sauce" : "");
    }
}