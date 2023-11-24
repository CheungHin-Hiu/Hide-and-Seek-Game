import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectionFrame extends JFrame implements ActionListener {
    JButton classicMaze;
    JButton randomMaze;
    JButton exitGame;

    SelectionFrame(){
        ImageIcon image = new ImageIcon("Game-logo.png");
        ImageIcon maze = new ImageIcon("Maze.png");
        //Jlabel
        JLabel label = new JLabel();
        label.setText("Classic Maze or Random Maze");
        label.setIcon(maze);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x0000FF));
        label.setFont(new Font("Mv Boli", Font.PLAIN, 20));
        label.setIconTextGap(10);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(50, 25, 500, 300);

        classicMaze = new JButton();
        classicMaze.setBounds(50, 350, 100, 50);
        classicMaze.addActionListener(this);
        classicMaze.setText("Classic Maze");
        classicMaze.setFocusable(false);
        classicMaze.setFont(new Font("Comic Sans", Font.BOLD, 10));
        classicMaze.setBorder(BorderFactory.createEtchedBorder());

        randomMaze = new JButton();
        randomMaze.setBounds(250, 350, 100, 50);
        randomMaze.addActionListener(this);
        randomMaze.setText("Random Maze");
        randomMaze.setFocusable(false);
        randomMaze.setFont(new Font("Comic Sans", Font.BOLD, 10));
        randomMaze.setBorder(BorderFactory.createEtchedBorder());

        exitGame = new JButton();
        exitGame.setBounds(450, 350, 100, 50);
        exitGame.addActionListener(this);
        exitGame.setText("Exit Game!");
        exitGame.setFocusable(false);
        exitGame.setFont(new Font("Comic Sans", Font.BOLD, 10));
        exitGame.setBorder(BorderFactory.createEtchedBorder());

        this.setTitle("Maze Creation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.add(label);
        this.add(classicMaze);
        this.add(randomMaze);
        this.add(exitGame);

        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0xEEEEEE));
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == classicMaze){
            this.dispose();
            String path = "MazaMap_TnJ.csv";
            String line ="";
            String cvsSplitBy = ",";
            List<List<Integer>> records = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(cvsSplitBy);
                    List<Integer> intValues = new ArrayList<>();
                    for (String str : values) {
                        intValues.add(Integer.parseInt(str.trim())); // trim whitespace before parsing
                    }
                    records.add(intValues);
                }
            } catch (IOException et) {
                et.printStackTrace();
            }

            // Convert list of lists to 2D array
            main.array = new int[records.size()][];
            for (int i = 0; i < main.array.length; i++) {
                main.array[i] = records.get(i).stream().mapToInt(j->j).toArray();
            }
            main.gameMaze = new MazeGenerator(30);
            main.gameMaze.changeMaze(main.array);

            ShortestPathFrame testFrame = new ShortestPathFrame(main.gameMaze.getMaze());
        }
        else if(e.getSource() == randomMaze){
            this.dispose();
            MazeGenerator generator = new MazeGenerator(30); // Example size and positions
            generator.generateMaze();
            generator.addPaths(30);
            main.gameMaze.changeMaze(generator.getMaze());
            ShortestPathFrame autoMazePathFrame = new ShortestPathFrame(main.gameMaze.getMaze());

        } else if (e.getSource() == exitGame) {
            this.dispose();

        }

    }

}
