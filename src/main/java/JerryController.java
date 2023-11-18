import java.util.ArrayList;
public class JerryController extends Thread{
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    public static Position JerryPosition;
    Position Exit;
    long JerrySpeed = 100;
    public static int JerryDirection;
    static public Boolean jerryWin = false;


     /**
     * Jerry Controller initialize the Jerry object
     * @param entryPoint Jerry starts from the start of the maze
     * @param exitPoint Jerry destination is the end of the maze
     */

    JerryController(Position entryPoint, Position exitPoint)
    {
        Squares = Window.Grid;

        JerryPosition = new Position(entryPoint);
        Exit = new Position(exitPoint);
        JerryDirection = 1;
    }
    public void run(){
        while(true){
            checkWinning();
            moveJerry(JerryDirection);
            try{
                JerryController.sleep(JerrySpeed);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
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

    /**
     * moveJerry is for moving Jerry in four directions, Jerry will check is it can move before moving
     * @param dir moves in one of the following direction, either up, down, left or right
     */
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
        if(checkMove(nextPosition)){
            Squares.get(JerryPosition.x).get(JerryPosition.y).lightMeUp(0);
            JerryPosition.changePosition(nextPosition.x, nextPosition.y);
            Squares.get(JerryPosition.x).get(JerryPosition.y).lightMeUp(2);
        }
    }

    private void checkWinning(){
        if(JerryPosition.x == Exit.x && JerryPosition.y == Exit.y) {
            jerryWin = true;
            stopTheGame();
        }
        else if (TomController.tomWin == true){
            stopTheGame2();
        }
    }

    private void stopTheGame(){
        System.out.println("Jerry Wins!\n");
        while (true){
            pauser();
        }
    }
    private void stopTheGame2(){
        while (true){
            pauser();
        }
    }
}
