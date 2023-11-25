import org.junit.Test;
import static org.junit.Assert.*;

public class WinCheckTest {

    @Test
    public void testConstructor(){
        WinCheck winCheck = new WinCheck(); //Target function
        assertFalse(winCheck.tomWin);
        assertFalse(winCheck.jerryWin);

        winCheck.tomWin = true;
    }

    @Test
    public void testTomWinning() {
        WinCheck winCheck = new WinCheck();
        Position dummyTomPosition = new Position(1,1);
        Position dummyJerryPosition = new Position(1,1);
        Position dummyExitPosition = new Position(10,10);

        winCheck.checkWinning(dummyTomPosition, dummyJerryPosition, dummyExitPosition); //Target function

        assertTrue(WinCheck.tomWin);
        assertFalse(WinCheck.jerryWin);
    }

    @Test
    public void testJerryWin() {
        WinCheck winCheck = new WinCheck();
        Position dummyTomPosition = new Position(1,1);
        Position dummyJerryPosition = new Position(10,10);
        Position dummyExitPosition = new Position(10,10);

        winCheck.checkWinning(dummyTomPosition, dummyJerryPosition, dummyExitPosition); //Target function

        assertFalse(WinCheck.tomWin);
        assertTrue(WinCheck.jerryWin);
    }


}