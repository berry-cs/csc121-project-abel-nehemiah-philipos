import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {

	HighScore ALiceHighScore = new HighScore("Alice", 100);

	@Test
	void GetTest() {
		assertEquals("Alice", ALiceHighScore.getName());
		assertEquals(100, ALiceHighScore.getScore());
	}
}

