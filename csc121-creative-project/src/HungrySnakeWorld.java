import processing.core.PApplet;
import processing.event.MouseEvent;

public class HungrySnakeWorld implements IWorld {

	HungrySnake snake;
	ILOW watermelons;
	int spawnCounter;
	int score;
	


	public HungrySnakeWorld(HungrySnake snake, ILOW watermelons, int spawnCounter, int score) {
		this.snake = snake;
		this.watermelons = watermelons;
		this.spawnCounter = spawnCounter;
		this.score = score;
	}


	/**
	 * Renders a picture of the snake on the window
	 */
	public PApplet draw(PApplet w) {
		snake.draw(w);
		watermelons.draw(w);
		w.fill(255);
		w.text("Score: " + score, 10, 20); 
		return w;
	}


	
	
	
	/**
	 * Produces an updated world where the snake moves
	 * with the mouse, if it hasn't hit the max or min x
	 */
	public IWorld update() {
		if (this.spawnCounter == 0) {
			return addWatermelon( new Watermelon( Math.random() * 800 ) );
		} else {
			return new HungrySnakeWorld( snake.update(), watermelons.removeOffScreen().update().removeEaten(snake), this.spawnCounter - 1,
						score + watermelons.countEaten(snake));
		}
	}


	/**
	 * Moves the snake according to the mouse's X position
	*/ 
	public IWorld mouseMoved(MouseEvent mev)  {
		return new HungrySnakeWorld( snake.mouseMoved(mev), watermelons, spawnCounter, score); 
	}
	
	/**
	 * Adds a new watermelon to the world
	 */
	public IWorld addWatermelon(Watermelon wm) {
		return new HungrySnakeWorld( snake, watermelons.addWatermelon(wm), (int)(50 + (Math.random() * 100)), score);   // reset spawn counter
	}
}
