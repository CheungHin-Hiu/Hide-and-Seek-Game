import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;


public class ShortestPathFrameTest {

    @Test
    public void testConstructor() {
        //Create a dummy maze
        MazeGenerator testMaze = new MazeGenerator(30);
        testMaze.generateMaze();
        testMaze.addPaths(30);
        int[][] maze = testMaze.getMaze();

        ShortestPathFrame shortestPathFrame = new ShortestPathFrame(maze);  //Target function

        assertEquals(30, ShortestPathFrame.Grid.size());
        assertEquals(30, ShortestPathFrame.Grid.get(0).size());

        //Check that JFrame property are set correctly
        assertEquals("Showing shortest path", shortestPathFrame.getTitle());
        assertTrue(shortestPathFrame.isVisible());
        assertEquals(600, shortestPathFrame.getWidth());
        assertEquals(600, shortestPathFrame.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, shortestPathFrame.getDefaultCloseOperation());
        assertFalse(shortestPathFrame.isResizable());
        assertNotNull(shortestPathFrame.getKeyListeners());
    }


}