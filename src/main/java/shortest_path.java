import java.util.*;
public class shortest_path { // initialize a new object of shortest_path, then call with generateShortestPath operation
    int INF = Integer.MAX_VALUE;
    int ROW, COL;
    int ROWNUM[] = {-1, 0, 0, 1}; // this is for find 4 directions
    int COLNUM[] = {0, -1, 1, 0};

    private Position[] PATHSEQUENCE; // this store the path sequence by calling appendArray();

    public Position[] generateShortestPath(int[][] Matrix, Position start, Position end) { // generate the sequence of shortest path
        ROW = Matrix.length; // initializing the maze's row and columns
        COL = Matrix[0].length;

        int[][] dist = new int [ROW][COL]; // initializing a 2D integer matrix to keep track of the dist of current node to the source
        Position[][] parents = new Position[ROW][COL];   // this is for each cell's corresponding parents
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

        // create a priority queue for storing cells to visit
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[2]-b[2]); // let the heap sort according to index 2
        pq.offer(new int[]{start.x, start.y, 0}); // add to the heap


        while(!pq.isEmpty()) { // iterate while there are matrix blocks to visit
            int[] curr = pq.poll(); // pop the head of heap
            int x = curr[0];
            int y = curr[1];
            int d = curr[2];

            // if the cell has already been visited, skip it
            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true; // mark the current cell as visited

            if (x == end.x && y == end.y) { // if we reach the end
                PATHSEQUENCE = new Position[d+1];
                for(int i = 0; i < d+1; i++){
                    PATHSEQUENCE[i] = new Position(-1,-1);

                }
                appendPath(end.x, end.y, parents, d); // call appendPath to append the sequence to PATHSEQUENCE
                return PATHSEQUENCE; // return the sequence
            }

            // Exploring the possibilities
            for (int i = 0; i < 4; i++) {
                int nx = x + ROWNUM[i];
                int ny = y + COLNUM[i];
                //Matrix[nx][ny] is the next I am going to access
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

    void appendPath(int x, int y, Position[][] parents, int counter){
        if(parents[x][y].x == -1 && parents[x][y].y== -1){
            PATHSEQUENCE[counter].x=x; // for the first cell
            PATHSEQUENCE[counter].y=y;
            return;
        }
        appendPath(parents[x][y].x, parents[x][y].y, parents, counter -1); // call from the last cell, so reverse the order of appending
        PATHSEQUENCE[counter].x=x;
        PATHSEQUENCE[counter].y=y;



    }
}
