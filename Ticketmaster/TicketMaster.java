import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMaster {

    private ArrayList<Show> shows;

    public TicketMaster() {
        shows = new ArrayList<>();
    }

    public void readShowsFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                try {
                    String[] parts = line.split(" ");

                    // Must have at least: date price qty performer, city
                    if (parts.length < 5 || !parts[0].contains("-")) {
                        throw new IllegalArgumentException("Bad data line");
                    }

                    String date = parts[0];

                    // Remove $ if present
                    double price = Double.parseDouble(parts[1]);

                    int qty = Integer.parseInt(parts[2]);

                    int commaIndex = line.indexOf(",");
                    if (commaIndex == -1) {
                        throw new IllegalArgumentException("Missing comma");
                    }

                    // Performer starts after qty
                    String performer = line.substring(
                            line.indexOf(parts[3]),
                            commaIndex
                    ).trim();

                    String city = line.substring(commaIndex + 1).trim();

                    shows.add(new Show(date, price, qty, performer, city));

                } catch (Exception e) {
                    System.out.println("Skipping invalid data line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening file.");
        }
    }

    public ArrayList<Show> searchByCity(String city) {
        ArrayList<Show> results = new ArrayList<>();
        for (Show s : shows) {
            if (s.getCity().equalsIgnoreCase(city)) {
                results.add(s);
            }
        }
        return results;
    }

    // Selection Sort by Performer (A-Z)
    public void sortByPerformer(boolean ascending) {
        for (int i = 0; i < shows.size() - 1; i++) {
            int target = i;
            for (int j = i + 1; j < shows.size(); j++) {
                int cmp = shows.get(j).getPerformer()
                        .compareToIgnoreCase(shows.get(target).getPerformer());
                if ((ascending && cmp < 0) || (!ascending && cmp > 0)) {
                    target = j;
                }
            }
            Show temp = shows.get(i);
            shows.set(i, shows.get(target));
            shows.set(target, temp);
        }
    }

    // Insertion Sort by Price
    public void sortByPrice(boolean highToLow) {
        for (int i = 1; i < shows.size(); i++) {
            Show temp = shows.get(i);
            int j = i - 1;

            while (j >= 0 &&
                (highToLow
                ? temp.getPrice() > shows.get(j).getPrice()
                : temp.getPrice() < shows.get(j).getPrice())) {

                shows.set(j + 1, shows.get(j));
                j--;
            }

            shows.set(j + 1, temp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(
                "%-10s %-8s %-5s %-15s %-15s%n",
                "Date", "Price", "Qty", "Performer", "City"
        ));
        sb.append("------------------------------------------------------------\n");

        for (Show s : shows) {
            sb.append(String.format(
                    "%-10s $%-7.2f %-5d %-15s %-15s%n",
                    s.getDate(),
                    s.getPrice(),
                    s.getQty(),
                    s.getPerformer(),
                    s.getCity()
            ));
        }

        return sb.toString();
    }
}
