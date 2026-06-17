public class MenuItem {
    private String name;
    private double price;
    private boolean isAvailable;

    public MenuItem(String name, double price, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getName()        { return name; }
    public double getPrice()       { return price; }
    public boolean isAvailable()   { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    // Returns a price string formatted to 2 decimal places
    public String formattedPrice() {
        return String.format("$%.2f", price);
    }

    // Calculates price after tax
    public double priceWithTax(double taxRate) {
        return price * (1 + taxRate);
    }

    // Overridden in subclasses to describe the item 
    public String describe() {
        return "Menu Item: " + name;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Unavailable";
        return "[" + status + "] " + name + " - " + formattedPrice();
    }
}