package Cours3.rpg;

public class ConsoleColors {
    // Couleurs du texte
    public static final String RESET = "\033[0m";        // Reset
    public static final String BLACK = "\033[0;30m";     // Noir
    public static final String RED = "\033[0;31m";       // Rouge
    public static final String GREEN = "\033[0;32m";     // Vert
    public static final String YELLOW = "\033[0;33m";    // Jaune
    public static final String LIGHT_YELLOW = "\033[1;33m"; // Jaune clair
    public static final String BLUE = "\033[0;34m";      // Bleu
    public static final String PURPLE = "\033[0;35m";    // Violet
    public static final String CYAN = "\033[0;36m";      // Cyan
    public static final String WHITE = "\033[0;37m";     // Blanc
    public static final String LIGHT_GRAY = "\033[0;37m"; //Gris

    public static String choiceTxtColor(String choiceTxt) {
        String colorTxt = YELLOW + choiceTxt.substring(0, 3) + RESET;
        String normalTxt = choiceTxt.substring(3);

        return colorTxt + normalTxt;
    }

    public static String textColorInformation(String text){
        return PURPLE + text + RESET;
    }

    public static String textColorDanger(String text){
        return RED + text + RESET;
    }


    // Exemple d'utilisation
//    public static void main(String[] args) {
//        System.out.println(RED + "Ce texte est en rouge" + RESET);
//        System.out.println(GREEN + "Ce texte est en vert" + RESET);
//        System.out.println(YELLOW + "Ce texte est en jaune" + RESET);
//    }
}