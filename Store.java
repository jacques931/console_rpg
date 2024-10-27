package Cours3.rpg;

import Cours3.rpg.items.*;
import java.util.ArrayList;
import java.util.Objects;

public class Store {
    private ArrayList<Item> items;

     public Store(){
         this.items = new ArrayList<>();
         this.items.add(new Axe());
         this.items.add(new Hammer());
         this.items.add(new Bow());
         this.items.add(new Dagger());
         this.items.add(new Stick());
         this.items.add(new Potion("Potion de vie",50,"health",30));
         this.items.add(new Potion("Potion de mana",50,"mana",20));
     }

     public ArrayList<Item> getWeapons(){
         return this.items;
     }

     public Item openStore(int currentGold){
         System.out.println("Choissisez une action : ");
         System.out.println(ConsoleColors.choiceTxtColor("[0] Retour"));
         // Affiche tous les items dans le store
         for (int i = 0; i < this.items.size(); i++) {
             int index = i+1;
             System.out.println(ConsoleColors.choiceTxtColor("[" + index  + "] - "+ this.items.get(i)));
         }
         String id = Game.scanner.nextLine();
         if(!Objects.equals(id, "0")){
             if (id.matches("[1-" + (this.items.size()) + "]+")){
                 Item chooseItem = this.items.get(Integer.parseInt(id)-1);
                 // Si il a assez d'argent
                 if(chooseItem.getPrice()<=currentGold){
                     return chooseItem;
                 } else{
                     System.out.println(ConsoleColors.textColorDanger("Vous n'avez pas assez de piècee d'or !"));
                     return openStore(currentGold);
                 }

             }else{
                 return openStore(currentGold);
             }
         } else {
             return null;
         }
     }
}
