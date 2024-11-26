import java.util.Objects;

import processing.core.PApplet;
import processing.core.PConstants;

/** Interface to represent a list of watermelons */
public interface ILOW {
	PApplet draw(PApplet w);  // Render all watermelons in the list
	ILOW update();            // Update all watermelons in the list
	ILOW addWatermelon(Watermelon wm);  // Add a new watermelon to the list
	ILOW removeOffScreen();   // Remove watermelons that have fallen off the screen
	ILOW removeEaten(HungrySnake s);  // Remove watermelons eaten by the snake
	int countEaten(HungrySnake s);    // Count how many watermelons have been eaten
	int countOffScreen();    // Count how many watermelons are off the screen
}

/**
 * Empty list of watermelons
 */
class MTLOW implements ILOW {

	public MTLOW() {

	}

	public PApplet draw(PApplet w) {
		return w; // no watermelons
	}

	public ILOW update() {
		return this; // no watermelons
	}

	public ILOW addWatermelon(Watermelon wm) {
		return new ConsLOW(wm, this); // add watermelon to the list
	}

	public ILOW removeOffScreen() {
		return this; // no watermelons
	}

	public ILOW removeEaten(HungrySnake s) {
		return this; // no watermelons
	}

	public int countEaten(HungrySnake s) {
		return 0; // no watermelons
	}

	public int countOffScreen() {
		return 0; // no watermelons
	}
}

/**
 * Non-empty list of watermelons
 */
class ConsLOW implements ILOW {
	private Watermelon first;
	private ILOW rest;

	public ConsLOW(Watermelon first, ILOW rest) {
		this.setFirst(first);
		this.setRest(rest);
	}

	public PApplet draw(PApplet w) {
		w.imageMode(PConstants.CENTER);
		w.image(w.loadImage("WM1.png"), (float) this.first.getX() , (float) this.first.getY()); // draw the first watermelon
		rest.draw(w); // recursively call it upom rest of the list
		return w;
	}

	public ILOW update() {
		return new ConsLOW(getFirst().update(), getRest().update()); // update the first and call it upon the rest
	}

	public ILOW addWatermelon(Watermelon wm) {
		return new ConsLOW(wm, this); // add watermelon at the front of the list
	}

	public ILOW removeOffScreen() {
		if (getFirst().IsOffScreen()) {
			return getRest().removeOffScreen(); // checks if the first should be remove 
		}
		else {return new ConsLOW(getFirst(), getRest().removeOffScreen()); // recursivaly check it upon rest
		}
	}

	public ILOW removeEaten(HungrySnake s) {
		if (s.ateWatermelon(getFirst())) { // checks if first is eaten
			return getRest().removeEaten(s);
		} else {
			return new ConsLOW(getFirst(), getRest().removeEaten(s)); // recurively check it upon rest
		}    }

	public int countEaten(HungrySnake s) {
		if (s.ateWatermelon(getFirst())) { 
			return 1 + getRest().countEaten(s); // checks if first is ate if true adds a point
		} else {
			return getRest().countEaten(s); // recursivaly check it upon rest
		}
	}

	public int countOffScreen() {
		if (getFirst().IsOffScreen()) {
			return 1 + getRest().countOffScreen(); // count this watermelon as off-screen
		} else {
			return getRest().countOffScreen(); // recursive check it upon rest
		}
	}

	// Hashcodes, equals, tostring, and getters
	@Override
	public String toString() {
		return "ConsLOW [first=" + first + ", rest=" + rest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, rest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsLOW other = (ConsLOW) obj;
		return Objects.equals(first, other.first) && Objects.equals(rest, other.rest);
	}

	public Watermelon getFirst() {
		return first;
	}

	public void setFirst(Watermelon first) {
		this.first = first;
	}

	public ILOW getRest() {
		return rest;
	}

	public void setRest(ILOW rest) {
		this.rest = rest;
	}


}
	
        

