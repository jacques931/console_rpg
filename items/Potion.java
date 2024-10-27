package Cours3.rpg.items;

import Cours3.rpg.personnage.Personnage;

import java.util.Objects;

public class Potion extends Item{
    private String type;
    private double amount;

    public Potion(String name, int price, String type, double amount) {
        super(name, price);
        this.type = type;
        this.amount = amount;
    }

    public String getType(){
        return this.type;
    }

    public double getAmount(){
        return this.amount;
    }

    public boolean usePotion(Personnage player){
        boolean useItem = false;

        if(Objects.equals(this.getType(), "health")){
            useItem = player.heal(this.getAmount());
        } else if(Objects.equals(this.getType(), "mana")){
            useItem = player.restoreMana(this.getAmount());
        }
        return useItem;
    }

    @Override
    public String toString() {
        String typeTxt = this.type=="health"? "point de vie" : this.type=="mana"? "point de mana" : "Inconnu";
        return super.quantity + " " + this.name + " | Effets : restaure " + this.amount + " " + this.type + " | prix : " + super.price + " pièce d'or";
    }
}
