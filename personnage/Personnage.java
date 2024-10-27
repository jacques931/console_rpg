package Cours3.rpg.personnage;

import Cours3.rpg.ConsoleColors;
import Cours3.rpg.Game;
import Cours3.rpg.Inventory;
import Cours3.rpg.Position;
import Cours3.rpg.items.*;

public abstract class Personnage {
    private double maxHealth;
    private double health;
    private double maxMana;
    private double mana;
    private double xp;
    private int gold;
    private Position position;

    private Weapon weapon;
    private Inventory inventory;

    public Personnage(double h, double m){
        this.health = h;
        this.maxHealth = this.health;
        this.mana = m;
        this.maxMana = this.mana;
        this.xp = 0;
        this.gold = 100;
        this.weapon = null;
        this.position = new Position(1,1);
        this.inventory = new Inventory(this);
    }

    public abstract double getSupDamage(Weapon weapon);

    public Position getPosition(){
        return this.position;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold){
        this.gold += gold;
    }

    public double getXp() {
        return this.xp;
    }

    public void setXp(double xp){
        this.xp += xp;
    }

    public double getMana(){
        return this.mana;
    }

    public void setMana(double mana){
        this.mana += mana;
    }

    public void displayGold(){
        System.out.println(ConsoleColors.textColorInformation("Vous avez " + this.gold + " gold"));
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void hit(double damage){
        this.health -= damage;
        System.out.println(ConsoleColors.textColorInformation("Point de vie : " + this.health + "/" + this.maxHealth));
    }

    public boolean isAlive(){
        return this.health>0;
    }

    public boolean heal(double heal){
        //Evite de gaspiller les potions
        if(this.health==this.maxHealth) return false;

        //Ne Recupere que le maximun de pv qu'il peut
        this.health = Math.min(this.health + heal, this.maxHealth);

        return true;
    }

    public boolean restoreMana(double mana){
        //Evite de gaspiller les potions
        if(this.mana==this.maxMana) return false;

        //Ne Recupere que le maximun de mana qu'il peut
        this.mana = Math.min(this.mana + mana, this.maxMana);

        return true;
    }

    public void displayPlayerStatus(){
        System.out.println(ConsoleColors.CYAN);
        this.displayPlayerClass();
        System.out.println("Point d'experience : " + this.xp);
        System.out.println("Point de vie : " + this.health + "/" + this.maxHealth);
        System.out.println("Point de mana : " + this.mana + "/" + this.maxMana);
        System.out.println("Pièce d'or : " + this.gold);
        System.out.println("Arme : " + (this.weapon != null ? this.weapon : "Aucun"));
        System.out.println(ConsoleColors.RESET);
    }

    public abstract void displayPlayerClass();

    public static Personnage createPlayer(){
        System.out.println("Choissiez votre classe :");
        System.out.println(ConsoleColors.choiceTxtColor("[1] Berserk : [point de vie : " + Berserker.START_HEALTH + " | point de mana : " + Berserker.START_MANA + " | Arme spécifique : " + Hammer.NAME + " | dégâts additionnels de l'arme : "+ Berserker.ADD_DAMAGE_WEAPON + "]"));
        System.out.println(ConsoleColors.choiceTxtColor("[2] Paladin : [point de vie : " + Paladin.START_HEALTH + " | point de mana : " + Paladin.START_MANA + " | Arme spécifique : " + Axe.NAME + " | dégâts additionnels de l'arme : "+ Paladin.ADD_DAMAGE_WEAPON + "]"));
        System.out.println(ConsoleColors.choiceTxtColor("[3] Archer : [point de vie : " + Archer.START_HEALTH + " | point de mana : " + Archer.START_MANA + " | Arme spécifique : " + Bow.NAME + " | dégâts additionnels de l'arme : "+ Archer.ADD_DAMAGE_WEAPON + "]"));
        System.out.println(ConsoleColors.choiceTxtColor("[4] Mage : [point de vie : " + Mage.START_HEALTH + " | point de mana : " + Mage.START_MANA + " | Arme spécifique : " + Stick.NAME + " | dégâts additionnels de l'arme : "+ Mage.ADD_DAMAGE_WEAPON + "]"));
        System.out.println(ConsoleColors.choiceTxtColor("[5] Assassin : [point de vie : " + Assassin.START_HEALTH + " | point de mana : " + Assassin.START_MANA + " | Arme spécifique : " + Dagger.NAME + " | dégâts additionnels de l'arme : "+ Assassin.ADD_DAMAGE_WEAPON + "]"));
        String playerClass = Game.scanner.nextLine();
        return switch (playerClass) {
            case "1" -> new Berserker();
            case "2" -> new Paladin();
            case "3" -> new Archer();
            case "4" -> new Mage();
            case "5" -> new Assassin();
            default -> createPlayer();
        };
    }

}
