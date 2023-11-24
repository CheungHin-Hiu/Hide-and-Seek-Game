import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class EntryFrameTest {

    @Test
    public void testEntryFrame() {
        EntryFrame entryFrame = new EntryFrame();       //Target function to test

        // Testing the frame title
        assertEquals("Tom-and-Jerry: Hide and Seek", entryFrame.getTitle());

        // Test default close operation
        assertEquals(JFrame.EXIT_ON_CLOSE, entryFrame.getDefaultCloseOperation());

        // Test whether the frame is resizable
        assertFalse(entryFrame.isResizable());


        // Test the size of the frame
        assertEquals(600, entryFrame.getWidth());
        assertEquals(600, entryFrame.getHeight());

        // Test the background color of the frame
        assertEquals(new Color(0xEEEEEE), entryFrame.getContentPane().getBackground());

        // Test the JLabel component inside
        JLabel label = (JLabel) entryFrame.getContentPane().getComponent(0);
        assertEquals("Welcome to T&J: Hide and Seek", label.getText());
        assertEquals(Font.PLAIN, label.getFont().getStyle());
        assertEquals(20, label.getFont().getSize());

        // Test the buttons of the frame
        JButton startButton = (JButton) entryFrame.getContentPane().getComponent(1);
        assertEquals("Start Game!", startButton.getText());


        JButton endButton = (JButton) entryFrame.getContentPane().getComponent(2);
        assertEquals("Exit Game!", endButton.getText());
        entryFrame.dispose();

    }

}