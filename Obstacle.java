package Cours3.rpg;

public class Obstacle extends Destructible{
    private static final double LIFE = 60;
    private static final int GOLD = 30;
    public static final int XP = 40;;

    public Obstacle(Position position) {
        super(LIFE,position,GOLD,XP);
    }
}
