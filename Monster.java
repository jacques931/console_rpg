package Cours3.rpg;

import Cours3.rpg.personnage.Personnage;

public class Monster extends Destructible{
    private static final double LIFE = 80;
    private static final double DAMAGE = 20;
    private static final int GOLD = 50;
    public static final int XP = 100;

    public Monster(Position position) {
        super(LIFE,position,GOLD, XP);
    }

    public void attack(Personnage player){
        player.hit(DAMAGE);
    }
}
