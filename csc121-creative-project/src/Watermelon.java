import java.util.Objects;

import processing.core.PApplet;

/**
 * Represents an interactive watermelon that falls
 * slowly from the top of the window. If the snake and
 * watermelone collide it will be eaten
 * if 1 fails to be eaten and hits the bottom of the screen game ends
 */
public class Watermelon {
	
	private static final double maxY = 600.00; // The bottom of the screen where the watermelon should stop
	private static final double maxX = 800.00;  // Maximum X to contain watermelon
	private static final double minX = 0.00;  // Minimum X to contain watermelon
	private static final double InitalSpeed = 0.5;
	private static final double SpeedIncrement = 0.05;

	private double x; // X position of the watermelon
	private double y; // Y position of the watermelon
	private double speed; // Speed at which the watermelon falls
    

    public Watermelon(double x) {
        this.setX(x);
        this.setY(0); // Start at the top of the screen
        this.speed = InitalSpeed; // Initial falling speed
    }

    public Watermelon(double x, double y, double speed) {
		super();
		this.setX(x);
		this.setY(y);
		this.speed = speed;

	}

	/**
     * Renders a watermelon on the screen.
     */
    public PApplet draw(PApplet w) {
    	w.fill(255, 0, 0);
    	w.ellipseMode(PApplet.CENTER);
    	w.circle((int)this.getX(), (int)this.getY(), 15);
        return w;
    }

    /**
     * Updates the watermelon position by making it fall down the screen.
     * The speed increases as it falls.
     */
    public Watermelon update() {
        this.setY(this.getY() + this.speed);
        this.speed += getSpeedincrement(); // Gradually increase speed over time

        if (this.getY() > Watermelon.getMaxy()) {
            this.setY(Watermelon.getMaxy()); // Stop at the bottom of the screen
        }
        return this;
    }
    
    /**
     * Checks if the watermelon is off the screen
     * if its y is greater than maxY
     * */
    public boolean IsOffScreen() {
    	return this.getY() >= Watermelon.getMaxy();
    }


 // Override toString(), hashCode(), and equals()
    @Override
 	public String toString() {
 		return "Watermelon [x=" + getX() + ", y=" + getY() + ", speed=" + (int)speed + ", maxY=" + getMaxy() + ", maxX=" + maxX + ", minX="
 				+ minX + "]";
 	}

 	@Override
	public int hashCode() {
		return Objects.hash(speed, x, y);
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
		return Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed)
				&& Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

 	// get and set methods
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public static double getMaxy() {
		return maxY;
	}

	public static double getSpeedincrement() {
		return SpeedIncrement;
	}
}
    

