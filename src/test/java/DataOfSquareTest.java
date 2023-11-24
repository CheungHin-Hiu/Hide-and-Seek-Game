import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.ArrayList;


public class DataOfSquareTest {

    @Test
    void testConstructor(){
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
        colors.add(new Color(0xFFA500));
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        int testColor = 0;
        DataOfSquare dataOfSquare = new DataOfSquare(testColor);    //Target function
        assertEquals(colors.get(testColor), dataOfSquare.grid.getBackground());
    }

    @Test
    void testLightMeUp() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
        colors.add(new Color(0xFFA500));
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);

        int testColor = 0;
        int targetColor = 3;

        DataOfSquare dataOfSquare = new DataOfSquare(testColor);
        dataOfSquare.lightMeUp(targetColor);                        //Target function

        assertEquals(colors.get(targetColor), dataOfSquare.grid.getBackground());
    }
}