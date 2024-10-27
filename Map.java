package Cours3.rpg;

import Cours3.rpg.personnage.Personnage;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private static final int DEFAULT_MAP_SIZE = 15;
    public static final int NB_OBSTACLE = 40;
    public static final int NB_MONSTER = 20;
    public static final Position END_POSITION = new Position(DEFAULT_MAP_SIZE-4,DEFAULT_MAP_SIZE-1);
    private String[][] maps;
    private int mapSize = 0;
    private Position startPosition;
    private ArrayList<Destructible> destructibleElements;

    public Map(Position startPosition){
        this.maps = new String[DEFAULT_MAP_SIZE][DEFAULT_MAP_SIZE];
        this.mapSize = DEFAULT_MAP_SIZE;
        this.startPosition = startPosition;
        this.destructibleElements = new ArrayList<>();
        this.initMap();
    }

    public Map(int size, Position startPosition){
        this.maps = new String[size][size];
        this.mapSize = size;
        this.startPosition = startPosition;
        this.destructibleElements = new ArrayList<>();
        this.initMap();
    }

    // Initialise la map avec les mur, obstacle et monstre
    public void initMap(){
        for (int i = 0; i < this.maps.length; i++) {
            for (int j = 0; j < this.maps[i].length; j++) {
                // Première ligne, dernière ligne, première colonne, dernière colonne
                if (i == 0 || i == this.maps.length - 1 || j == 0 || j == this.maps[i].length - 1) {
                    this.setNewValeur(i, j, "[x]");
                }
                else{
                    this.setNewValeur(i,j);
                }
            }
        }

        this.setNewValeur(this.startPosition.getX(), this.startPosition.getY(), "[P]");
        this.setNewValeur(END_POSITION.getX(),END_POSITION.getY());

        Random random = new Random();
        // Ajoute 40 obstacle
        int countObstacle = NB_OBSTACLE;
        while (countObstacle > 0) {
            int i = random.nextInt(this.maps.length);
            int j = random.nextInt(this.maps[0].length);

            if (this.giveMapElement(i,j)==0) {
                destructibleElements.add(new Obstacle(new Position(i,j)));
                this.setNewValeur(i, j, "[o]");
                countObstacle--;
            }
        }

        // Ajoute 20 monster aléatoirement
        int countMonster = NB_MONSTER;
        while (countMonster > 0) {
            int i = random.nextInt(this.maps.length);
            int j = random.nextInt(this.maps[0].length);

            if (this.giveMapElement(i,j)==0) {
                destructibleElements.add(new Monster(new Position(i,j)));
                this.setNewValeur(i, j, "[m]");
                countMonster--;
            }
        }
    }

    // Permet de recuperer le code des elements l'entour
    public int[] getNextElement(Position playerPosition){
        int[] move = new int[4];
        Position[] directions = Position.getDirection();

        for (int i = 0; i < move.length; i++) {
            Position nextPosition  = new Position(
                    playerPosition.getX() + directions[i].getX(),
                    playerPosition.getY() + directions[i].getY()
            );
            move[i] = this.giveMapElement(nextPosition.getX(),nextPosition.getY());
        }
        return move;
    }

    public boolean isCorrectObstacle(int idObstacle, Position playerPosition){
        for(int nextElement : this.getNextElement(playerPosition)){
            if(nextElement==idObstacle){
                return true;
            }
        }
        return false;
    }

    // Permet de recuperer les instances de monstre ou obstacle
    public Destructible[] getDestructibes(Position playerPosition){
        Destructible[] destructibles = new Destructible[4];
        Position[] directions = Position.getDirection();
        for (int i = 0; i < destructibles.length; i++) {
            Position nextPosition  = new Position(
                    playerPosition.getX() + directions[i].getX(),
                    playerPosition.getY() + directions[i].getY()
            );
            destructibles[i] = this.getDestructibleElement(nextPosition);
        }
        return destructibles;
    }

    public void setNewValeur(int row, int col, String value){
        this.maps[row][col] = value;
    }

    public void setNewValeur(int row, int col){
        this.maps[row][col] = "[ ]";
    }

    public String getMapsElement(int row, int col){
        if(row<0 || col<0 || row>this.mapSize || col>this.mapSize) return "";
        return this.maps[row][col];
    }

    public int giveMapElement(int row, int col){
        // 0 : vide / 1 : monstre / 2 : obstacle / 3 : mur
        String mapElement = this.getMapsElement(row,col);
        return switch (mapElement) {
            case "[ ]" -> 0;
            case "[m]" -> 1;
            case "[o]" -> 2;
            case "[x]" -> 3;
            default -> -1;
        };
    }

    // Renvoie l'element qui est sur la position donné
    public Destructible getDestructibleElement(Position position) {
        for (Destructible element : this.destructibleElements){
            if(element.position.equals(position)){
                return element;
            }
        }
        return null;
    }

    public void removeDestructible(Destructible destructible){
        this.destructibleElements.remove(destructible);
        this.setNewValeur(destructible.position.getX(), destructible.position.getY());
    }

    public String toString(){
        String res = "";
        for (int i = 0; i < this.maps[0].length; i++) {
            String line = "";
            for(String e : this.maps[i]){
                line+=this.colorMapElement(e);
            }
            res+=line + "\n";
        }

        return res;
    }

    private String colorMapElement(String element){
        return switch (element) {
            case "[x]" -> ConsoleColors.LIGHT_GRAY + element + ConsoleColors.RESET;
            case "[P]" -> ConsoleColors.BLUE + element + ConsoleColors.RESET;
            case "[m]" -> ConsoleColors.RED + element + ConsoleColors.RESET;
            case "[o]" -> ConsoleColors.GREEN + element + ConsoleColors.RESET;
            default -> element;
        };
    }

}
