public class Position {
    public int x;
    public int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position another){
        this(another.x, another.y);
    }

    public void changePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

}