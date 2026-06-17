import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    private Map<String, Integer> itemsForSale = new HashMap<>();
    private Map<String, Integer> weaponsForSale = new HashMap<>();
    private Map<String, Integer> armorForSale = new HashMap<>();

    public Shop() {
        itemsForSale.put("Health Potion", 50);
        itemsForSale.put("Strength Potion", 75);
        
        weaponsForSale.put("Stone Sword", 100);
        weaponsForSale.put("Copper Sword", 200);
        weaponsForSale.put("Iron Sword", 300);
        weaponsForSale.put("Gold Sword", 500);
        weaponsForSale.put("Diamond Sword", 800);
        
        armorForSale.put("Chainmail Armor", 150);
        armorForSale.put("Copper Armor", 250);
        armorForSale.put("Iron Armor", 400);
        armorForSale.put("Gold Armor", 600);
        armorForSale.put("Diamond Armor", 1000);
    }

    public String buy(String category, String itemName, Player p) {
        Map<String, Integer> currentMap = null;
        
        switch (category.toLowerCase()) {
            case "item":
                currentMap = itemsForSale;
                break;
            case "weapon":
                currentMap = weaponsForSale;
                break;
            case "armor":
                currentMap = armorForSale;
                break;
            default:
                return "Invalid category.";
        }
        
        if (!currentMap.containsKey(itemName)) {
            return "Item not found.";
        }
        
        int price = currentMap.get(itemName);
        
        if (p.money < price) {
            return "Not enough money! " + itemName + " costs " + price + " gold.";
        }

        p.money -= price;
        
        switch (category.toLowerCase()) {
            case "item":
                p.addItem(itemName);
                break;
            case "weapon":
                p.weapon = itemName;
                weaponsForSale.remove(itemName);
                break;
            case "armor":
                p.armor = itemName;
                armorForSale.remove(itemName);
                break;
        }
        
        return "You have purchased: " + itemName + " for " + price + " gold.";
    }
    
    public String getItem(String category, int index) {
        List<Map.Entry<String, Integer>> sortedEntries;
    
        switch (category.toLowerCase()) {
            case "item":
                sortedEntries = new ArrayList<>(itemsForSale.entrySet());
                break;
            case "weapon":
                sortedEntries = new ArrayList<>(weaponsForSale.entrySet());
                break;
            case "armor":
                sortedEntries = new ArrayList<>(armorForSale.entrySet());
                break;
            default:
                return "Invalid category.";
        }
        
        sortedEntries.sort(Map.Entry.comparingByValue());
        if (index >= 0 && index < sortedEntries.size()) {
            return sortedEntries.get(index).getKey();
        }
    
        return "Invalid index.";
    }
    
    public int getPrice(String category, String itemName) {
        switch (category.toLowerCase()) {
            case "item":
                return itemsForSale.getOrDefault(itemName, -1);
            case "weapon":
                return weaponsForSale.getOrDefault(itemName, -1);
            case "armor":
                return armorForSale.getOrDefault(itemName, -1);
            default:
                return -1;
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("\n=== SHOP INVENTORY ===\n\n");
    
        sb.append("ITEMS:\n");
        final int[] index = {1};
        itemsForSale.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEachOrdered(entry -> {
                sb.append(index[0]++)
                  .append(". ")
                  .append(entry.getKey())
                  .append(" - ")
                  .append(entry.getValue())
                  .append(" gold\n");
            });
    
        sb.append("\nWEAPONS:\n");
        index[0] = 1;
        weaponsForSale.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEachOrdered(entry -> {
                sb.append(index[0]++)
                  .append(". ")
                  .append(entry.getKey())
                  .append(" - ")
                  .append(entry.getValue())
                  .append(" gold\n");
            });
    
        sb.append("\nARMOR:\n");
        index[0] = 1;
        armorForSale.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEachOrdered(entry -> {
                sb.append(index[0]++)
                  .append(". ")
                  .append(entry.getKey())
                  .append(" - ")
                  .append(entry.getValue())
                  .append(" gold\n");
            });
    
        return sb.toString();
    }
}