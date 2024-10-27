package Cours3.rpg;

public class Destructible {
    protected double health;
    protected int id;
    private static int lastId = -1;
    protected Position position;
    protected int giveGold;
    protected double giveXp;

    public Destructible(double health,Position position, int gold, double xp) {
        this.health = health;
        this.id = lastId+1;
        this.position = position;
        this.giveGold = gold;
        this.giveXp = xp;
    }

    public boolean hit(double damage){
        this.health -= damage;
        return this.health>0;
    }

    public double getHealth() {
        return this.health;
    }

    public int getGiveGold(){
        return this.giveGold;
    }

    public double getGiveXp(){
        return this.giveXp;
    }
}
