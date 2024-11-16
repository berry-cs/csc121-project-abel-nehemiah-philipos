import java.io.*;
import java.util.*;

public class HighScore {

    public static void main(String[] args) {
        String filename = "highscores.txt";
        List<Integer> highScores = new ArrayList<>();

        /**
         * Scan existing high scores from the file
         */
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextInt()) {
                highScores.add(fileScanner.nextInt());
            }
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

        /** 
         * Sort scores from biggest to smallest
         */
        Collections.sort(highScores);

        /**
         * Show the current high scores
         */
        System.out.println("Top 5 High Scores: " + highScores);

        /**
         *  Get a new score from the user
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your score: ");
        int newScore = input.nextInt();

        /**
         * Adds the new score but only keep the top 5
         */
        highScores.add(newScore);
        Collections.sort(highScores);
        
        while (highScores.size() > 5) {
            highScores.remove(highScores.size() - 1); // Remove the smallest score
        }

        /**
         * Write the updated scores back to the file
         */
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            for (int score : highScores) {
                pw.println(score);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e);
            e.printStackTrace();
        }

        /**
         *  Shows the updated high scores
         */
        System.out.println("New High Scores: " + highScores);
    }
}


