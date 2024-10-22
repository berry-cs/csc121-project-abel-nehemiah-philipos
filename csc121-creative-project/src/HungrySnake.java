import java.util.Objects;

import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * Represents an interactive snake that eats
 * watermelon that falls down from the top of the window. If the 
 * user moves the mouse, the snake is moved with the mouse
 */

public class HungrySnake  {
	
	@Override
	public String toString() {
		return "HungrySnake [x=" + x + ", y=" + y + ", maxX=" + maxX + ", minX=" + minX + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maxX, minX, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HungrySnake other = (HungrySnake) obj;
		return Double.doubleToLongBits(maxX) == Double.doubleToLongBits(other.maxX)
				&& Double.doubleToLongBits(minX) == Double.doubleToLongBits(other.minX)
				&& Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x) && y == other.y;
	}

	double x; // position of the snake 
	int y; // always constant
	double maxX;  // Maximum X to contain snake
	double minX;  // Minimum X to contain sanke
	
	
	 public HungrySnake(double x) {
	        this.x = x;
	        this.y = 595;
	        this.minX = 0.00; // left edge of screen
	        this.maxX = 800.00; // right edge of screen
	        
	 }
	 
	 /**
	  * Renders a snake on the window
	  */
	    public PApplet draw(PApplet snake) {
	    	snake.background(152,190,100);

	    	snake.ellipseMode(PApplet.CENTER);
	    	snake.fill(0, 255, 0);
	    	snake.ellipse((int)this.x, (int)this.y, 30, 30);

	    	//snake.fill(0);
	        //snake.text(toString(), 10, 90);

	        return snake;
	    }
	    
	    /**
	     * Produces an updated snake where the snake moves
	     * with the mouse, if it hasn't hit the max or min x
	     */
	    public HungrySnake update() {
	    	if (this.x < minX) {
	    		this.x = minX;  // Prevent snake from moving left off screen
	    	}
	    	if (this.x > maxX) {
	    		this.x = maxX;  // Prevent snake from moving right off screen
	        }
	        return this;
	    }
	    
	    /**
	     * Moves the snake according to the mouse's X position
	     */
	    public HungrySnake mouseMoved(MouseEvent mev) {
	    	this.x = mev.getX();  // Update snake's x position to follow mouse
	        return this.update();
	    }
	    
	    /**
	     * Checks the collision of a sanke and wartermelon 
	     */
	    public boolean ateWatermelon(Watermelon w) {
	        return Math.sqrt( Math.pow(this.x - w.x, 2) + Math.pow(this.y - w.y, 2) )  <= 22;
	    }

}



