import java.util.Objects;

import processing.core.PApplet;

public class Watermelon {

	double x; // X position of the watermelon
    double y; // Y position of the watermelon
    double speed; // Speed at which the watermelon falls
    double maxY; // The bottom of the screen where the watermelon should stop
    double maxX;  // Maximum X to contain watermelon
	double minX;  // Minimum X to contain watermelon

    public Watermelon(double x) {
        this.x = x;
        this.y = 0; // Start at the top of the screen
        this.speed = .5; // Initial falling speed
        this.maxY = 600; 
        this.minX = 0.00; // left edge of screen
        this.maxX = 800.00; // right edge of screen

    }

    public Watermelon(double x, double y, double speed, double maxY, double maxX, double minX) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.maxY = maxY;
		this.maxX = maxX;
		this.minX = minX;
	}

	/**
     * Renders a watermelon on the screen.
     */
    public PApplet draw(PApplet w) {
    	w.fill(255, 0, 0);
    	w.ellipseMode(PApplet.CENTER);
    	w.circle((int)this.x, (int)this.y, 15);
    	
    	
    	//w.fill(0);
    	//w.text(toString(), 10, 60);

        return w;
    }

    /**
     * Updates the watermelon position by making it fall down the screen.
     * The speed increases as it falls.
     */
    public Watermelon update() {
        this.y += this.speed;
        this.speed += 0.05; // Gradually increase speed over time

        if (this.y > this.maxY) {
            this.y = this.maxY; // Stop at the bottom of the screen
        }
        return this;
    }
    
    /**
     * Checks if the watermelon is off the screen
     * if its y is greater than maxY
     * */
    public boolean IsOffScreen() {
    	return this.y >= this.maxY;
    }


 
    @Override
 	public String toString() {
 		return "Watermelon [x=" + x + ", y=" + y + ", speed=" + (int)speed + ", maxY=" + maxY + ", maxX=" + maxX + ", minX="
 				+ minX + "]";
 	}

 	@Override
 	public int hashCode() {
 		return Objects.hash(maxX, maxY, minX, speed, x, y);
 	}

 	@Override
 	public boolean equals(Object obj) {
 		if (this == obj)
 			return true;
 		if (obj == null)
 			return false;
 		if (getClass() != obj.getClass())
 			return false;
 		Watermelon other = (Watermelon) obj;
 		return Double.doubleToLongBits(maxX) == Double.doubleToLongBits(other.maxX)
 				&& Double.doubleToLongBits(maxY) == Double.doubleToLongBits(other.maxY)
 				&& Double.doubleToLongBits(minX) == Double.doubleToLongBits(other.minX)
 				&& Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed)
 				&& Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
 				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
 	}
}
    

