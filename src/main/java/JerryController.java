import javax.swing.*;
import java.util.ArrayList;

/**
 * A thread used to represent Jerry (the player in the game).
 * The thread is controlled by arrow keys on the keyboard.
 * The player's target is to get to the exit point of the maze before getting caught by Tom (the computer).
 */
public class JerryController extends Thread {
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    public static Position JerryPosition;
    public static Position Exit;
    long JerrySpeed = 100;
    public static int JerryDirection;
    Boolean running = true;


    /**
     * Initializes the JerryController object.
     *
     * @param entryPoint The starting point of Jerry in the maze.
     * @param exitPoint  The destination point of Jerry in the maze.
     */
    JerryController(Position entryPoint, Position exitPoint) {
        Squares = Window.Grid;

        JerryPosition = new Position(entryPoint);
        Exit = new Position(exitPoint);
        JerryDirection = 1;
    }

    /**
     * If the game has not finished, first checks if any thread has won the game,
     * then moves the Jerry controller.
     */
    public void run() {
        while (running) {
            checkWinning();
            try {
                JerryController.sleep(JerrySpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveJerry(JerryDirection);
        }
    }

    /**
     * Checks if the next move of Jerry is legal or not.
     *
     * @param nextPosition The next grid that Jerry is going to walk to.
     * @return Returns true if the next move is legal, false otherwise.
     */
    private Boolean checkMove(Position nextPosition) {
        if (nextPosition.x < 0 || nextPosition.x > 29 || nextPosition.y < 0 || nextPosition.y > 29)
            return false;
        if (Squares.get(nextPosition.x).get(nextPosition.y).color == 1)
            return false;
        return true;
    }

    /**
     * Moves Jerry in one of the four directions: up, down, left, or right.
     *
     * @param dir The direction in which Jerry moves.
     */
    private void moveJerry(int dir) {
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
     * Checks if Jerry has won the game or if Tom has won the game.
     */
    private void checkWinning() {
        if (WinCheck.jerryWin) {
            stopTheGame();
        } else if (WinCheck.tomWin) {
            stopTheGame2();
        }
    }

    /**
     * Stops the game and shows the winning message if Jerry wins.
     */
    private void stopTheGame() {
        JOptionPane.showMessageDialog(null, "You win! Congratulations!", "Match End", JOptionPane.INFORMATION_MESSAGE);
        running = false;
        main.gameWindow.dispose();
        EntryFrame f1 = new EntryFrame();
    }

    /**
     * Stops the game.
     */
    private void stopTheGame2() {
        running = false;
    }
}