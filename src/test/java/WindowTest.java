import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import java.awt.GridLayout;

public class WindowTest {

    @Test
    public void testGuiSetup() {
        MazeGenerator testGUIMaze = new MazeGenerator(30);
        testGUIMaze.generateMaze();
        testGUIMaze.addPaths(30);
        int[][] maze = testGUIMaze.getMaze();
        Window window = new Window(maze);       //Test function

        // Test layout
        assertTrue(window.getContentPane().getLayout() instanceof GridLayout);
        GridLayout layout = (GridLayout) window.getContentPane().getLayout();
        assertEquals(30, layout.getRows());
        assertEquals(30, layout.getColumns());
        assertEquals(0, layout.getHgap());
        assertEquals(0, layout.getVgap());

        // Test window title
        assertEquals("Tom-and-Jerry: Hide and Seek", window.getTitle());

        // Test visibility
        assertTrue(window.isVisible());

        // Test size
        assertEquals(600, window.getWidth());
        assertEquals(600, window.getHeight());

        // Test resizable
        assertFalse(window.isResizable());

        // Test default close operation
        assertEquals(JFrame.EXIT_ON_CLOSE, window.getDefaultCloseOperation());

        // Test key listener
        assertNotNull(window.getKeyListeners());
        assertEquals(1, window.getKeyListeners().length);
        assertTrue(window.getKeyListeners()[0] instanceof KeyBoardListener);
    }

    // Add more test cases as needed
}