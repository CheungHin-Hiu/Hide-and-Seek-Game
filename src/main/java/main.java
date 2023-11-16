import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class main {
   public static void main(String[] args){
      // this is for reading the csv
      String path = "C:\\Users\\asus\\Desktop\\Academic\\UST\\academic\\Year 3\\COMP3111\\Project code\\out\\production\\Comp3111F23G59\\MazeMap_SPT.csv";
      String line ="";
      String cvsSplitBy = ",";
      List<List<Integer>> records = new ArrayList<>();

      try (BufferedReader br = new BufferedReader(new FileReader(path))) {
         while ((line = br.readLine()) != null) {
            String[] values = line.split(cvsSplitBy);
            List<Integer> intValues = new ArrayList<>();
            for (String str : values) {
               intValues.add(Integer.parseInt(str.trim())); // trim whitespace before parsing
            }
            records.add(intValues);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      // Convert list of lists to 2D array
      int[][] array = new int[records.size()][];
      for (int i = 0; i < array.length; i++) {
         array[i] = records.get(i).stream().mapToInt(j->j).toArray();
      }

       // end of reading csv




      int[][] maze1 = { {1, 0, 1, 1, 1},
              {1, 0, 1, 0, 1},
              {1, 1, 1, 0, 1},
              {0, 0, 0, 0, 1},
              {1, 1, 1, 0, 1}};;
      StringBuilder sb = new StringBuilder();
      for (int[] row : maze1) {
         sb.append(Arrays.toString(row) + "\n");
      }

      System.out.println(sb);
      Node start = new Node(0,0);
      Node end = new Node(4,4);

      // calculate the shortest path in the maze
      shortest_path sp1 = new shortest_path();
      int shortestPath = sp1.generateShortestPath(maze1, start, end);

      System.out.println("Shortest path length: " + shortestPath);

   }
}
