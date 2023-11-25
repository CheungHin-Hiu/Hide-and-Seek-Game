import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import javax.swing.JPanel;


public class MazeGridTest {

    @Test
    public void testConstructor(){
        Color testColour = Color.RED;
        MazeGrid testGrid = new MazeGrid(testColour);           //Target function
        assertEquals(testColour, testGrid.getBackground());
    }

    //Testing if the ChangeGridColor() function works properly or not
    @Test
    public void testChangeGridColor() {
        Color initialColor = Color.WHITE;
        Color targetColor = Color.RED;
        MazeGrid testGrid = new MazeGrid(initialColor);
        testGrid.changeGridColor(targetColor);                  //Target function

        assertEquals(targetColor, testGrid.getBackground());
    }


}