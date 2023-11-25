import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread used to represent Tom(computer in the game)
 * The thread follows the computed shortest path with respect to Jerry
 * The computer's target is to catch Jerry
 */
public class TomController extends Thread{
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    public static Position tomPosition;
    public static Position targetPosition;
    long tomSpeed = 130;
    ShortestPath sp1 = new ShortestPath();
    Position[] path;

    Boolean running = true;



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
        path = sp1.generateShortestPath(main.gameMaze.getMaze(), start, end);
    }

    /**
     * If the game has not finished
     * First check if any thread wins the game
     * Then move the Jerry controller
     */
    public void run(){
        while(running) {
            checkWinning();
            try {
                TomController.sleep(tomSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveTom();
        }
    }



    /**
     * The moveTom operation moves Tom's position, calls the ShortestPath to move near Jerry
     */
    private void moveTom(){

            if (path.length > 1) {
                Position nextPosition = new Position(path[1].x, path[1].y);
                Squares.get(tomPosition.x).get(tomPosition.y).lightMeUp(0);

                tomPosition.changePosition(nextPosition.x, nextPosition.y);
                Squares.get(tomPosition.x).get(tomPosition.y).lightMeUp(3);

            }
            targetPosition = new Position(JerryController.JerryPosition.x, JerryController.JerryPosition.y);
            Position start = new Position(tomPosition.x, tomPosition.y);
            Position end = new Position(targetPosition.x, targetPosition.y);
            path = sp1.generateShortestPath(main.gameMaze.getMaze(), start, end);

    }

    /**
     * Checks if Jerry wins the game or Tom wins the game
     */
    private void checkWinning(){
            if (WinCheck.tomWin) {
                stopTheGame();
            } else if (WinCheck.jerryWin) {
                stopTheGame2();
            }

    }
    /**
     * Toms wins the game, shows the lossing message, pauses the thread
     */
    private void stopTheGame(){
        JOptionPane.showMessageDialog(null, "You lose! Don't give up and try again", "Match End", JOptionPane.INFORMATION_MESSAGE);
        running = false;
        main.gameWindow.dispose();
        EntryFrame f1 = new EntryFrame();
    }
    /**
     * Pause the thread
     */
    private void stopTheGame2(){
        running = false;
    }



}
