package Cours3.rpg.items;

public class Stick extends Weapon{
    private static final double DAMAGE = 50;
    private static final int PRICE = 250;
    public static final String NAME = "Stick";
    private static final double MONSTER_DAMAGE_RATIO = 1.2;
    private static final double OBSTACLE_DAMAGE_RATIO = 1.2;
    public static final double USE_MANA = 20;

    public Stick() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO, USE_MANA);
    }

    public String asciiArt(){
        return
                "             +-+\n" +
                        "=============| |\n" +
                        "            `:_;'";
    }
}
