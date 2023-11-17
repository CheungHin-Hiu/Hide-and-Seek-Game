public class Position {
    public int x;      //X-coordiante of the character
    public int y;      //Y-coordiante of the character

    public Position(int x, int y){
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

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }


}
