import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SelectionFrame extends JFrame implements ActionListener {
    JButton manualCreate;
    JButton autoCreate;
    JButton exitGame;

    SelectionFrame(){
        ImageIcon image = new ImageIcon("Game-logo.png");
        ImageIcon maze = new ImageIcon("Maze.png");
        //Jlabel
        JLabel label = new JLabel();
        label.setText("Create Maze: Manual or Automatic");
        label.setIcon(maze);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x0000FF));
        label.setFont(new Font("Mv Boli", Font.PLAIN, 20));
        label.setIconTextGap(10);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(50, 25, 500, 300);

        manualCreate = new JButton();
        manualCreate.setBounds(50, 350, 100, 50);
        manualCreate.addActionListener(this);
        manualCreate.setText("Manual Create");
        manualCreate.setFocusable(false);
        manualCreate.setFont(new Font("Comic Sans", Font.BOLD, 10));
        manualCreate.setBorder(BorderFactory.createEtchedBorder());

        autoCreate = new JButton();
        autoCreate.setBounds(250, 350, 100, 50);
        autoCreate.addActionListener(this);
        autoCreate.setText("Auto Create");
        autoCreate.setFocusable(false);
        autoCreate.setFont(new Font("Comic Sans", Font.BOLD, 10));
        autoCreate.setBorder(BorderFactory.createEtchedBorder());

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
        this.add(manualCreate);
        this.add(autoCreate);
        this.add(exitGame);

        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0xEEEEEE));
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == manualCreate){
            this.dispose();
            JFrame manualCreateFrame = new JFrame();
            manualCreateFrame.setVisible(true);
        }
        else if(e.getSource() == autoCreate){
            this.dispose();
            JFrame autoCreateFrame = new JFrame();
            autoCreateFrame.setVisible(true);
        } else if (e.getSource() == exitGame) {
            this.dispose();
            Window testFrame = new Window(main.maze.getMaze());
        }

    }

}
