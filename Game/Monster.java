public class Monster {
    public int health;
    public final int exp;
    public final int money;
    public final int damage;
    private String name;
    private Stats stats;

    public Monster(String name, int health, int damage, int exp, int money, Stats stats) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.exp = exp;
        this.money = money;
        this.stats = stats;
    }

    public String attack(Player p) {
        p.health -= damage;
        stats.hitsTaken += 1;
        stats.totalDamageTaken += damage;
        return this.name + " attacked " + p.returnName() + " for " + damage + " damage.";
    }

    public String defeat(Player p) {
        stats.monstersDefeated += 1;
        p.experience += exp;
        p.money += money;
        return "You have defeated the " + name + "!";
    }

    public String returnName() {
        return name;
    }
}