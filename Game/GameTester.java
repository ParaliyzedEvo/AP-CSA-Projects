import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameTester {
    public static void main(String[] args) {
        System.out.println("Welcome to Game 1! (Made by Andrei Villaro)");
        System.out.println();
        System.out.print("Please enter your player name: ");
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        String playerName = input.nextLine();
        Stats stats = new Stats();
        Player player = new Player(playerName, stats);
        System.out.println("Hello, " + player.returnName() + "! Your adventure begins now.");

        while (true && player.health > 0) {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("1. View Stats");
            System.out.println("2. Visit Shop");
            System.out.println("3. Fight Monsters");
            System.out.println("4. Exit Game");
            System.out.print("Enter your choice (1-4): ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(stats);
                    break;
                case "2":
                    Shop shop = new Shop();
                    System.out.println("Welcome to the Shop! Here are the available items: ");
                    System.out.println("Your current money: " + player.money + " gold");
                    System.out.println(shop);
                    
                    while (true) {
                        System.out.print("Enter the category (item, weapon, armor) or 'exit' to leave: ");
                        String category = input.nextLine().trim().toLowerCase();
                        if (category.equals("exit")) {
                            break;
                        }
                    
                        if (!category.equals("item") && !category.equals("weapon") && !category.equals("armor")) {
                            System.out.println("Invalid category. Please enter 'item', 'weapon', or 'armor'.");
                            continue;
                        }
                    
                        System.out.print("Enter the index of the item you want to buy: ");
                        String indexInput = input.nextLine().trim();
                        int index;
                    
                        try {
                            index = Integer.parseInt(indexInput) - 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number for the item index.");
                            continue;
                        }
                    
                        String itemName = shop.getItem(category, index);
                    
                        if (itemName.equals("Invalid category.") || itemName.equals("Invalid index.")) {
                            System.out.println(itemName);
                            continue;
                        }
                    
                        if (category.equals("item")) {
                            System.out.print("How many do you want to buy? ");
                            String amountInput = input.nextLine().trim();
                            int amount;
                    
                            try {
                                amount = Integer.parseInt(amountInput);
                                if (amount <= 0) {
                                    System.out.println("Please enter a positive number.");
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number for amount.");
                                continue;
                            }
                    
                            int totalCost = shop.getPrice(category, itemName) * amount;
                    
                            if (player.money < totalCost) {
                                System.out.println("Not enough money! You need " + totalCost + " gold but only have " + player.money + " gold.");
                            } else {
                                for (int i = 0; i < amount; i++) {
                                    shop.buy(category, itemName, player);
                                }
                                System.out.println("You have purchased " + amount + "x " + itemName + " for " + totalCost + " gold.");
                            }
                        } else {
                            String result = shop.buy(category, itemName, player);
                            System.out.println(result);
                        }
                    
                        System.out.println();
                    }
                    
                    System.out.println("Thanks for visiting the shop!");
                    break;
                case "3":
                    List<Monster> monsters = new ArrayList<>();
                    if (stats.monstersDefeated < 20) {
                        int numGoblins = (int)(Math.random() * 3) + 1;
                        for (int i = 0; i < numGoblins; i++) {
                            monsters.add(new Monster("Goblin", 30, 8, 20, 10, stats));
                        }
                    } else if (stats.monstersDefeated < 45) {
                        int numOrcs = (int)(Math.random() * 2) + 1;
                        for (int i = 0; i < numOrcs; i++) {
                            monsters.add(new Monster("Orc", 50, 15, 50, 25, stats));
                        }
                    } else if (stats.monstersDefeated < 67) {
                        int numTrolls = (int)(Math.random() * 2) + 1;
                        for (int i = 0; i < numTrolls; i++) {
                            monsters.add(new Monster("Troll", 80, 25, 100, 50, stats));
                        }
                    } else {
                        monsters.add(new Monster("Dragon", 150, 40, 200, 100, stats));
                    }
                    
                    System.out.println("\n=== BATTLE START ===");
                    if (monsters.size() == 1) {
                        System.out.println("A wild " + monsters.get(0).returnName() + " appears!");
                    } else {
                        System.out.println(monsters.size() + " wild " + monsters.get(0).returnName() + "s appear!");
                    }
                    
                    for (int m = 0; m < monsters.size(); m++) {
                        Monster monster = monsters.get(m);
                        
                        if (monsters.size() > 1) {
                            System.out.println("\n--- Fighting " + monster.returnName() + " #" + (m + 1) + " ---");
                        }
                        
                        System.out.println(monster.returnName() + " HP: " + monster.health);
                        System.out.println("Your HP: " + player.health);
                        
                        while (monster.health > 0 && player.health > 0) {
                            System.out.println("\nWhat will you do?");
                            System.out.println("1. Fight");
                            System.out.println("2. Use Item");
                            System.out.print("Enter your choice: ");
                            String choiceInput = input.nextLine().trim();
                            int choice2;
                            
                            try {
                                choice2 = Integer.parseInt(choiceInput);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input! Please enter a number.");
                                continue;
                            }
                            
                            if (choice2 == 1) {
                                double attackChance = 0.67;
                                if (player.levelUp() > 7) attackChance = 0.727;
                                if (player.levelUp() > 10) attackChance = 0.85;
                                if (player.levelUp() > 20) attackChance = 1;
                                
                                if (Math.random() < attackChance) {
                                    int damage = (int)(player.damage * player.damageMultiplier);
                                    monster.health -= damage;
                                    System.out.println("You dealt " + damage + " damage to " + monster.returnName() + "!");
                                    
                                    stats.hitsDealt++;
                                    stats.totalDamageDealt += damage;
                                    
                                    if (player.damageMultiplier > 1.0) {
                                        player.damageMultiplier = 1.0;
                                    }
                                    
                                    if (monster.health <= 0) {
                                        System.out.println(monster.returnName() + " has been defeated!");
                                        player.experience += monster.exp;
                                        player.money += monster.money;
                                        stats.monstersDefeated++;
                                        stats.moneyEarned += monster.money;
                                        
                                        System.out.println("You gained " + monster.exp + " EXP and " + monster.money + " gold!");
                                        break;
                                    }
                                    
                                    if (Math.random() < 0.15) {
                                        System.out.println(monster.returnName() + " flinched and couldn't attack!");
                                        continue;
                                    }
                                } else {
                                    System.out.println("Your attack missed!");
                                }
                                
                                double monsterHitChance = 0.45;
                                if (player.levelUp() > 10) monsterHitChance = 0.65;
                                if (player.levelUp() > 25) monsterHitChance = 0.85;
                                
                                if (Math.random() < monsterHitChance) {
                                    int monsterDamage = monster.damage;
                                    player.health -= monsterDamage;
                                    System.out.println(monster.returnName() + " dealt " + monsterDamage + " damage to you!");
                                    
                                    stats.hitsTaken++;
                                    stats.totalDamageTaken += monsterDamage;
                                } else {
                                    System.out.println(monster.returnName() + "'s attack missed!");
                                }
                                
                                System.out.println("\n" + monster.returnName() + " HP: " + monster.health);
                                System.out.println("Your HP: " + player.health);
                                
                                if (player.health <= 0) {
                                    System.out.println("\nYou have been defeated!");
                                    System.out.println("\n=== GAME OVER ===");
                                    System.out.println(stats);
                                    return;
                                }
                                
                            } else if (choice2 == 2) {
                                System.out.println(player.showInventory());
                                System.out.print("Enter the name of the item you want to use (or 'back' to cancel): ");
                                String itemName = input.nextLine();
                                
                                if (itemName.equalsIgnoreCase("back")) {
                                    continue;
                                }
                                
                                String result = player.useItem(itemName);
                                if (result.equals("You don't have that item!") || result.equals("Item cannot be used.")) {
                                    System.out.println(result);
                                    continue;
                                }
                                
                                System.out.println(monster.returnName() + " is waiting...");
                                
                            } else {
                                System.out.println("Invalid choice! Try again.");
                            }
                        }
                        
                        if (player.health <= 0) {
                            break;
                        }
                    }
                    
                    if (player.health > 0) {
                        player.health = 100;
                        System.out.println("\n=== BATTLE COMPLETE ===");
                        System.out.println("All monsters defeated!");
                        int oldLevel = player.levelUp();
                        player.levelUp();
                        int newLevel = player.levelUp();
                        if (newLevel > oldLevel) {
                            System.out.println("\n*** LEVEL UP! You are now level " + newLevel + "! ***");
                        }
                        
                        if (stats.monstersDefeated == 20) {
                            System.out.println("\n!!! You've defeated 20 monsters! Orcs will now appear! !!!");
                        } else if (stats.monstersDefeated == 45) {
                            System.out.println("\n!!! You've defeated 45 monsters! Trolls will now appear! !!!");
                        } else if (stats.monstersDefeated == 67) {
                            System.out.println("\n!!! You've defeated 67 monsters! Dragons will now appear! !!!");
                        }
                    }
                    break;
                case "4":
                    System.out.println("Here are your final stats: ");
                    System.out.println(stats);
                    System.out.println("Kk bye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}