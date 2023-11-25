import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

class ShortestPathTest {
    @Test
    void generateShortestPath() {
        // testing if it can return the shortest path, covering line 18-73
        int[][] tnjMaze = ReadCSV.readCSV("C:\\Users\\asus\\Desktop\\Academic\\UST\\academic\\Year 3\\COMP3111\\Lab\\Comp3111F23G59\\MazaMap_TnJ.csv");
        int[][] expected1 = new int[][] {
                {12,0}, {12,1}, {12,2}, {13,2}, {13,3}, {13,4}, {14,4}, {14,5}, {14,6}, {14,7},
                {14,8}, {13,8}, {12,8}, {12,9}, {12,10}, {11,10}, {10,10}, {10,11}, {10,12}, {9,12},
                {9,13}, {8,13}, {7,13}, {7,14}, {7,15}, {7,16}, {7,17}, {6,17}, {5,17}, {5,18},
                {4,18}, {4,19}, {4,20}, {4,21}, {5,21}, {6,21}, {6,22}, {6,23}, {6,24}, {5,24},
                {5,25}, {5,26}, {4,26}, {3,26}, {2,26}, {1,26}, {1,27}, {1,28}, {1,29}
        };

        Position[] output1 = new ShortestPath().generateShortestPath(tnjMaze, new Position(12,0), new Position(1,29)); // target function
        int[][] actual1 = new int[output1.length][2];
        for (int i = 0; i < output1.length; i++){
            actual1[i][0] = output1[i].x;
            actual1[i][1] = output1[i].y;
        }
        assertArrayEquals(actual1, expected1);

        // testing if there is no shortest path from the starting cell to the end cell, it will return [-1, -1], covers line 18-47, 58-74
        int[][] expected2 = new int[][] {{-1,-1}};

        Position[] output2 = new ShortestPath().generateShortestPath(tnjMaze, new Position(12,0), new Position(0,29)); // target function
        int[][] actual2 = new int[output2.length][2];
        for (int i = 0; i < output2.length; i++){
            actual2[i][0] = output2[i].x;
            actual2[i][1] = output2[i].y;
        }
        assertArrayEquals(actual2, expected2);

    }

}