package Cours3.rpg.personnage;

import Cours3.rpg.items.Axe;
import Cours3.rpg.items.Bow;
import Cours3.rpg.items.Weapon;

public class Paladin extends Personnage{
    public static final double START_HEALTH = 180;
    public static final double START_MANA = 40;
    public static final double ADD_DAMAGE_WEAPON = 15;

    public Paladin() {
        super(START_HEALTH, START_MANA);
    }

    @Override
    public double getSupDamage(Weapon weapon) {
        //Si c'est le bon arme specifique ajoute le damage
        if(weapon instanceof Axe){
            return ADD_DAMAGE_WEAPON;
        }
        return 0;
    }

    @Override
    public void displayPlayerClass() {
        System.out.println("Class : Paladin");
        System.out.println("Arme spécifique : " + Axe.NAME + " | dégâts additionnels de l'arme : "+ ADD_DAMAGE_WEAPON);
    }
}
