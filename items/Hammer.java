package Cours3.rpg.items;

public class Hammer extends Weapon{
    private static final double DAMAGE = 25;
    private static final int PRICE = 100;
    public static final String NAME = "Hammer";
    private static final double MONSTER_DAMAGE_RATIO = 0.7;
    private static final double OBSTACLE_DAMAGE_RATIO = 1.3;

    public Hammer() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO, 0);
    }

    public String asciiArt(){
        return
                "             +-+\n" +
                        "=============| |\n" +
                        "            `:_;'";
    }

}
