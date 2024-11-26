import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WatermelonTest {

    Watermelon w1 = new Watermelon(400.00, 50.00, 5);
    Watermelon w2 = new Watermelon(410.00, 100.00, 5);
    Watermelon w3 = new Watermelon(420.00, 200.00, 5);
    Watermelon w4 = new Watermelon(500.00, Watermelon.getMaxy() - 10, 5);  // Near maxY
    Watermelon w5 = new Watermelon(600.00, Watermelon.getMaxy() - 1, 5);  // Near maxY
    Watermelon w6 = new Watermelon(700.00, 300.00, 5);

    @Test
    public void testUpdate() {  
        assertTrue(w1.equals(w1.update()));
        assertTrue(w2.equals(w2.update()));
        assertTrue(w3.equals(w3.update()));
    }

    @Test
    public void testIsOffScreenTrue() {
        w4.setY(Watermelon.getMaxy());
        assertTrue(w4.IsOffScreen());

        w5.setY(Watermelon.getMaxy());
        assertTrue(w5.IsOffScreen());
    }

    @Test
    public void testIsOffScreenFalse() {
        assertFalse(w1.IsOffScreen());
        assertFalse(w2.IsOffScreen());
        assertFalse(w6.IsOffScreen());
    }
}


