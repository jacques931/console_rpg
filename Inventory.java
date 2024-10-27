package Cours3.rpg;

import Cours3.rpg.items.Item;
import Cours3.rpg.items.Weapon;
import Cours3.rpg.personnage.Personnage;

import java.util.ArrayList;
import java.util.Objects;

public class Inventory {
    private ArrayList<Item> items;
    private Personnage player;

    public Inventory(Personnage player){
        this.items = new ArrayList<>();
        this.player = player;
    }

    public void addItemInInventory(Item item){
        // augmente la quantité si ce n'est pas une arme
        if(!(item instanceof Weapon)){
            int itemId = getItemInInventory(item);
            if(itemId!=-1){
                System.out.println("double");
                this.items.get(itemId).setQuantity(1);
            }
            else {
                this.items.add(item);
            }
        } else {
            this.items.add(item);
        }
    }

    public void removeItemInInventory(Item item){
        // augmente la quantité si ce n'est pas une arme
        if(!(item instanceof Weapon)){
            int itemId = getItemInInventory(item);
            if(itemId!=-1){
                Item inventoryItem = this.items.get(itemId);
                inventoryItem.setQuantity(-1);
                if(inventoryItem.getQuantity()<=0){
                    this.items.remove(item);
                }
            }

        } else {
            this.items.remove(item);
        }
    }

    public int inventorySize(){
        return this.items.size();
    }

    public boolean haveItem(){
        return !this.items.isEmpty();
    }

    // Normalement c'est mieux d'utiliser un id
    public Item getItemInInventory(int position){
        return this.items.get(position);
    }

    public int getItemInInventory(Item item){
        for (int i = 0; i < this.items.size(); i++) {
            if(Objects.equals(this.items.get(i).getName(), item.getName())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String res = "Votre Inventaire : \n";
        for (int i = 0; i < this.items.size(); i++) {
            int index = i+1;
            res += this.player.getWeapon()==this.items.get(i)? "[Equip] " :"";
            res += ConsoleColors.choiceTxtColor("["+ index +"] " + this.items.get(i) + "\n");
        }
        if(!this.haveItem()){
            res += "Vous n'avez aucun item";
        }
        return res;
    }
}
