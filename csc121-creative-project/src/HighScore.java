import java.util.Objects;

/**
 * Represents data of a player that conatins their name and score
 * number of watermelons eaten before 1 was missed
 */
public class HighScore {

	private final String name;
	private final int score;

	public HighScore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * Gets name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets score
	 * 
	 */
	public int getScore() {
		return score;
	}

	// Override toString(), hashCode(), and equals()
	@Override
	public String toString() {
		return "HighScore [name=" + name + ", score=" + score + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighScore other = (HighScore) obj;
		return Objects.equals(name, other.name) && score == other.score;
	}
}
