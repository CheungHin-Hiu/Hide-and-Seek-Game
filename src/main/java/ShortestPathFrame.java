import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ShortestPathFrame extends JFrame implements KeyListener {
    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    public static int width = 30;
    public static int height = 30;

    public static

    Position entry;
    Position exit;
    ShortestPath sp1 = new ShortestPath();
    Position[] path;


    public ShortestPathFrame(int[][] maze){

        Grid = new ArrayList<ArrayList<DataOfSquare>>();
        ArrayList<DataOfSquare> data;
        for(int i = 0; i<height; i++){
            if(maze[i][0] == 0){
                entry = new Position(i, 0);
                break;
            }
        }
        for(int i = 0; i < height; i++){
            if(maze[i][29] == 0){
                exit = new Position(i, 29);
                break;
            }
        }
        for(int i = 0; i<width; i++) {
            data = new ArrayList<DataOfSquare>();
            for (int j = 0; j < height; j++) {
                DataOfSquare c = new DataOfSquare(maze[i][j]);
                data.add(c);
            }
            Grid.add(data);
        }

        //Setting up the layout of the panel
        getContentPane().setLayout(new GridLayout(30,30,0,0));

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                getContentPane().add(Grid.get(i).get(j).grid);
            }
        }
        path = sp1.generateShortestPath(maze, entry, exit);
        for(int i = 0; i< path.length;i++) {
            Grid.get(path[i].x).get(path[i].y).lightMeUp(4);
        }
        this.setTitle("Showing shortest path");
        this.setVisible(true);
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);;
        this.addKeyListener((this));
        JOptionPane.showMessageDialog(null, "Press any key to continue", "Show Shortest Path", JOptionPane.INFORMATION_MESSAGE);

    }
    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() != 0){
            this.dispose();

            Window gameWindow = new Window(main.maze.getMaze());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
