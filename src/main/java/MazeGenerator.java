import java.util.*;

/**
 * Generates a maze using a modified version of Prim's algorithm.
 * The maze is represented as a 2D integer array where the value 0 indicates a path,
 * and the value 1 indicates a wall. Entry and exit points can be specified.
 */

public class MazeGenerator {
    public int[][] maze;
    private int dimension;
    private List<Wall> walls;
    private Random rand = new Random();
    private Position start;
    private Position end;

    /**
     * Represents a wall between cells with a direction to the neighboring cell.
     */
    private class Wall {
        int x, y; // Coordinates of the wall (x for column, y for row)
        int dx, dy; // Direction from the wall to the cell it might connect to

        /**
         * Constructs a new wall with a specified direction.
         *
         * @param x  X-coordinate of the wall.
         * @param y  Y-coordinate of the wall.
         * @param dx Delta X, representing the horizontal direction to the neighboring cell.
         * @param dy Delta Y, representing the vertical direction to the neighboring cell.
         */
        Wall(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }

        /**
         * Checks if this wall leads to a valid, unvisited cell within the maze boundaries.
         *
         * @return true if the wall is valid, false otherwise.
         */
        boolean isValid() {
            int nx = x + dx;
            int ny = y + dy;
            return nx > 0 && nx < dimension - 1 && ny > 0 && ny < dimension - 1 && maze[ny][nx] == 1;
        }
    }

    /**
     * Initializes the maze generator with a specific dimension.
     *
     * @param dim The dimension for both width and height of the maze.
     */
    public MazeGenerator(int dim) {
        dimension = dim;
        maze = new int[dim][dim];
        walls = new ArrayList<>();
        // Randomize start position on the left side of the maze
        // End position first holds dummy value and update in function updateEndPoint()
        start = new Position(0, rand.nextInt(dim - 2) + 1);
        end = new Position(0, 0);
        initializeMaze();
    }

    /**
     * Fills the maze with walls (value 1).
     */
    private void initializeMaze() {
        for (int i = 0; i < dimension; i++) {
            Arrays.fill(maze[i], 1); // 1 represents a wall
        }
    }

    /**
     * Generates the maze using the Prim's algorithm.
     */
    public void generateMaze() {
        addWalls(start.x, start.y);

        while (!walls.isEmpty()) {
            Wall wall = walls.remove(rand.nextInt(walls.size()));
            if (wall.isValid()) {
                maze[wall.y][wall.x] = 0; // 0 represents a path
                addWalls(wall.x + wall.dx, wall.y + wall.dy);
            }
        }
        updateEndPoint();
    }

    /**
     * Adds walls to the list of potential walls to be converted to paths.
     *
     * @param x X-coordinate of the cell.
     * @param y Y-coordinate of the cell.
     */
    private void addWalls(int x, int y) {
        maze[y][x] = 0; // Mark cell as part of the maze

        // all checking do not include maze boarder
        if (x > 0) walls.add(new Wall(x - 1, y, -1, 0));            // Left wall
        if (y > 0) walls.add(new Wall(x, y - 1, 0, -1));            // Top wall
        if (x < dimension - 1) walls.add(new Wall(x + 1, y, 1, 0)); // Right wall
        if (y < dimension - 1) walls.add(new Wall(x, y + 1, 0, 1)); // Bottom wall
    }

    /**
     * Randomly selects end points for the maze based on the configuration of the last row.
     */
    private void updateEndPoint(){
        List<Position> possibleEndPointPositions = new ArrayList<>();
        for (int y = 1; y < dimension - 1; y++){
            if (isStraightWall(dimension - 1, y)){
                possibleEndPointPositions.add(new Position(dimension - 1, y));
            }
        }
        Collections.shuffle(possibleEndPointPositions);         // Randomize the list
        Position pos = possibleEndPointPositions.get(0);
        maze[pos.y][pos.x] = 0;                                 // Turn the wall into a path
        end.x = pos.x;                                          // Update end point
        end.y = pos.y;
    }

    /**
     * Calculates the weighted sum of walls surrounding a given cell.
     * Horizontal wall is weighted for 3.
     * Vertical wall is weighted for 2.
     *
     * @param x X-coordinate of the cell.
     * @param y Y-coordinate of the cell.
     * @return The weighted sum of walls surrounding the cell.
     */
    private int getWeightedSurroundingVertexSum(int x, int y) {
        int sum = 0;
        if (x > 0 && maze[y][x - 1] == 1) sum += 3;                 // Check left
        if (x < dimension - 1 && maze[y][x + 1] == 1) sum += 3;     // Check right
        if (y > 0 && maze[y - 1][x] == 1) sum += 2;                 // Check above
        if (y < dimension - 1 && maze[y + 1][x] == 1) sum += 2;     // Check below
        return sum;
    }

    /**
     * Checks if a given cell is a straight wall based on the surrounding walls.
     * For non-conjunction wall the weighted sum will be either 6 or 4.
     *
     * @param x X-coordinate of the cell.
     * @param y Y-coordinate of the cell.
     * @return true if the cell is a straight wall, false otherwise.
     */
    private boolean isStraightWall(int x, int y) {
        int sum = getWeightedSurroundingVertexSum(x, y);
        return maze[y][x] == 1 && (sum == 6 || sum == 4);
    }

    /**
     * Adds additional paths to the maze by randomly breaking straight walls.
     *
     * @param numWallsToBreak The number of additional walls to convert into paths.
     */
    public void addPaths(int numWallsToBreak) {
        List<Position> breakableWallPositions = new ArrayList<>();
        for (int y = 1; y < dimension - 1; y++) {
            for (int x = 1; x < dimension - 1; x++) {
                if (isStraightWall(x, y)) breakableWallPositions.add(new Position(x, y));
            }
        }

        Collections.shuffle(breakableWallPositions); // Randomize the list of breakable wall positions
        for (int i = 0; i < Math.min(numWallsToBreak, breakableWallPositions.size()); i++) {
            Position pos = breakableWallPositions.get(i);
            maze[pos.y][pos.x] = 0; // Turn the wall into a path
        }
    }

    /**
     * Retrieves the maze grid as a 2D array of integers.
     *
     * @return The maze grid.
     */
    public int[][] getMaze(){
        return maze;
    }
    /**
     * Retrieves the dimension of the maze.
     *
     * @return The dimension of the maze.
     */
    public int getDimension(){
        return dimension;
    }

    /**
     * Replaces the current maze with a new maze configuration.
     *
     * @param maze The new maze grid to set.
     */
    public void changeMaze(int [][] maze){
        this.maze = maze;
    }
}
