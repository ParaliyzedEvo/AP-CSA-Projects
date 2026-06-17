import java.util.Map;
import java.util.LinkedHashMap;

public class Player {
    public int health;
    public int money;
    public int damage;
    public String weapon;
    public String armor;
    public int experience;
    public double damageMultiplier = 1.0;
    private Stats stats;
    private String name;
    private int level;
    private Map<String, Integer> inventory = new LinkedHashMap<>();

    public Player(String name, Stats stats) {
        this.name = name;
        this.health = 100;
        this.experience = 0;
        this.level = 1;
        this.money = 0;
        this.damage = 10;
        this.weapon = "Wooden Sword";
        this.armor = "Leather Armor";
        inventory.put("Health Potion", 2);
        inventory.put("Strength Potion", 1);
        this.stats = stats;
    }

    public String attack(Monster m) {
        m.health -= damage;
        stats.hitsDealt += 1;
        stats.totalDamageDealt += damage;
        return this.name + " attacked " + m.returnName() + " for " + damage + " damage.";
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int levelUp() {
        if (experience >= level * 100){
            level += 1;
        }
        return level;
    }

     public String useItem(String itemName) {
        if (!inventory.containsKey(itemName) || inventory.get(itemName) == 0) {
            return "You don't have that item!";
        }

        if (itemName.equals("Health Potion")) {
            health += 50;
            if (health > 100) {
                health = 100;
            }
            System.out.println("You used a Health Potion and recovered 50 health!");
            stats.healthRecovered += 50;
        } else if (itemName.equals("Strength Potion")) {
            damageMultiplier = 2.0;
            System.out.println("You used a Strength Potion! Your damage is doubled for the next attack!");
        } else {
            return "Item cannot be used.";
        }

        inventory.put(itemName, inventory.get(itemName) - 1);
        if (inventory.get(itemName) == 0) {
            inventory.remove(itemName);
        }

        return itemName;
    }

    public String returnName() {
        return name;
    }

    public void addItem(String itemName) {
        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + 1);
    }

    public String showInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTORY ===\n\n");
        
        if (inventory.isEmpty()) {
            sb.append("Your inventory is empty.\n");
        } else {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String name = entry.getKey();
                int count = entry.getValue();
                if (count > 1) {
                    sb.append("• " + name + " x" + count + "\n");
                } else {
                    sb.append("• " + name + "\n");
                }
            }
        }
        
        return sb.toString();
    }

}