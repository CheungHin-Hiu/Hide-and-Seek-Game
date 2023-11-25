import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBoardListenerTest {

    @Test
    public void testKeyPressedLeft() {
        JerryController controller = new JerryController(new Position(1,2), new Position(2,1));
        KeyBoardListener listener = new KeyBoardListener();
        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        controller.start();
        assertEquals(1, JerryController.JerryDirection);
    }
    @Test
    public void testKeyPressedRight() {
        JerryController controller = new JerryController(new Position(1,2), new Position(2,1));
        KeyBoardListener listener = new KeyBoardListener();
        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        controller.start();
        assertEquals(3, JerryController.JerryDirection);
    }
    @Test
    public void testKeyPressedUp() {
        JerryController controller = new JerryController(new Position(1,2), new Position(2,1));
        KeyBoardListener listener = new KeyBoardListener();
        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        controller.start();
        assertEquals(2, JerryController.JerryDirection);
    }
    @Test
    public void testKeyPressedLeftWithJerryController() {
        JerryController controller = new JerryController(new Position(1,2), new Position(10, 20));
        KeyBoardListener listener = new KeyBoardListener();
        KeyEvent event = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        controller.start();
        assertEquals(4, JerryController.JerryDirection);
    }


}