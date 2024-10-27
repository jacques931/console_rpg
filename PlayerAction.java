package Cours3.rpg;

import Cours3.rpg.items.Item;
import Cours3.rpg.items.Potion;
import Cours3.rpg.items.Weapon;
import Cours3.rpg.personnage.Personnage;

import java.io.Console;
import java.util.Objects;

// Dans le projet, elle ne sert pas vraiment, on peut directement le mettre dans la class Game
public class PlayerAction{
    private Map map;
    private Personnage player;

    public PlayerAction(Map map, Personnage player){
        this.map = map;
        this.player = player;
    }

    public void displayActionOption() {
        System.out.println("Veuillez choisir une action :");
        if(this.map.isCorrectObstacle(0, this.player.getPosition())) System.out.println(ConsoleColors.choiceTxtColor("[1] Mouvement"));
        if((this.map.isCorrectObstacle(1, this.player.getPosition()) || this.map.isCorrectObstacle(2,this.player.getPosition()))){
            if(this.player.getWeapon()==null){
                System.out.println(ConsoleColors.textColorDanger("Vous devez avoir une arme pour attaquer"));
            } else if(!this.player.getWeapon().canAttack(this.player.getMana())){
                System.out.println(ConsoleColors.textColorDanger("Vous n'avez pas assez de mana pour attaquer"));
            }
            else{
                System.out.println(ConsoleColors.choiceTxtColor("[2] Skill"));
            }
        }
        System.out.println(ConsoleColors.choiceTxtColor("[3] Boutique"));
        System.out.println(ConsoleColors.choiceTxtColor("[4] Inventaire"));
        System.out.println(ConsoleColors.choiceTxtColor("[5] Status joueur"));
        String action = Game.scanner.nextLine();
        switch (action){
            case "1" :{
                if(this.map.isCorrectObstacle(0, this.player.getPosition())){
                    displayMove();
                    break;
                }
                displayActionOption();
            }
            case "2" :{

                if((this.map.isCorrectObstacle(1, this.player.getPosition()) || this.map.isCorrectObstacle(2, this.player.getPosition()) && this.player.getWeapon()!=null) && this.player.getWeapon().canAttack(this.player.getMana())){
                    displayAttack();
                    break;
                }
                displayActionOption();
            }
            case "3" :{
                gotoShop();
                break;
            }
            case "4" :{
                viewInventory();
                break;
            }
            case "5":{
                this.player.displayPlayerStatus();
                break;
            }
            default:{
                displayActionOption();
            }
        }
    }

    private void displayMove() {
        System.out.println("Veuillez choisir une direction :");
        int[] nextElements = this.map.getNextElement(this.player.getPosition());
        String[] directions = Position.getDirectionName();
        System.out.println(ConsoleColors.choiceTxtColor("[0] Retour"));
        for (int i = 0; i < nextElements.length; i++) {
            if(nextElements[i]==0){
                int index = i+1;
                System.out.println(ConsoleColors.choiceTxtColor("["+ index +"] " + directions[i]));
            }
        }
        String mouvement = Game.scanner.nextLine();

        //Retourne en arriere
        if(mouvement.matches("[1-4]")){
            int directionIndex = Integer.parseInt(mouvement) - 1;
            if (nextElements[directionIndex] == 0) {
                Position[] positions = Position.getDirection();
                this.movePlayer(positions[directionIndex]);
            } else {
                displayMove();
            }
        } else if (!mouvement.equals("0")) {
            displayMove();
        }
    }

    // Deplace le joueur dans la variable et dans la map
    private void movePlayer(Position position){
        map.setNewValeur(this.player.getPosition().getX(),this.player.getPosition().getY());
        this.player.getPosition().move(position);
        map.setNewValeur(this.player.getPosition().getX(),this.player.getPosition().getY(),"[P]");
    }

    private void displayAttack() {
        // Montre les degats qu'il va faire
        this.player.getWeapon().displayDamage();
        // Recupere tous les elements touchables
        Destructible[] destructibes = this.map.getDestructibes(this.player.getPosition());
        String[] directions = Position.getDirectionName();
        // Affiche les options possible
        System.out.println(ConsoleColors.choiceTxtColor("[0] Retour"));
        for (int i = 0; i < destructibes.length; i++) {
            if(destructibes[i]!=null){
                int index = i+1;
                System.out.println(ConsoleColors.choiceTxtColor("["+ index +"] " + directions[i] + " | point de vie : " + destructibes[i].getHealth()));
            }
        }

        // Recupere l'action et l'execute
        String action = Game.scanner.nextLine();
        // Si il fait un retour (0) on laisse terminer le boucle pour revenir au action
        if (!Objects.equals(action, "0")){
            if (action.matches("[1-4]")) {
                int index = Integer.parseInt(action)-1;
                Destructible destructible = destructibes[index];
                if(destructible!=null){
                    boolean isAlive = attack(destructible);
                    // Enleve le mana meme si certain arme ne consomme pas de mana
                    double useMana = this.player.getWeapon().getUseMana();
                    this.player.setMana(-useMana);
                    if(!isAlive){
                        this.map.removeDestructible(destructible);
                    }

                } else{
                    displayAttack();
                }
            } else{
                displayAttack();
            }
        }
    }

    private boolean attack(Destructible destructible){
        boolean isMonster = destructible instanceof Monster;
        boolean isAlive;
        if (isMonster) {
            Monster monster = (Monster) destructible;
            isAlive = this.player.getWeapon().atack(monster);
            this.displayDestructibleStatus(isAlive, destructible,"monstre");
            // Attaque le joueur si le monstre est encore en vie
            if(isAlive){
                monster.attack(this.player);
            }
        } else {
            isAlive = this.player.getWeapon().atack((Obstacle) destructible);
            this.displayDestructibleStatus(isAlive, destructible,"obstacle");
        }

        return isAlive;
    }

    private void displayDestructibleStatus(boolean isAlive, Destructible destructible, String name){
        if(isAlive){
            System.out.println(ConsoleColors.textColorInformation("Il ne reste que "+ destructible.getHealth() + " point de vie" ));
        } else{
            System.out.println(ConsoleColors.textColorInformation("Vous avez gagner " + destructible.getGiveGold() + " pièce d'or"));
            System.out.println(ConsoleColors.textColorInformation("Vous avez gagner " + destructible.getGiveXp() + " point d'experience"));
            System.out.println(ConsoleColors.textColorInformation("Vous avez detruit le " + name));
            this.player.setGold(destructible.getGiveGold());
            this.player.setXp(destructible.getGiveXp());
        }
    }

    private void gotoShop() {
        Store store = new Store();
        this.player.displayGold();
        Item item = store.openStore(this.player.getGold());
        // Retire l'argent et l'ajout dans l'inventaire si il arrive a recupere l'item
        if(item!=null){
            this.player.setGold(-item.getPrice());
            this.player.displayGold();

            this.player.getInventory().addItemInInventory(item);
            if(this.player.getWeapon()==null && item instanceof Weapon){
                Weapon newWeapon = (Weapon) item;
                newWeapon.setSupDamage(this.player.getSupDamage(newWeapon));
                this.player.setWeapon(newWeapon);
            }

            System.out.println(this.player.getInventory());
        }
    }

    private void viewInventory() {
        Inventory inventory = this.player.getInventory();
        System.out.println(inventory);
        // Ouvrir la possibilité de faire un action seulement si il a 1 item
        if (inventory.haveItem()){
            System.out.println("Choissisez une action : ");
            System.out.println(ConsoleColors.choiceTxtColor("[0] Retour"));
            System.out.println(ConsoleColors.choiceTxtColor("[?] Utiliser une potion / equiper l'arme"));
            String action = Game.scanner.nextLine();
            // Verifie qu'il s'agit d'un chiffre puis qu'elle est dans la liste
            if(action.matches("[0-9]+") && inventory.inventorySize()>=Integer.parseInt(action)){
                int idItem = Integer.parseInt(action)-1;
                // Utilise l'item si l'action n'est pas retour
                if (idItem>=0){
                    Item item = this.player.getInventory().getItemInInventory(idItem);
                    if (item instanceof Weapon){
                        Weapon newWeapon = (Weapon) item;
                        newWeapon.setSupDamage(this.player.getSupDamage(newWeapon));
                        this.player.setWeapon(newWeapon);
                    }
                    if(item instanceof Potion){
                        Potion potion = (Potion) item;
                        boolean useItem = potion.usePotion(this.player);
                        if(useItem){
                            System.out.println(ConsoleColors.textColorInformation("Vous avez utiliser une potion !"));
                            inventory.removeItemInInventory(item);
                            this.player.displayPlayerStatus();
                        }
                    }
                }
            }
            else {
                viewInventory();
            }
        }
    }

}
