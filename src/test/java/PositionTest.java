import org.junit.jupiter.api.Test;

import javax.management.openmbean.CompositeDataInvocationHandler;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void positionConstructorTest(){
        Position testPosition = new Position(1,1);      //Test target
        assertEquals(testPosition.x, 1);
        assertEquals(testPosition.y, 1);
    }

    void copyConstructorTest(){
        Position targetPosition = new Position(12, 25);
        Position copyPosition = new Position(targetPosition);   //Test target
        assertEquals(targetPosition.x, copyPosition.x);
        assertEquals(targetPosition.y, copyPosition.y);
    }
    @Test
    void changePositionTest() {
        Position testPosition = new Position(10,20);
        Position targetPosition = new Position(5,12);
        testPosition.changePosition(5,12);              //Test target
        assertEquals(testPosition.x, targetPosition.x);
        assertEquals(testPosition.y, targetPosition.y);
    }


}