import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

class HungrySnakeWorldTest {

	ILOW mt = new MTLOW(); 
	HungrySnakeWorld w1 = new HungrySnakeWorld(new HungrySnake(400.0), mt, 10, 0, 3);
	HungrySnakeWorld w2 = new HungrySnakeWorld(new HungrySnake(500.0), mt, 5, 0, 3);
	HungrySnakeWorld w3 = new HungrySnakeWorld(new HungrySnake(600.0), 
			new ConsLOW(new Watermelon(400.0, 500.0, 5), mt), 0, 0, 3);

	@Test
	void testUpdate() {
		HungrySnakeWorld expectedW1 = new HungrySnakeWorld(new HungrySnake(400.0), mt, 9, 0, 3);
		assertTrue(expectedW1.equals(w1.update()));

		HungrySnakeWorld expectedW2 = new HungrySnakeWorld(new HungrySnake(500.0), mt, 4, 0, 3);
		assertTrue(expectedW2.equals(w2.update()));


	}

	@Test
	void testMouseMoved() {

		HungrySnakeWorld movedW1 = new HungrySnakeWorld(new HungrySnake(410.0), mt, 10, 0, 3);
		MouseEvent mev = new MouseEvent(null, 1, 0, 0, 410, 0, 0, 0);
		assertEquals(movedW1, w1.mouseMoved(mev));

		HungrySnakeWorld movedW2 = new HungrySnakeWorld(new HungrySnake(300.0), mt, 5, 0, 3);
		MouseEvent mev2 = new MouseEvent(null, 1, 0, 0, 300, 0, 0, 0);
		assertEquals(movedW2, w2.mouseMoved(mev2));
	}

	@Test
	void testKeyPressed() {
		KeyEvent xKey = new KeyEvent(null, 0, 0, 0, 'x', 'x');
		assertTrue(w1.keyPressed(xKey) instanceof GameOverWorld);

		KeyEvent cKey = new KeyEvent(null, 0, 0, 0, 'c', 'c');
		assertEquals(w1, w1.keyPressed(cKey));
	}
}

