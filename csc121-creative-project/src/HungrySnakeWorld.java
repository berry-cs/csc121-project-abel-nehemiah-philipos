import processing.core.PApplet;
import processing.event.MouseEvent;

public class HungrySnakeWorld implements IWorld {

	HungrySnake snake;
	Watermelon watermelon;
	

	public HungrySnakeWorld(HungrySnake snake, Watermelon watermelon) {
		this.snake = snake;
		this.watermelon = watermelon;
	}

	/**
	 * Renders a picture of the snake on the window
	 */
	public PApplet draw(PApplet w) {
		snake.draw(w);
		watermelon.draw(w);
		return w;
	}


	/**
	 * Produces an updated world where the snake moves
	 * with the mouse, if it hasn't hit the max or min x
	 */
	public IWorld update() {
		return new HungrySnakeWorld( snake.update(), watermelon.update());
	}


	/**
	 * Moves the snake according to the mouse's X position
	*/ 
	public IWorld mouseMoved(MouseEvent mev)  {
		return new HungrySnakeWorld( snake.mouseMoved(mev), watermelon.update()); 
	}
	


}
