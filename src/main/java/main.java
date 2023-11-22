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
    static int [][] array;
    static MazeGenerator maze;
    public static void main(String[] args) {
//        String path = "C:\\Users\\asus\\Desktop\\Academic\\UST\\academic\\Year 3\\COMP3111\\Lab\\Comp3111F23G59\\MazaMap_TnJ.csv";
//        String line ="";
//        String cvsSplitBy = ",";
//        List<List<Integer>> records = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(cvsSplitBy);
//                List<Integer> intValues = new ArrayList<>();
//                for (String str : values) {
//                    intValues.add(Integer.parseInt(str.trim())); // trim whitespace before parsing
//                }
//                records.add(intValues);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Convert list of lists to 2D array
//        array = new int[records.size()][];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = records.get(i).stream().mapToInt(j->j).toArray();
//        }
//        maze = new MazeGenerator(30);
//        maze.changeMaze(array);
//
//        //Create the starting menu
//        EntryFrame firstFrame = new EntryFrame();
        MazeGenerator generator = new MazeGenerator(30); // Example size and positions
        generator.generateMaze();
        System.out.println("Perfect maze: ");
        generator.printSymbolicMaze();
        System.out.println();
        System.out.println("Paths added maze: ");
        generator.addPaths(30);
        generator.printSymbolicMaze();
    }
}

