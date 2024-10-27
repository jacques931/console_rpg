package Cours3.rpg;
import java.util.Scanner;
import Cours3.rpg.personnage.Personnage;

public class Game {
    public static Scanner scanner;

    public Game(){
        scanner = new Scanner(System.in);
    }

    public void start(){
        Personnage player = Personnage.createPlayer();
        Map map = new Map(player.getPosition());
        PlayerAction action = new PlayerAction(map,player);

        Position endPosition = Map.END_POSITION;

        while(!endPosition.equals(player.getPosition()) && player.isAlive()){
            System.out.println(map);
            action.displayActionOption();
        }

        if(!player.isAlive()){
            System.out.println(ConsoleColors.textColorDanger("\nGame over, vous êtes mort !"));
        } else {
            System.out.println(ConsoleColors.LIGHT_YELLOW);
            System.out.println("Toutes mes félicitations, vous avez réussi à vous enfuir !");
            player.displayPlayerClass();
            System.out.println("Vous avez réussi à obtenir " + player.getXp() + " points d'expérience et " + player.getGold() + " pièces d'or.");
            this.displayScore(player);
            System.out.println(ConsoleColors.RESET);
        }

    }

    private void displayScore(Personnage player) {
        double maxXp = Map.NB_MONSTER * Monster.XP + Map.NB_OBSTACLE * Obstacle.XP;
        double playerXp = player.getXp();

        if (playerXp == maxXp) {
            System.out.println("Bravo, vous avez réussi à détruire et tuer tous les monstres du donjon ! Vous avez atteint l'XP maximale, un exploit digne des plus grands héros !");
        } else if (playerXp >= 0.75 * maxXp) {
            System.out.println("Félicitations, vous avez abattu plus de 75% des ennemis et obstacles du donjon ! Encore un petit effort pour finir le travail !");
        } else if (playerXp >= 0.5 * maxXp) {
            System.out.println("Pas mal, vous avez fait la moitié du donjon, mais ce n'est pas encore ça.");
        } else if (playerXp >= 0.25 * maxXp) {
            System.out.println("Vous auriez pu faire mieux que ça !");
        } else {
            System.out.println("Vous avez eu de la chance, sans trop vous forcer, comme un lâche !");
        }
    }
}
