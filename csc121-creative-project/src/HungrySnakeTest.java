import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WorldTest {

HungrySnake Mamba = new HungrySnake(810.00);
HungrySnake Anaconda = new HungrySnake(410.00);
HungrySnake Python = new HungrySnake(-2.00);

Watermelon w1 = new Watermelon(400.00);
Watermelon w2 = new Watermelon(400.00);
Watermelon w3 = new Watermelon(400.00);
    
    @Test
    void tests() {
        assertEquals(new HungrySnake(800.00), Mamba.update());
        assertEquals(Anaconda, Anaconda.update());
        assertEquals(new HungrySnake(0.00), Python.update());
    }

}
