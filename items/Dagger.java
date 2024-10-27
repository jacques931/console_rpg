package Cours3.rpg.items;

public class Dagger extends Weapon{
    private static final double DAMAGE = 30;
    private static final int PRICE = 150;
    public static final String NAME = "Dagger";
    private static final double MONSTER_DAMAGE_RATIO = 1.2;
    private static final double OBSTACLE_DAMAGE_RATIO = 1.0;

    public Dagger() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO, 0);
    }

    public String asciiArt(){
        return
                "             +-+\n" +
                        "=============| |\n" +
                        "            `:_;'";
    }
}
