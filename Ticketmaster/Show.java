public class Show {
    private String date;
    private double price;
    private int qty;
    private String performer;
    private String city;

    public Show(String date, double price, int qty, String performer, String city) {
        this.date = date;
        this.price = price;
        this.qty = qty;
        this.performer = performer;
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public String getPerformer() {
        return performer;
    }

    public String getCity() {
        return city;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s $%7.2f %-15s %-12s",
                date, price, performer + ",", city
        );
    }
}
