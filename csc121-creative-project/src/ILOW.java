import processing.core.PApplet;

/** to represent a list of watermelons */
public interface ILOW {
    PApplet draw(PApplet w);
    ILOW update();
    // Adds a new watermelon to the list
    ILOW addWatermelon(Watermelon wm);
    // Removes watermelons that have fallen off the screen
    ILOW removeOffScreen();
    
    // remove all the watermelons in this list that the snake has eaten..
    // really: produce a copy of this list with only the watermelon that the
    //     snake has *not* eaten
    ILOW removeEaten(HungrySnake s);
    
    int countEaten(HungrySnake s);
}

class MTLOW implements ILOW {

    public PApplet draw(PApplet w) {
        return w; 
    }

    public ILOW update() {
        return this; 
    }

    public ILOW addWatermelon(Watermelon wm) {
        return new ConsLOW(wm, this); 
    }

    public ILOW removeOffScreen() {
        return this; 
    }

	public ILOW removeEaten(HungrySnake s) {
		return this;
	}

	public int countEaten(HungrySnake s) {
		return 0;
	}
}

class ConsLOW implements ILOW {
    Watermelon first;
    ILOW rest;

    public ConsLOW(Watermelon first, ILOW rest) {
        this.first = first;
        this.rest = rest;
    }

    public PApplet draw(PApplet w) {
        first.draw(w);
        return rest.draw(w);
    }

    public ILOW update() {
        return new ConsLOW(first.update(), rest.update());
    }

    public ILOW addWatermelon(Watermelon wm) {
        return new ConsLOW(wm, this);
    }

    public ILOW removeOffScreen() {
        if (first.IsOffScreen()) {
            return rest.removeOffScreen(); // Remove the first and keep checking the rest
        }
        else {return new ConsLOW(first, rest.removeOffScreen()); // Keep the first and check the rest
        }
    }

    /**
     * Remove eaten watermelons
     */
    public ILOW removeEaten(HungrySnake s) {
    	if (s.ateWatermelon(first)) { // checks if first is eaten
    		return rest.removeEaten(s);
    	} else {
    		return new ConsLOW(first, rest.removeEaten(s)); // Recurively call it 
    	}    }
    
    /**
     * Count the number of watermelons eaten by the snake
     */
    public int countEaten(HungrySnake s) {
    	if (s.ateWatermelon(first)) { 
    		return 1 + rest.countEaten(s); // checks if first is ate if true adds a point
    	} else {
    		return rest.countEaten(s); // recursivaly call it 
    	}
    }
        


}

