import java.util.*;
import java.io.*;

/**
 * The "DayTwoSolution" class solves both parts of the "Advent Of Code"
 * puzzle for December 2nd, 2022.
 */
public class DayTwoSolution {

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The `returnMoveScore()` method accepts a single parameter - a 
     * String ('gestureGlyph') - and returns an integer value 
     * representing the player's score for the move they make 
     * (Rock == "A", "X" = 1 point; Paper == "B", "Y" = 2; 
     * Scissors == "C", "Z" = 3) based on the 'gestureGlyph'. If the 
     * proper 'gestureGlyph' is not provided, the method returns -1 
     * indicating an ERROR has occurred.
     * 
     * @param gestureGlyph The String representation of the "gesture" a 
     *                     player makes (Rock, Paper, Scissors). 
     * @return The point value ascribed to that particular gesture.
     */
    private static int returnMoveScore(String gestureGlyph) {
        int playerMoveScore = switch (gestureGlyph) {
            case "A", "X" -> { yield 1; }
            case "B", "Y" -> { yield 2; }
            case "C", "Z" -> { yield 3; }
            default       -> { yield -1; }
        };
        return playerMoveScore;
    }

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART ONE ONLY !!!!!!!!!!!!!
     * 
     * The `returnPartOneGameScore()` method accepts two String 
     * parameters - 'glyphOne' & 'glyphTwo' - returning the score as 
     * determined by the parameters outlined in Part One of the 
     * problem's premise.
     * 
     * @param glyphOne The first glyph passed in from the 
     *                 "DayTwoInput.txt" file, representing the Elf's 
     *                 gesture according to Part One of the problem's 
     *                 premise.
     * @param glyphTwo The second glyph passed in from the 
     *                 "DayTwoInput.txt" file, representing what the 
     *                 Player's gesture should be according to Part One 
     *                 of the problem's premise.
     * @return The overall score for the game based on the Player 
     *         winning (6), losing (0), or the game coming to a draw 
     *         (3).
     */
    private static int returnPartOneGameScore(String glyphOne, String glyphTwo) {
        if ((glyphOne.equals("A") && glyphTwo.equals("X")) ||
                (glyphOne.equals("B") && glyphTwo.equals("Y")) ||
                (glyphOne.equals("C") && glyphTwo.equals("Z"))) {
            return 3;
        } else if ((glyphOne.equals("A") && glyphTwo.equals("Y")) ||
                (glyphOne.equals("B") && glyphTwo.equals("Z")) ||
                (glyphOne.equals("C") && glyphTwo.equals("X"))) {
            return 6;
        } else {
            return 0;
        }
    }

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART TWO ONLY !!!!!!!!!!!!!
     * 
     * The `returnPartTwoGameScore()` method accepts a single String 
     * parameter - 'gameResult' - returning an integer score based on 
     * the intended result of the game ("X" == Lose = 0; 
     * "Y" == Draw = 3; "Z" == Win = 6). If the proper 'gameResult' is 
     * not provided, the method returns a -1 indicating an ERROR has 
     * occurred.
     * 
     * @param gameResult The String representation of the intended end 
     *                   result of that specific game (Win, Lose, 
     *                   Draw).
     * @return A integer representing the score awarded based on the 
     *         'gameResult'.
     */
    private static int returnPartTwoGameScore(String gameResult) {
        int gameScore = switch(gameResult) {
            case "X" -> { yield 0; }
            case "Y" -> { yield 3; }
            case "Z" -> { yield 6; }
            default  -> { yield -1; } 
        };
        return gameScore;
    }

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART TWO ONLY !!!!!!!!!!!!!
     * 
     * The `returnComplement()` method accepts two String parameters - 
     * 'gesturePlayerOne' & 'gameResult' - returning a String 
     * 'gesturePlayerTwo' based on those two results. According to the 
     * stipulations provided in Part Two of the problem's premise, the 
     * first letter on each line of the "DayTwoInput.txt" file 
     * represents the Elf's gesture for that round and the second 
     * letter on each line represents the 'gameResult' that should 
     * occur once the Player makes their gesture. The method takes both 
     * of those inputs and calculates the complementary gesture the 
     * Player would have to make to ensure the intended 'gameResult' 
     * occurs.
     * 
     * @param gesturePlayerOne The String representation of the gesture 
     *                         made by the Elf ("A" == Rock; 
     *                         "B" == Paper; "C" == Scissors).
     * @param gameResult The String representation of the intended game 
     *                   result for the Player once they have made 
     *                   their gesture ("X" == Player loses; 
     *                   "Y" == Draw; "Z" == Player wins).
     * @return The String representation of the complementary gesture 
     *         that the Player should make in order to ensure the 
     *         intended 'gameResult' ("X" == Rock; "Y" == Paper; 
     *         "Z" == Scissors).
     */
    private static String returnComplement(String gesturePlayerOne, String gameResult) {
        String gesturePlayerTwo = "";
        if ((gesturePlayerOne.equals("A") && gameResult.equals("Y")) ||
                (gesturePlayerOne.equals("B") && gameResult.equals("X")) ||
                (gesturePlayerOne.equals("C") && gameResult.equals("Z"))) {
            gesturePlayerTwo = "X";
        } else if ((gesturePlayerOne.equals("A") && gameResult.equals("Z")) ||
                (gesturePlayerOne.equals("B") && gameResult.equals("Y")) ||
                (gesturePlayerOne.equals("C") && gameResult.equals("X"))) {
            gesturePlayerTwo = "Y";
        } else if ((gesturePlayerOne.equals("A") && gameResult.equals("X")) ||
                (gesturePlayerOne.equals("B") && gameResult.equals("Z")) ||
                (gesturePlayerOne.equals("C") && gameResult.equals("Y"))) {
            gesturePlayerTwo = "Z";
        }
        return gesturePlayerTwo;
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The `returnTotalScore()` method accepts three parameters - an 
     * integer ('version') and two Strings ('glyphOne', 'glyphTwo') - 
     * returning an integer ('totalScore') representing the ultimate 
     * score of the game. The passed 'version' is evaluated (1 for the 
     * Part One understanding of how the "DayTwoInput.txt" should be 
     * evaluated, 2 for the Part Two understanding of how the 
     * "DayTwoInput.txt" should be evaluated). If an improper value is 
     * passed as the 'version', a -1 value will be returned indicating 
     * an ERROR occurred in calculating the total score. 
     * 
     * @param version An integer value representing whether the score 
     *                should be calculated based on the rules outlined 
     *                in Part One or Part Two of the problem's premise.
     * @param glyphOne A String value representing the Elf's hand 
     *                 gesture for that particular game.
     * @param glyphTwo A String value representing either the gesture 
     *                 the Player should make (Part One) or the 
     *                 intended game result the Player should strive 
     *                 for (Part Two).
     * @return An integer value representing the total score of the 
     *         game (calculated as the sum of the Player's gesture 
     *         score and whether the Player won, lost, or the game 
     *         ended in a draw).
     */
    private static int returnTotalScore(int version, String glyphOne, String glyphTwo) {
        int totalScore = switch (version) {
            case 1 -> {
                yield returnMoveScore(glyphTwo) + returnPartOneGameScore(glyphOne, glyphTwo);
            }
            case 2 -> {
                yield returnMoveScore(returnComplement(glyphOne, glyphTwo))
                        + returnPartTwoGameScore(glyphTwo);
            }
            default -> {
                yield -1;
            }
        };
        return totalScore;
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The `printResult()` method accepts two integer parameters - 
     * ('partOne' & 'partTwo') - which it passes to a printf, 
     * displaying the results and their context to the User in the 
     * terminal in a human-readable format.
     * 
     * @param partOne An integer value representing the total score of 
     *                all matches as calculated per the understanding 
     *                laid out in Part One of the problem's premise.
     * @param partTwo An integer value representing the total score of 
     *                all matches as calculated per the understanding 
     *                laid out in Part Two of the problem's premise.
     */
    private static void printResult(int partOne, int partTwo) {
        System.out.printf(
                "\nBased on our FIRST interpretation of the strategy " 
                + "guide, my total score would be:  %,6d points\n\n" 
                + "Based on our FINAL interpretation of the strategy " 
                + "guide, my total score would be:  %,6d points\n\n",
                partOne, partTwo);
    }

    /**
     * The `solveDayTwo()` method accepts no parameters and returns no 
     * values. Utilizing a Scanner, the method reads data points from 
     * the "DayTwoInput.txt" file and evaluates each data point twice - 
     * once following the rules outlined in Part One and once following 
     * the rules outlined in Part Two. After all values have been 
     * evaluated, the end results are printed to the terminal for the 
     * User in a "human readable" format with context for each answer.
     * 
     * @throws FileNotFoundException If no file is found, an exception 
     *                               will be thrown notifying the User 
     *                               that the file path needs to be 
     *                               updated.
     */
    static void solveDayTwo() throws FileNotFoundException {

        // Instantiate File & Scanner
        File inputDayTwo = new File(
                "/Users/jarvis/.dev/advent-of-code/2022/day02/DayTwoInput.txt");
        Scanner pairs = new Scanner(inputDayTwo);

        // Instantiate Variables
        int sumPartOne = 0, sumPartTwo = 0;

        // Method Behavior
        while (pairs.hasNextLine()) {
            String firstGlyph = pairs.next();
            String secondGlyph = pairs.next();

            sumPartOne += returnTotalScore(1, firstGlyph, secondGlyph);
            sumPartTwo += returnTotalScore(2, firstGlyph, secondGlyph);
        }
        printResult(sumPartOne, sumPartTwo);
        pairs.close();
    }

    /**
     * The `main()` method is called upon running the program and 
     * executes the `solveDayTwo()` method at that time.
     * 
     * @throws FileNotFoundException If no file is found, an exception 
     *                               will be thrown notifying the User 
     *                               that the file path needs to be 
     *                               updated.
     */
    public static void main(String[] args) throws FileNotFoundException {
        solveDayTwo();
    }
}
