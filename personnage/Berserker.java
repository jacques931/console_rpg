package Cours3.rpg.personnage;

import Cours3.rpg.items.Bow;
import Cours3.rpg.items.Hammer;
import Cours3.rpg.items.Weapon;

public class Berserker extends Personnage{
    public static final double START_HEALTH = 150;
    public static final double START_MANA = 10;
    public static final double ADD_DAMAGE_WEAPON = 5;

    public Berserker() {
        super(START_HEALTH, START_MANA);
    }

    @Override
    public double getSupDamage(Weapon weapon) {
        //Si c'est le bon arme specifique ajoute le damage
        if(weapon instanceof Hammer){
            return ADD_DAMAGE_WEAPON;
        }
        return 0;
    }

    @Override
    public void displayPlayerClass() {
        System.out.println("Class : Berserker");
        System.out.println("Arme spécifique : " + Hammer.NAME + " | dégâts additionnels de l'arme : "+ ADD_DAMAGE_WEAPON);
    }
}
