public class Stats {
    public int hitsDealt;
    public int hitsTaken;
    public int monstersDefeated;
    public int totalDamageDealt;
    public int totalDamageTaken;
    public int itemsPurchased;
    public int healthRecovered;
    public int moneyEarned;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PLAYER STATISTICS ===\n\n");
        
        sb.append("Combat Stats:\n");
        sb.append("  Hits Dealt: " + hitsDealt + "\n");
        sb.append("  Hits Taken: " + hitsTaken + "\n");
        sb.append("  Monsters Defeated: " + monstersDefeated + "\n");
        sb.append("  Total Damage Dealt: " + totalDamageDealt + "\n");
        sb.append("  Total Damage Taken: " + totalDamageTaken + "\n\n");
        
        sb.append("Shop & Recovery:\n");
        sb.append("  Items Purchased: " + itemsPurchased + "\n");
        sb.append("  Health Recovered: " + healthRecovered + "\n");
        sb.append("  Money Earned: " + moneyEarned + " gold\n");
        
        return sb.toString();
    }
}