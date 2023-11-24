import org.junit.Test;
import static org.junit.Assert.*;

public class MazeGeneratorTest {

    @Test
    public void testMazeDimensions() {
        int dimension = 10;
        MazeGenerator generator = new MazeGenerator(dimension);
        assertEquals("The maze should be of the given dimension.", dimension, generator.getDimension());
    }

    @Test
    public void testMazeInitialization() {
        int dimension = 10;
        MazeGenerator generator = new MazeGenerator(dimension);
        int[][] maze = generator.getMaze();
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                assertEquals("Maze should be initialized with walls (1).", 1, maze[y][x]);
            }
        }
    }

    @Test
    public void testMazePathCreation() {
        int dimension = 10;
        MazeGenerator generator = new MazeGenerator(dimension);
        generator.generateMaze();
        int[][] maze = generator.getMaze();

        boolean pathExists = false;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (maze[y][x] == 0) {
                    pathExists = true;
                    break;
                }
            }
            if (pathExists) break;
        }

        assertTrue("Paths (0) should exist after maze generation.", pathExists);
    }

    @Test
    public void testAddPaths() {
        int dimension = 10;
        int numWallsToBreak = 5;
        MazeGenerator generator = new MazeGenerator(dimension);
        generator.generateMaze();
        generator.addPaths(numWallsToBreak);

        int[][] maze = generator.getMaze();
        int pathCount = 0;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (maze[y][x] == 0) pathCount++;
            }
        }

        assertTrue("The number of paths should be increased after adding paths.", pathCount > numWallsToBreak);
    }
}
