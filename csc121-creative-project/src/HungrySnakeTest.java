import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HungrySnakeTest {

	HungrySnake Mamba = new HungrySnake(810.00);
	HungrySnake Anaconda = new HungrySnake(410.00);
	HungrySnake Python = new HungrySnake(-2.00);

	Watermelon w1 = new Watermelon(400.00);
	Watermelon w2 = new Watermelon(400.00);
	Watermelon w3 = new Watermelon(400.00);
	
	Watermelon w4 = new Watermelon(409, 590, .5, 600, 0, 800);
	Watermelon w5 = new Watermelon(450, 595, .5, 600, 0, 800);

	@Test
	public void tests() {
		assertEquals(new HungrySnake(800.00), Mamba.update());
		assertEquals(Anaconda, Anaconda.update());
		assertEquals(new HungrySnake(0.00), Python.update());
	}

	@Test
	public void testAteWatermelon() {
		assertEquals( true, Anaconda.ateWatermelon(w4) );
		assertEquals( false, Anaconda.ateWatermelon(w3) );
		assertEquals( false, Anaconda.ateWatermelon(w5) );
		
	}

}
