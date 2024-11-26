import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HungrySnakeTest {

	HungrySnake Mamba = new HungrySnake(810.00);
	HungrySnake Anaconda = new HungrySnake(410.00);
	HungrySnake Rattle = new HungrySnake(400.00);
	HungrySnake Python = new HungrySnake(-2.00);

	Watermelon w1 = new Watermelon(400.00, 595.00, 0);
	Watermelon w2 = new Watermelon(410, 595, 0);
	Watermelon w3 = new Watermelon(413, 595, 0);
	Watermelon w4 = new Watermelon(425, 595, 0);
	Watermelon w5 = new Watermelon(500, 595, 0);


	@Test
	public void Updatetests() {
		assertEquals(new HungrySnake(800.00), Mamba.update());
		assertEquals(Anaconda, Anaconda.update());
		assertEquals(new HungrySnake(0.00), Python.update());
		assertEquals(Rattle, Rattle.update());
	}
	@Test
	public void testAteWatermelon() {
		assertTrue(Rattle.ateWatermelon(w1));
		assertTrue(Rattle.ateWatermelon(w2));
		assertTrue(Rattle.ateWatermelon(w3));
		assertFalse(Rattle.ateWatermelon(w4));
		assertFalse(Rattle.ateWatermelon(w5));
	}
}

