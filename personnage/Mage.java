package Cours3.rpg.personnage;

import Cours3.rpg.items.Bow;
import Cours3.rpg.items.Stick;
import Cours3.rpg.items.Weapon;

public class Mage extends Personnage{
    public static final double START_HEALTH = 100;
    public static final double START_MANA = 100;
    public static final double ADD_DAMAGE_WEAPON = 10;

    public Mage() {
        super(START_HEALTH, START_MANA);
    }

    @Override
    public double getSupDamage(Weapon weapon) {
        //Si c'est le bon arme specifique ajoute le damage
        if(weapon instanceof Stick){
            return ADD_DAMAGE_WEAPON;
        }
        return 0;
    }

    @Override
    public void displayPlayerClass() {
        System.out.println("Class : Mage");
        System.out.println("Arme spécifique : " + Stick.NAME + " | dégâts additionnels de l'arme : "+ ADD_DAMAGE_WEAPON);
    }
}
