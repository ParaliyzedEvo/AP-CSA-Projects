import java.util.ArrayList;
import java.util.Scanner;

public class TicketMasterDriver {

    private static final int SORT_AZ = 1;
    private static final int SORT_ZA = 2;
    private static final int PRICE_LOW_HIGH = 3;
    private static final int PRICE_HIGH_LOW = 4;
    private static final int SEARCH_CITY = 5;
    private static final int QUIT = 6;

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        TicketMaster tm = new TicketMaster();
        tm.readShowsFromFile("tickets.txt");

        int choice = 0;

        while (choice != QUIT) {
            try {
                System.out.println("\nTicketMaster Menu");
                System.out.println("1. Sort by Performer A-Z");
                System.out.println("2. Sort by Performer Z-A");
                System.out.println("3. Sort by Price Low-High");
                System.out.println("4. Sort by Price High-Low");
                System.out.println("5. Search by City");
                System.out.println("6. Quit");
                System.out.print("Enter choice: ");

                choice = keyboard.nextInt();
                keyboard.nextLine();

                if (choice < 1 || choice > 6) {
                    throw new IllegalArgumentException();
                }

                switch (choice) {
                    case SORT_AZ:
                        tm.sortByPerformer(true);
                        System.out.println(tm);
                        break;

                    case SORT_ZA:
                        tm.sortByPerformer(false);
                        System.out.println(tm);
                        break;

                    case PRICE_LOW_HIGH:
                        tm.sortByPrice(true);
                        System.out.println(tm);
                        break;

                    case PRICE_HIGH_LOW:
                        tm.sortByPrice(false);
                        System.out.println(tm);
                        break;

                    case SEARCH_CITY:
                        System.out.print("Enter city: ");
                        String city = keyboard.nextLine();
                        ArrayList<Show> results = tm.searchByCity(city);
                        for (Show s : results) {
                            System.out.println(s);
                        }
                        break;

                    case QUIT:
                        System.out.println("Goodbye!");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number 1-6.");
                keyboard.nextLine();
            }
        }

        keyboard.close();
    }
}
