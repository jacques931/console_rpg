package Cours3.rpg.items;

import Cours3.rpg.ConsoleColors;
import Cours3.rpg.Monster;
import Cours3.rpg.Obstacle;

public abstract class Weapon extends Item{
    protected double damage;
    protected double supDamage; // Etant donne qu'on doit laisser l'attaque dans weapon
    protected double useMana;
    protected double monster_ratio_damage;
    protected double obstacle_ratio_damage;

    public Weapon(double d, int p, String n, double monster_ratio, double obstacle_ratio, double useMana) {
        super(n,p);
        this.damage = d;
        this.supDamage = 0;
        this.useMana = useMana;
        this.monster_ratio_damage = monster_ratio;
        this.obstacle_ratio_damage = obstacle_ratio;
    }

    public abstract String asciiArt();

    public boolean canAttack(double mana){
        return this.useMana<=mana;
    }

    public void setSupDamage(double supDamage) {
        this.supDamage = supDamage;
    }

    public double getUseMana(){
        return this.useMana;
    }

    public boolean atack(Monster monster){
        return monster.hit((this.damage + this.supDamage) * this.getMonster_ratio_damage());
    }

    public boolean atack(Obstacle obstacle){
        return obstacle.hit((this.damage + this.supDamage) * this.getObstacle_ratio_damage());
    }

    public void displayMonsterDamage(){
        double finalDamage = (this.damage + this.supDamage) * this.getMonster_ratio_damage();
        System.out.println(ConsoleColors.textColorInformation("Votre attaque sur un monstre : " + finalDamage));
    }

    public void displayObstacleDamage(){
        double finalDamage = (this.damage + this.supDamage) * this.getObstacle_ratio_damage();
        System.out.println(ConsoleColors.textColorInformation("Votre attaque sur un obstacle : " + finalDamage));
    }

    public void displayDamage(){
        this.displayObstacleDamage();
        this.displayMonsterDamage();
    }

    public double getMonster_ratio_damage() {
        return this.monster_ratio_damage;
    }

    public double getObstacle_ratio_damage() {
        return this.obstacle_ratio_damage;
    }

    @Override
    public String toString() {
        return super.name + " - damage = " + this.damage + " - prix : " + super.price + " piècee d'or";
    }
}
