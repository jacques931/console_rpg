package Cours3.rpg.personnage;

import Cours3.rpg.items.Bow;
import Cours3.rpg.items.Dagger;
import Cours3.rpg.items.Weapon;

public class Assassin extends Personnage{
    public static final double START_HEALTH = 110;
    public static final double START_MANA = 10;
    public static final double ADD_DAMAGE_WEAPON = 20;

    public Assassin() {
        super(START_HEALTH, START_MANA);
    }

    @Override
    public double getSupDamage(Weapon weapon) {
        //Si c'est le bon arme specifique ajoute le damage
        if(weapon instanceof Dagger){
            return ADD_DAMAGE_WEAPON;
        }
        return 0;
    }

    @Override
    public void displayPlayerClass() {
        System.out.println("Class : Assassin");
        System.out.println("Arme spécifique : " + Dagger.NAME + " | dégâts additionnels de l'arme : "+ ADD_DAMAGE_WEAPON);
    }
}
