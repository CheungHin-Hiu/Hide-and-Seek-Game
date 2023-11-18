import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Arrays;

class MazeGenerator {

    private Stack<Position> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;

    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;
    }

    public void generateMaze() {
        stack.push(new Position(0,0));
        while (!stack.empty()) {
            Position next = stack.pop();
            if (validNextNode(next)) {
                maze[next.y][next.x] = 1;
                ArrayList<Position> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }
    }

    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }


    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(maze[i][j] == 1 ? "*" : " ");
                sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }



    private boolean validNextNode(Position position) {
        int numNeighboringOnes = 0;
        for (int y = position.y-1; y < position.y+2; y++) {
            for (int x = position.x-1; x < position.x+2; x++) {
                if (pointOnGrid(x, y) && pointNotNode(position, x, y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[position.y][position.x] != 1;
    }

    private void randomlyAddNodesToStack(ArrayList<Position> positions) {
        int targetIndex;
        while (!positions.isEmpty()) {
            targetIndex = rand.nextInt(positions.size());
            stack.push(positions.remove(targetIndex));
        }
    }

    private ArrayList<Position> findNeighbors(Position position) {
        ArrayList<Position> neighbors = new ArrayList<>();
        for (int y = position.y-1; y < position.y+2; y++) {
            for (int x = position.x-1; x < position.x+2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(position, x, y)
                        && pointNotNode(position, x, y)) {
                    neighbors.add(new Position(x, y));
                }
            }
        }
        return neighbors;
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