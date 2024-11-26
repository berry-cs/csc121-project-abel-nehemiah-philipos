import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * Represents a world that is activated when the game ends
 * If 1 watermelon is missed game ends 
 * Displays the top 5 scores
 */

public class GameOverWorld implements IWorld {

	// Constants
	private static final String HIGHSCORES_FILE = "highscores.txt";
	private static final int FontSize = 18;
	private static final int MaxHighScores = 5;
	private static final int ScoreDisplayX = 10;
	private static final int ScoreDisplayY = 60;
	private static final int ScoreDisplayXspace = 20;

	List<HighScore> highScores = new ArrayList<>();

	public GameOverWorld(int newScore) {
		this.highScores = new ArrayList<>();
		String newName = JOptionPane.showInputDialog("Enter your name: "); // Name

		loadHighScores();

		/**
		 * Add new score and sort the list
		 */
		highScores.add(new HighScore(newName, newScore));
		Collections.sort(highScores, new Comparator<HighScore>() {
			public int compare(HighScore hs1, HighScore hs2) {
				return hs2.getScore() - hs1.getScore();
			}
		}
				);


		// top 5 scores
		while (highScores.size() > MaxHighScores) {
			highScores.remove(highScores.size() - 1);
		}

		saveHighScores();
	}

	/**
	 * Draws the game over screen with high scores.
	 */
	public PApplet draw(PApplet w) {
		w.background(0);
		w.textFont(w.createFont("Times New Roman", FontSize));
		w.fill(255);
		w.text("GAME OVER", ScoreDisplayX, ScoreDisplayXspace);
		w.text("High Scores:", ScoreDisplayX, 40);

		// Display high scores 
		int y = ScoreDisplayY;
		for (HighScore hs : highScores) {
			w.text(hs.getName() + ": " + hs.getScore(), ScoreDisplayX, y);
			y += ScoreDisplayXspace;
		}
		return w;
	}

	/**
	 *  Restarts the game when space bar is pressed
	 */
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {
			return new HungrySnakeWorld(new HungrySnake(400),
					new ConsLOW(new Watermelon(400), new MTLOW()), 100, 0, 1);
		}
		else {
			return this;
		}
	}

	/**
	 * load high scores from the file
	 */
	void loadHighScores() {
		try (Scanner fileScanner = new Scanner(new File(HIGHSCORES_FILE))) {
			while (fileScanner.hasNextLine()) {
				String[] parts = fileScanner.nextLine().split(",");
				if (parts.length == 2) {
					String name = parts[0];
					int score = Integer.parseInt(parts[1]); // converts a string into an integer
					highScores.add(new HighScore(name, score));
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * save high scores to the file
	 */
	void saveHighScores() {
		try (PrintWriter pw = new PrintWriter(new File(HIGHSCORES_FILE))) {
			for (HighScore hs : highScores) {
				pw.println(hs.getName() + "," + hs.getScore());
			}
		} 
		catch (IOException e2) {
			System.out.println("Something went wrong: " + e2);
			e2.printStackTrace();
		}
	}
}
    




