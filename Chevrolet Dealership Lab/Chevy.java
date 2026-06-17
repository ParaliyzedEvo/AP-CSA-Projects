public class Chevy
{
    private int year;
    private int mileage;
    private double fuelEfficiency;
    private double basePrice;
    private String model;
    private String color;
    private boolean luxuryPackage;
    private boolean fourWDPackage;
    private boolean sportsPackage;
    private double priceWithUpgrades;
    private double grandTotal;

    private final String MAKE = "Chevrolet";
    private final double TAX_RATE = 0.122;
    private final double LUXURY_PRICE = 5000;
    private final double FOURWD_PRICE = 3500;
    private final double SPORTS_PRICE = 6000;
    private final double SPORTS_MPG_DEDUCTION = 5;

    public Chevy()
    {
        year = 2021;
        mileage = 0;
        fuelEfficiency = 35;
        basePrice = 16000;
        model = "Trax";
        color = "White";
        luxuryPackage = false;
        fourWDPackage = false;
        sportsPackage = false;
        calcPrice();
    }

    public Chevy(int y, int m, double mpg, double price, String mod, String col, boolean lux, boolean four, boolean sport)
    {
        year = y;
        mileage = m;
        fuelEfficiency = mpg;
        basePrice = price;
        model = mod;
        color = col;
        luxuryPackage = lux;
        fourWDPackage = four;
        sportsPackage = sport;
        calcPrice();
    }

    public void calcPrice()
    {
    priceWithUpgrades = basePrice;

    if (luxuryPackage)
        priceWithUpgrades += LUXURY_PRICE;

    if (fourWDPackage)
        priceWithUpgrades += FOURWD_PRICE;

    if (sportsPackage)
    {
        priceWithUpgrades += SPORTS_PRICE;
        fuelEfficiency = fuelEfficiency - SPORTS_MPG_DEDUCTION;
        if (fuelEfficiency < 0) fuelEfficiency = 0;
    }

    grandTotal = priceWithUpgrades + (priceWithUpgrades * TAX_RATE);
    }


    public int compareTo(Chevy other)
    {
        if(this.mileage < other.mileage)
            return -1;
        else if(this.mileage > other.mileage)
            return 1;
        else
            return 0;
    }

    public boolean equals(Chevy other)
    {
        boolean sameModel = this.model.equalsIgnoreCase(other.model);
        boolean sameColor = this.color.equalsIgnoreCase(other.color);
        boolean sameStatus = (this.mileage < 200 && other.mileage < 200) || (this.mileage >= 200 && other.mileage >= 200);

        return sameModel && sameColor && sameStatus;
    }

    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getFuelEfficiency() { return fuelEfficiency; }
    public double getBasePrice() { return basePrice; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public boolean getLuxuryPackage() { return luxuryPackage; }
    public boolean getFourWDPackage() { return fourWDPackage; }
    public boolean getSportsPackage() { return sportsPackage; }
    public double getPriceWithUpgrades() { return priceWithUpgrades; }
    public double getGrandTotal() { return grandTotal; }

    public void setYear(int y) { year = y; }
    public void setMileage(int m) { mileage = m; }
    public void setFuelEfficiency(double mpg) { fuelEfficiency = mpg; }
    public void setBasePrice(double price) { basePrice = price; calcPrice(); }
    public void setModel(String mod) { model = mod; }
    public void setColor(String col) { color = col; }
    public void setLuxuryPackage(boolean lux) { luxuryPackage = lux; calcPrice(); }
    public void setFourWDPackage(boolean four) { fourWDPackage = four; calcPrice(); }
    public void setSportsPackage(boolean sport) { sportsPackage = sport; calcPrice(); }

    public String toString()
    {
        String result = "******************************************\n";
        result += year + " " + MAKE + " " + model + " (" + color + ")\n\n";
        result += "    BASE PRICE:\t\t\t$" + basePrice + "\n";
        result += "    MILES:\t\t\t" + mileage + "\n";
        result += "    FUEL EFFICIENCY:\t\t" + fuelEfficiency + " mpg\n";
        result += "    PACKAGES:\n";

        if(luxuryPackage)
            result += "       - Luxury Package\n";
        if(fourWDPackage)
            result += "       - 4WD Package\n";
        if(sportsPackage)
            result += "       - Sports Package\n";
        if(!luxuryPackage && !fourWDPackage && !sportsPackage)
            result += "       None\n";

        result += "\n    PRICE WITH UPGRADES:\t$" + priceWithUpgrades + "\n";
        result += "    FINAL PRICE WITH TAX:\t$" + grandTotal + "\n";
        result += "******************************************";

        return result;
    }
}