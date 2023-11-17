import java.awt.Color;
import javax.swing.JPanel;
public class MazeGrid extends JPanel{
    public MazeGrid(Color d){
        this.setBackground(d);
    }

    public void changeGridColor(Color d){
        this.setBackground(d);
        this.repaint();
    }
}
