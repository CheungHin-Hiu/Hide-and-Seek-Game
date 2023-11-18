import java.util.ArrayList;

public class TomController extends Thread{
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    Position tomPosition;
    Position targetPosition;
    long tomSpeed = 270;
    ShortestPath sp1 = new ShortestPath();
    Position[] path;
    static public Boolean tomWin = false;

    /**
     * TomController initialize the Tom object
     * @param exitPoint Tom starts from the exit of the maze
     * @param entryPoint Tom target destination is Jerry's position, which is equivalent to entry of the maze
     */
    TomController(Position exitPoint, Position entryPoint){
        Squares = Window.Grid;
        tomPosition = new Position(exitPoint);
        targetPosition = new Position(entryPoint);
        Position start = new Position(exitPoint.x, exitPoint.y);
        Position end = new Position(entryPoint.x, entryPoint.y);
        path = sp1.generateShortestPath(main.array,start, end);
    }

    public void run(){
        while(true) {
            checkWinning();
            moveTom();
            try {
                TomController.sleep(tomSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void pauser(){
        try{
            sleep(tomSpeed);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * The moveTom operation moves Tom's position, calls the ShortestPath to move near Jerry
     */
    private void moveTom(){
        System.out.println("TomPos:" + tomPosition.x + ", " + tomPosition.y);
        Position nextPosition = new Position(path[1].x, path[1].y);
        System.out.println("NextPosition:" + nextPosition.x + "," + nextPosition.y);
        Squares.get(tomPosition.x).get(tomPosition.y).lightMeUp(0);
        tomPosition.changePosition(nextPosition.x, nextPosition.y);
        Squares.get(tomPosition.x).get(tomPosition.y).lightMeUp(3);
        targetPosition = new Position(JerryController.JerryPosition.x, JerryController.JerryPosition.y);
        Position start = new Position(tomPosition.x, tomPosition.y);
        Position end = new Position(targetPosition.x, targetPosition.y);
        path = sp1.generateShortestPath(main.array,start, end);
    }

    private void checkWinning(){
        if(tomPosition.x == targetPosition.x && tomPosition.y == targetPosition.y) {
            tomWin = true;
            stopTheGame();
        } else if (JerryController.jerryWin == true) {
            stopTheGame2();
        }
    }

    private void stopTheGame(){
        System.out.println("Tom Wins");
        while(true){
            pauser();
        }
    }
    private void stopTheGame2(){
        while (true){
            pauser();
        }
    }

}
