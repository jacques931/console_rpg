package Cours3.rpg.items;

public class Item {
    private int id; //Inutile pour le moment
    protected int price;
    protected String name;
    protected int quantity;

    public Item(String name, int price){
        this.price = price;
        this.name = name;
        this.quantity = 1;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity += quantity;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + " - prix : " + this.price;
    }
}
