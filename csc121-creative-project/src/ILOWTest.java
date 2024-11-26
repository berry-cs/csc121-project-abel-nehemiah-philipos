import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ILOWTest {

    HungrySnake snake = new HungrySnake(100.0);

    Watermelon wm1 = new Watermelon(50.0, 50.0, 5);  // On-screen, not eaten
    Watermelon wm2 = new Watermelon(100.0, 100.0, 10); // On-screen, eaten by snake
    Watermelon wm3 = new Watermelon(50.0, 600.0, 15); // Off-screen
    Watermelon wm4 = new Watermelon(200.0, 50.0, 5); // On-screen, not eaten
    Watermelon wm5 = new Watermelon(100.0, 20.0, 10); // On-screen, eaten by snake

    ILOW empty = new MTLOW();
    ILOW single = new ConsLOW(wm1, empty);
    ILOW multiple = new ConsLOW(wm1, new ConsLOW(wm2, new ConsLOW(wm3, new ConsLOW(wm4, empty))));

       @Test
    public void testUpdate() {
        ILOW updated = multiple.update();
        
        assertEquals(new Watermelon(50.0, 55.0, 5 + Watermelon.getSpeedincrement()),
                ((ConsLOW) updated).getFirst());
        assertEquals(new Watermelon(100.0, 110.0, 10 + Watermelon.getSpeedincrement()),
                ((ConsLOW) ((ConsLOW) updated).getRest()).getFirst());
    }

    @Test
    public void testAddWatermelon() {
        assertEquals(new ConsLOW(wm1, empty), empty.addWatermelon(wm1));
        assertEquals(new ConsLOW(wm4, multiple), multiple.addWatermelon(wm4));
    }

    @Test
    public void testRemoveOffScreen() {
        assertEquals(new ConsLOW(wm1, new ConsLOW(wm2, new ConsLOW(wm4, empty))), multiple.removeOffScreen());
    }

    @Test
    public void testCountEaten() {
        assertEquals(0, multiple.countEaten(snake));
    }

    @Test
    public void testCountOffScreen() {
        assertEquals(1, multiple.countOffScreen());
    }
}

