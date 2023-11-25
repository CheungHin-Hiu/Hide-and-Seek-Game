import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public static int[][] readCSV(String path){
        String line ="";
        String cvsSplitBy = ",";
        List<List<Integer>> records = new ArrayList<>();
        try(
                BufferedReader br = new BufferedReader(new FileReader(path))){
            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String[] values = line.split(cvsSplitBy);
                List<Integer> intValues = new ArrayList<>();
                for (String str : values) {
                    intValues.add(Integer.parseInt(str.trim())); // trim whitespace before parsing
                }
                records.add(intValues);

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        // Convert list of lists to 2D array
        int[][] array = new int[records.size()][];
        for (int i = 0; i < array.length; i++) {
            array[i] = records.get(i).stream().mapToInt(j->j).toArray();
        }


        return array;
    }

}
