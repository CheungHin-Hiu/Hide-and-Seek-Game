import java.util.*;
public class ShortestPath { // initialize a new object of shortest_path, then call with generateShortestPath operation
    int INF = Integer.MAX_VALUE;
    int ROW, COL;
    final int ROWNUM[] = {-1, 0, 0, 1}; // this is for find 4 directions
    final int COLNUM[] = {0, -1, 1, 0};

    private Position[] pathSequence; // this store the path sequence by calling appendArray();
    private Position[][] parents;
    /**
     * The generateShortestPath method generate the shortest path from the starting cell to the ending cell, use for displaying the shortest path from the entry of the maze to the end of the maze. Also used for Tom to find the shortest path to Jerry
     * @param Matrix The input matrix from manual create or random create
     * @param start The starting cell of the path
     * @param end The ending cell of the path
     * @return The shortest path sequence from starting cell to ending cell
     */
    public Position[] generateShortestPath(int[][] Matrix, Position start, Position end) { // generate the sequence of shortest path
        ROW = Matrix.length; // initializing the maze's row and columns
        COL = Matrix[0].length;

        int[][] dist = new int [ROW][COL]; // initializing a 2D integer matrix to keep track of the dist of current node to the source
        parents = new Position[ROW][COL];   // this is for each cell's corresponding parents
        boolean[][] visited = new boolean[ROW][COL]; // Stores if the cell is visited or not

        for(int i = 0; i < ROW; i++){ // fill the whole array of dist[][] with infinity large
            Arrays.fill(dist[i], INF);
        }
        for(int i = 0; i < ROW; i++){ // fill all the parent node with -1, -1, indicates no parents
            for(int j =0; j < COL;j++){
                parents[i][j] = new Position(-1, -1);
            }
        }

        dist[start.x][start.y] = 0; // initialize the starting cell as length of 0

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[2]-b[2]); // let the heap sort according to index 2
        pq.offer(new int[]{start.x, start.y, 0}); // add to the heap


        while(!pq.isEmpty()) { // iterate while there are matrix blocks to visit
            int[] curr = pq.poll(); // pop the head of heap
            int x = curr[0];
            int y = curr[1];
            int d = curr[2];

            visited[x][y] = true; // mark the current cell as visited

            if (x == end.x && y == end.y) { // if we reach the end
                pathSequence = new Position[d+1];
                for(int i = 0; i < d+1; i++){
                    pathSequence[i] = new Position(-1,-1);

                }
                appendSequence(end.x, end.y, parents, d); // call appendPath to append the sequence to PATHSEQUENCE
                return pathSequence; // return the sequence
            }

            // Exploring the possibilities
            for (int i = 0; i < 4; i++) {
                int nx = x + ROWNUM[i];
                int ny = y + COLNUM[i];
                if (nx >= 0 && nx < ROW && ny >= 0 && ny < COL && (Matrix[nx][ny] == 0 || Matrix[nx][ny] == 2) && !visited[nx][ny]) {
                    int nd = d + 1;
                    if (nd < dist[nx][ny]) {
                        dist[nx][ny] = nd;
                        parents[nx][ny].x = x;
                        parents[nx][ny].y = y;
                        pq.offer(new int[]{nx, ny, nd});
                    }
                }
            }

        }
        return new Position[]{new Position(-1,-1)}; // if the is no possible way to reach the end, we will return -1,-1 in the maze

    }

    /**
     * This recursively called the appendSequence method to reverse from the end of the path to the start, which can store the correct sequence to pathSequence variable
     * @param x The x position of the current node
     * @param y The y position of the current node
     * @param parents The corresponding parent cell of the current cell, pointing to the previous cell
     * @param counter To keep track of the current depth of recursion, use to initialize the array of pathSequence from 0 to initial counter value
     */
    void appendSequence(int x, int y, Position[][] parents, int counter){
        if(parents[x][y].x == -1 && parents[x][y].y== -1){
            pathSequence[counter].x=x; // for the first cell
            pathSequence[counter].y=y;
            return;
        }
        appendSequence(parents[x][y].x, parents[x][y].y, parents, counter -1); // call from the last cell, so reverse the order of appending
        pathSequence[counter].x=x;
        pathSequence[counter].y=y;



    }
}
