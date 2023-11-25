import javax.swing.*;
import java.util.ArrayList;

/**
 * A thread used to represent Jerry(the player in the game)
 * The thread is controlled by arrow keys on the keyboard
 * The player's target is to get to the exit point of the maze before getting caught by Tom (the computer)
 *
 */

public class JerryController extends Thread{
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    public static Position JerryPosition;
    public static Position Exit;
    long JerrySpeed = 100;
    public static int JerryDirection;
    Boolean running = true;



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

    /**
     * If the game has not finished
     * First check if any thread wins the game
     * Then move the Jerry controller
     */
    public void run(){
        while(running){
            checkWinning();
            try{
                JerryController.sleep(JerrySpeed);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
                moveJerry(JerryDirection);
        }
    }

    /**
     * Check if the next move of Jerry is legal of not
     * @param nextPosition the next grid that Jerry are going to walk to
     * @return Returns true indicating the next move is legal, false indicating next move is illegal
     */

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
            switch (dir) {
                case 1:
                    nextPosition = new Position(JerryPosition.x, JerryPosition.y - 1);
                    break;
                case 2:
                    nextPosition = new Position(JerryPosition.x - 1, JerryPosition.y);
                    break;
                case 3:
                    nextPosition = new Position(JerryPosition.x, JerryPosition.y + 1);
                    break;
                case 4:
                    nextPosition = new Position(JerryPosition.x + 1, JerryPosition.y);
                    break;
            }
            if (checkMove(nextPosition)) {
                Squares.get(JerryPosition.x).get(JerryPosition.y).lightMeUp(0);
                JerryPosition.changePosition(nextPosition.x, nextPosition.y);
                Squares.get(JerryPosition.x).get(JerryPosition.y).lightMeUp(2);
                main.gameMaze.maze[JerryPosition.x][JerryPosition.y] = 2;
            }
    }

    /**
     * Checks if Jerry wins the game or Tom wins the game
     */

    private void checkWinning(){
            if (WinCheck.jerryWin == true) {
                stopTheGame();
            } else if (WinCheck.tomWin == true) {
                stopTheGame2();
            }
    }

    /**
     * Jerry wins the game, shows the winning message, pauses the thread
     */
    private void stopTheGame(){
        JOptionPane.showMessageDialog(null, "You win! Congratulation!", "Match End", JOptionPane.INFORMATION_MESSAGE);
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
