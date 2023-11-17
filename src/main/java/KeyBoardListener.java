import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoardListener extends KeyAdapter{

    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case 37: 	// -> Left
                JerryController.JerryDirection=1;
                break;
            case 38:    // -> Up
                JerryController.JerryDirection=2;
                break;
            case 39:    // -> right
                JerryController.JerryDirection=3;
                break;
            case 40:    // -> down
                JerryController.JerryDirection=4;
                break;

            default: 	break;
        }
    }

}
