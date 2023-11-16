import java.util.*;
public class shortest_path {
    int INF = Integer.MAX_VALUE;
    Node NO_PARENT = new Node(-1,-1);
    int ROW, COL;
    int ROWNUM[] = {-1, 0, 0, 1};
    int COLNUM[] = {0, -1, 1, 0};

    private class queueNode{
        Node node;
        int dist;

        queueNode(Node node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }
    private class Path {
        public String name;
        public int[][] path;

        Path(String name, int[][] path){
            this.name = name;
            this.path = path;
        }
    }

    // These arrays are used to get row and column
    // numbers of 4 neighbours of a given cell


    public int generateShortestPath(int[][] Matrix, Node start, Node end) { // change to returning the path later
        ROW = Matrix.length;
        COL = Matrix[0].length;

        int[][] dist = new int [ROW][COL];
//        Node[][] parents = new Node [ROW][COL];
        boolean[][] visited = new boolean[ROW][COL];

        for(int i = 0; i < ROW; i++){
            Arrays.fill(dist[i], INF);
        }
//        for(int i = 0; i < ROW; i++){
//            Arrays.fill(parents[i], NO_PARENT);
//        }

        dist[start.x][start.y] = 0; // initialize the starting cell as length of 0

        // create a priority queue for storing cells to visit
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[2]-b[2]);
        pq.offer(new int[]{start.x, start.y, 0}); // add to the heap

        // iterate while there are matrix blocks to visit
        while(pq.isEmpty() == false){
            int[] curr = pq.poll(); // pop the head of heap
            int x = curr[0];
            int y = curr[1];
            int d = curr[2];

            // if the cell has already been visited, skip it
            if(visited[x][y]){
                continue;
            }

            visited[x][y] = true;

            if(x == end.x && y == end.y){
                return d;
            }

            // Exploring the possibilities
            for(int i = 0; i < 4; i++){
                int nx = x + ROWNUM[i];
                int ny = y + COLNUM[i];
                //Matrix[nx][ny] is the next I am going to access
                if(nx >= 0  &&  nx < ROW && ny>=0 && ny < COL && Matrix[nx][ny] == 1 && visited[nx][ny] == false){
                    int nd = d + 1;
                    if(nd < dist[nx][ny]){
                        dist[nx][ny] = nd;
//                        parents[nx][ny].x = x;
//                        parents[nx][ny].y = y;


                        pq.offer(new int[]{nx, ny, nd});
                    }
                }
            }
//            printPath(end, parents);

        }
        return -1;

    }

    void printPath(Node currNode, Node [][] parents){ // put the end node in the first parameter
        if(parents[currNode.x][currNode.y].x== NO_PARENT.x && parents[currNode.x][currNode.y].y== NO_PARENT.y){
            return;
        }
        printPath(parents[currNode.x][currNode.y], parents);
        System.out.printf("[%d,%d] ", currNode.x, currNode.y);
    }



}
