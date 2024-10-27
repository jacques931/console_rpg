package Cours3.rpg;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.setPosition(x,y);
    }

    public Position(){
        this.setPosition(0,0);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(Position pos) {
        this.x += pos.getX();
        this.y += pos.getY();
    }

    public static Position[] getDirection() {
        return new Position[] {
                new Position(-1, 0),
                new Position(0, 1),
                new Position(1, 0),
                new Position(0, -1)
        };
    }

    public static String[] getDirectionName(){
        return new String[] {"Haut", "Droite", "Bas", "Gauche"};
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        if(!(obj instanceof Position)){
            return false;
        }
        Position pos = (Position) obj;
        return this.x== pos.getX() && this.y== pos.getY();
    }
}
