import java.util.ArrayList;
public class JerryController extends Thread{
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    Position JerryPosition;
    Position Exit;
    long JerrySpeed = 100;
    public static int JerryDirection;

    JerryController(Position entryPoint, Position exitPoint)
    {
        Squares = Window.Grid;

        JerryPosition = new Position(entryPoint);
        Exit = new Position(exitPoint);
        JerryDirection = 1;
    }
    public void run(){
        while(true){
            moveJerry(JerryDirection);
            try{
                JerryController.sleep(JerrySpeed);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("JerryPos:" + JerryPosition.x + ", " + JerryPosition.y);
            checkWinning();
        }
    }
    private void pauser(){
        try{
            sleep(JerrySpeed);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private Boolean checkMove(Position nextPosition){
        if(nextPosition.x < 0 || nextPosition.x > 29 || nextPosition.y < 0 || nextPosition.y > 29)
            return false;
        if(Squares.get(nextPosition.x).get(nextPosition.y).color == 1)
            return false;
        return true;
    }

    private void moveJerry(int dir){
        Position nextPosition = null;
        switch(dir){
            case 1:
                nextPosition = new Position(JerryPosition.x , JerryPosition.y-1);
                break;
            case 2:
                nextPosition = new Position(JerryPosition.x - 1, JerryPosition.y );
                break;
            case 3:
                nextPosition = new Position(JerryPosition.x , JerryPosition.y + 1);
                break;
            case 4:
                nextPosition = new Position(JerryPosition.x + 1, JerryPosition.y );
                break;
        }
        System.out.println("NextPosition:" + nextPosition.x + "," + nextPosition.y);
        if(checkMove(nextPosition)){
            Squares.get(JerryPosition.x).get(JerryPosition.y).lightMeUp(0);
            JerryPosition.changePosition(nextPosition.x, nextPosition.y);
            Squares.get(JerryPosition.x).get(JerryPosition.y).lightMeUp(2);
        }
    }

    private void checkWinning(){
        if(JerryPosition.x == Exit.x && JerryPosition.y == Exit.y)
            stopTheGame();
    }

    private void stopTheGame(){
        System.out.println("Jerry Wins!\n");
        while (true){
            pauser();
        }
    }
}
