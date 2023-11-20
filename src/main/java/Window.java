import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Window extends  JFrame{
    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    public static int width = 30;
    public static int height = 30;
    Position entry;
    Position exit;

    public Window(int[][] maze){


        Grid = new ArrayList<ArrayList<DataOfSquare>>();
        ArrayList<DataOfSquare> data;
        for(int i = 0; i<height; i++){
            if(maze[i][0] == 0){
                maze[i][0] = 2;
                entry = new Position(i, 0);
                break;
            }
        }
        for(int i = 0; i < height; i++){
            if(maze[i][29] == 0){
                maze[i][29] = 3;
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
        this.setTitle("Tom-and-Jerry: Hide and Seek");
        this.setVisible(true);
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        JerryController c = new JerryController(entry, exit);
        TomController j = new TomController(exit, entry);

        c.start();
        j.start();


        this.addKeyListener((KeyListener) new KeyBoardListener());
    }
}
