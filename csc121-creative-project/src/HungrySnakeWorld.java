import java.util.Objects; 

import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * Represents an interactive world that brings the
 * snake and watermelon all into one world
 */
public class HungrySnakeWorld implements IWorld {

	private HungrySnake snake;
	private ILOW watermelons;
	private int spawnCounter;
	private int score;
	private int lives;

	private static final int MaxX = 800;
	private static final int MinSpawnCounter = 50;
	private static final int MaxSpawnCounter = 100;



	public HungrySnakeWorld(HungrySnake snake, ILOW watermelons, int spawnCounter, int score, int lives) {
		this.snake = snake;
		this.watermelons = watermelons;
		this.spawnCounter = spawnCounter;
		this.score = score;
		this.lives = lives;
	}


	/**
	 * Draws the snake, watermelons, and score on the window.
	 */
	public PApplet draw(PApplet w) {
		snake.draw(w);
		watermelons.draw(w);
		w.fill(255);
		w.text("Score: " + score, 10, 20); 
		w.imageMode(PConstants.CENTER);
		w.image(w.loadImage("Small Snake.png"), (float) this.snake.getX() , this.snake.getY()-25);
		return w;
	}

	/**
	 * Updates the world: moves the snake, checks world conditions, and spawns new watermelons.
	 */
	public IWorld update() {
		if (lives - watermelons.countOffScreen() <= 0) { // Check if the game is over
			return new GameOverWorld(this.score); 
		}

		if (this.spawnCounter == 0) { // Spawn new watermelon
			return addWatermelon( new Watermelon( Math.random() * MaxX ) );
		} else {
			return new HungrySnakeWorld( snake.update(), 
					watermelons.removeOffScreen().update().removeEaten(snake), 
					this.spawnCounter - 1,
					score + watermelons.countEaten(snake),
					lives - watermelons.countOffScreen());
		}
	}


	/**
	 * Handles if the x key is pressed, the game will end.
	 */
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == 'x') {
			return new GameOverWorld(this.score);
		} else {
			return this;
		}
	}

	/**
	 * Moves the snake according to the mouse's X position.
	 */ 
	public IWorld mouseMoved(MouseEvent mev)  {
		return new HungrySnakeWorld( snake.mouseMoved(mev), watermelons, spawnCounter, score, lives); 
	}

	/**
	 * Adds a new watermelon to the world
	 */
	public IWorld addWatermelon(Watermelon wm) {
		return new HungrySnakeWorld( snake, watermelons.addWatermelon(wm), (int)(MinSpawnCounter + (Math.random() * MaxSpawnCounter)), score, lives);   // reset spawn counter
	}



	@Override
	public String toString() {
		return "HungrySnakeWorld [snake=" + snake + ", watermelons=" + watermelons + ", spawnCounter=" + spawnCounter
				+ ", score=" + score + ", lives=" + lives + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(lives, score, snake, spawnCounter, watermelons);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HungrySnakeWorld other = (HungrySnakeWorld) obj;
		return lives == other.lives && score == other.score && Objects.equals(snake, other.snake)
				&& spawnCounter == other.spawnCounter && Objects.equals(watermelons, other.watermelons);
	}


}
