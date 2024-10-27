package Cours3.rpg.items;

public class Axe extends Weapon{
    private static final double DAMAGE = 35;
    private static final int PRICE = 150;
    public static final String NAME = "Axe";

    private static final double MONSTER_DAMAGE_RATIO = 0.9;
    private static final double OBSTACLE_DAMAGE_RATIO = 1.2;

    public Axe() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO, 0);
    }

    public String asciiArt() {
        return
                "    /'-./\\_\n" +
                        "   :    ||,>\n" +
                        "    \\.-'||\n" +
                        "        ||\n" +
                        "        ||\n" +
                        "        ||  \n";
    }

}
