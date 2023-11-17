import java.util.ArrayList;
import java.awt.Color;
public class DataOfSquare {
    ArrayList<Color> C = new ArrayList<Color>();  //An array list that contains all the color
    int color;  //0 = Clear vertex; 1 = border; 2 = Jerry; 3 = Tom;
    MazeGrid grid;

    public DataOfSquare(int color){
        C.add(Color.white);
        C.add(Color.black);
        C.add(new Color(0xFFA500));
        C.add(Color.blue);

        this.color = color;
        grid = new MazeGrid(C.get(color));
    }

    public void lightMeUp(int c){
        grid.changeGridColor(C.get(c));
    }
}
