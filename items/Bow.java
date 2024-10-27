package Cours3.rpg.items;

public class Bow extends Weapon{
    private static final double DAMAGE = 30;
    private static final int PRICE = 200;
    public static final String NAME = "Bow";
    private static final double MONSTER_DAMAGE_RATIO = 1.2;
    private static final double OBSTACLE_DAMAGE_RATIO = 0.5;

    public Bow() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO, 0);
    }

    public String asciiArt(){
        return "  (\n" +
                "    \\\n" +
                "     )\n" +
                "##-------->\n" +
                "     )\n" +
                "    /\n" +
                "   (";
    }

}
