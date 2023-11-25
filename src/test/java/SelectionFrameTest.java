import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import static org.junit.jupiter.api.Assertions.*;

class SelectionFrameTest {

    @Test
    public void testSelectionFrame(){
        SelectionFrame selectionFrame = new SelectionFrame();               //Target function

        //Testing the frame title
        assertEquals("Maze Creation", selectionFrame.getTitle());

        //Test the default close operation
        assertEquals(JFrame.EXIT_ON_CLOSE, selectionFrame.getDefaultCloseOperation());

        //Test whether the frame is resizeable
        assertFalse(selectionFrame.isResizable());

        //Test the size of the frame
        assertEquals(600, selectionFrame.getWidth());
        assertEquals(600, selectionFrame.getHeight());

        //Test the background color of the frame
        assertEquals(new Color(0xEEEEEE), selectionFrame.getContentPane().getBackground());

        // Test the JLabel component inside
        JLabel label = (JLabel) selectionFrame.getContentPane().getComponent(0);
        assertEquals("Classic Maze or Random Maze", label.getText());
        assertEquals(Font.PLAIN, label.getFont().getStyle());
        assertEquals(20, label.getFont().getSize());

        // Test the buttons of the frame
        JButton classicMaze = (JButton) selectionFrame.getContentPane().getComponent(1);
        assertEquals("Classic Maze", classicMaze.getText());


        JButton randomMaze = (JButton) selectionFrame.getContentPane().getComponent(2);
        assertEquals("Random Maze", randomMaze.getText());

        JButton exitGame = (JButton) selectionFrame.getContentPane().getComponent(3);
        assertEquals("Exit Game!", exitGame.getText());
        selectionFrame.dispose();
    }
}