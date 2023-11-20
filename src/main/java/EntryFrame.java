import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryFrame extends JFrame implements ActionListener {

    JButton startButton;
    JButton endButton;
    EntryFrame() {
        ImageIcon image = new ImageIcon("Game-logo.png");

        //Jlabel
        JLabel label = new JLabel();
        label.setText("Welcome to T&J: Hide and Seek");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x0000FF));
        label.setFont(new Font("Mv Boli", Font.PLAIN, 20));
        label.setIconTextGap(10);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(115, 25, 350, 300);

        //StartButton
        startButton = new JButton();
        startButton.setBounds(235, 325, 100, 50);
        startButton.addActionListener(this);
        startButton.setText("Start Game!");
        startButton.setFocusable(false);
        startButton.setFont(new Font("Comic Sans", Font.BOLD, 10));
        startButton.setBorder(BorderFactory.createEtchedBorder());


        //End Button

        endButton = new JButton();
        endButton.setBounds(235, 400, 100, 50);
        endButton.addActionListener(this);
        endButton.setText("Exit Game!");
        endButton.setFocusable(false);
        endButton.setFont(new Font("Comic Sans", Font.BOLD, 10));
        endButton.setBorder(BorderFactory.createEtchedBorder());



        //General Frame
        this.setTitle("Tom-and-Jerry: Hide and Seek");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.add(label);
        this.add(startButton);
        this.add(endButton);

        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0xEEEEEE));
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == startButton) {
            this.dispose();
            SelectionFrame f1 = new SelectionFrame();
            f1.setVisible(true);
        }
        else if (e.getSource() == endButton) {
            this.dispose();

        }
    }
}
