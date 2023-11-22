import java.util.*;

public class MazeGenerator {
    private int[][] maze;
    private int dimension;
    private List<Wall> walls;
    private Random rand = new Random();
    private Position start;
    private Position end;

    // Represents a wall between cells, with direction to the neighboring cell
    private class Wall {
        int x, y; // Coordinates of the wall (x for column, y for row)
        int dx, dy; // Direction from the wall to the cell it might connect to

        Wall(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }

        // Checks if the wall leads to a valid unvisited cell (not including maze boarder)
        boolean isValid() {
            int nx = x + dx;
            int ny = y + dy;
            return nx > 0 && nx < dimension - 1 && ny > 0 && ny < dimension - 1 && maze[ny][nx] == 1;
        }
    }

    public MazeGenerator(int dim) {
        dimension = dim;
        maze = new int[dim][dim];
        walls = new ArrayList<>();
        // Randomize start and end positions within the maze bounds
        start = new Position(0, rand.nextInt(dim - 2) + 1);
        end = new Position(0, 0);
        initializeMaze();
    }

    // Initializes the maze with walls
    private void initializeMaze() {
        for (int i = 0; i < dimension; i++) {
            Arrays.fill(maze[i], 1); // 1 represents a wall
        }
    }

    // Generates the maze using Prim's algorithm
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

    // Adds walls around a given cell to the list of walls
    private void addWalls(int x, int y) {
        maze[y][x] = 0; // Mark cell as part of the maze

        // all checking do not include maze boarder
        if (x > 0) walls.add(new Wall(x - 1, y, -1, 0));            // Left wall
        if (y > 0) walls.add(new Wall(x, y - 1, 0, -1));            // Top wall
        if (x < dimension - 1) walls.add(new Wall(x + 1, y, 1, 0)); // Right wall
        if (y < dimension - 1) walls.add(new Wall(x, y + 1, 0, 1)); // Bottom wall
    }

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
        end.x = pos.x;                                          //update end point
        end.y = pos.y;
    }

    // Computes the sum of walls surrounding a given cell
    private int getWeightedSurroundingVertexSum(int x, int y) {
        int sum = 0;
        if (x > 0 && maze[y][x - 1] == 1) sum += 3;                 // Check left
        if (x < dimension - 1 && maze[y][x + 1] == 1) sum += 3;     // Check right
        if (y > 0 && maze[y - 1][x] == 1) sum += 2;                 // Check above
        if (y < dimension - 1 && maze[y + 1][x] == 1) sum += 2;     // Check below
        return sum;
    }

    private boolean isStraightWall(int x, int y) {
        int sum = getWeightedSurroundingVertexSum(x, y);
        return maze[y][x] == 1 && (sum == 6 || sum == 4);
    }

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

    public void printBreakableWallMaze() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (maze[y][x] == 1)  // It's a wall
                    System.out.print(isStraightWall(x,y) ? " 2 " : " " + maze[y][x] + " ");
                else
                    System.out.print("   ");
            }
            System.out.println();
        }
    }

    // Prints the raw numerical maze
    public void printRawMaze() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                System.out.print(maze[y][x] + " ");
            }
            System.out.println();
        }
    }

    // Prints a symbolic representation of the maze
    public void printSymbolicMaze() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (x == start.x && y == start.y)
                    System.out.print(" I ");
                else if (x == end.x && y == end.y)
                    System.out.print(" O ");
                else if (maze[y][x] == 1)
                    System.out.print(" * ");
                else
                    System.out.print("   ");
            }
            System.out.println();
        }
    }

    private Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    private Boolean pointNotCorner(Position position, int x, int y) {
        return (x == position.x || y == position.y);
    }

    private Boolean pointNotNode(Position position, int x, int y) {
        return !(x == position.x && y == position.y);
    }

    public int[][] getMaze(){
        return maze;
    }
    public int getDimension(){
        return dimension;
    }

    public void changeMaze(int [][] maze){
        this.maze = maze;
    }
}
