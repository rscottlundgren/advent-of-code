import java.util.*;
import java.io.*;

/**
 * The "DayOneSolution" class solves both parts of the "Advent Of Code"
 * puzzle for December 1st, 2022.
 */
public class DayOneSolution {

    /**
     * The `checkTopThree()` method accepts two parameters - a
     * one-dimensional integer array ('topThree') and an integer
     * ('total') - and returns no values. The method uses a boolean
     * value ('hasSwapOccurred') in a "while" loop to short-circuit a
     * "for" loop, which is checking to see if the 'total' is greater
     * than the integer stored in the specified index of 'topThree'. If
     * the 'total' is greater, the `swapTopThreeValue()` method is
     * called and 'hasSwapOccurred' is toggled to true; if the total is
     * not greater, we break the "while" loop and move onto the next
     * index.
     * 
     * @param topThree A one-dimensional array of integers representing
     *                 the "top three" calorie counts carried by three
     *                 individual Elves in the problem's premise.
     * @param total    An integer value representing the total number of
     *                 calories as tallied for the most recent Elf.
     */
    static void checkTopThree(int[] topThree, int total) {
        boolean hasSwapOccurred = false;
        for (int i = 0; i < topThree.length; i++) {
            while (!hasSwapOccurred) {
                if (total > topThree[i]) {
                    swapTopThreeValue(topThree, total, i);
                    hasSwapOccurred = true;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * The `swapTopThreeValue()` method accepts three parameters - a
     * one-dimensional array of integers ('topThree') and two integers
     * ('total' and 'index', respectively) - and returns no values. The
     * method checks if the provided 'index' does not equal the integer
     * two. If the 'index' does not equal two, then it swaps out the
     * integer value at the 'index' in the 'topThree' array, shuffling
     * the original value to the next 'index' on the right; if the
     * 'index' does equal two, then it swaps out the integer value at
     * the 'index', dropping the original value from the array.
     * 
     * @param topThree A one-dimensional array of integers representing
     *                 the "top three" calorie counts carried by three
     *                 individual Elves in the problem's premise.
     * @param total    An integer value representing the total number of
     *                 calories as tallied for the most recent Elf.
     * @param index    The index of the 'topThree' one-dimensional array
     *                 where the 'total' was greater than the integer
     *                 contained in that index.
     */
    static void swapTopThreeValue(int[] topThree, int total, int index) {
        if (index != 2) {
            topThree[index + 1] = topThree[index];
            topThree[index] = total;
        } else {
            topThree[index] = total;
        }
    }

    /**
     * The `printResult()` method accepts a single one-dimensional
     * array of integers as a parameter ('topThree') and returns no
     * values. The method sums the values contained in the array and
     * then prints a "human readable" interpretation of the results.
     * 
     * @param topThree A one-dimensional array of integers representing
     *                 the "top three" calorie counts carreid by three
     *                 individual Elves in the problem's premise.
     */
    static void printResult(int[] topThree) {
        int sum = 0;
        for (int i = 0; i < topThree.length; i++) {
            sum += topThree[i];
        }
        System.out.printf("\nThe most calories carried by a single "
                + "Elf is... %,d.\n\nThe largest calorie counts "
                + "carried by a single elf are:\n\t#1:  %,d\n\t#2:"
                + "  %,d\n\t#3:  %,d\nAltogether, these Elves are "
                + "carrying %,d total calories.\n\n",
                topThree[0], topThree[0], topThree[1], topThree[2], sum);
    }

    /**
     * The `solveDayOne()` method accepts no parameters and returns no
     * values. Utilizing a Scanner, the method reads data points from
     * the "DayOneInput.txt" file and evaluates each data point based
     * on whether the data point contains an integer value or is an
     * empty string. If the data point is an integer value, it will be
     * added to the running sum of 'totalCalories' for that section of
     * data; if the data point is an empty string, the 'totalCalories'
     * are evaluated using the `checkTopThree()` method and the
     * 'totalCalories' variable is reset to 0.
     * 
     * @throws FileNotFoundException If no file is found, an exception
     *                               will be thrown notifying the User
     *                               that the file path needs to be
     *                               updated.
     */
    static void solveDayOne() throws FileNotFoundException {

        // Instantiate File & Scanner
        File day01input = new File(
                "/Users/jarvis/.dev/advent-of-code/2022/day01/DayOneInput.txt");
        Scanner integers = new Scanner(day01input);

        // Instantiate Variables
        String calorieCount;
        int totalCalories = 0;
        int[] topThreeCalorieCounts = new int[3];

        // Method Behavior
        while (integers.hasNextInt()) {
            calorieCount = integers.nextLine();
            if (calorieCount.isEmpty()) {
                checkTopThree(topThreeCalorieCounts, totalCalories);
                totalCalories = 0;
            } else {
                totalCalories += Integer.parseInt(calorieCount);
            }
        }
        printResult(topThreeCalorieCounts);
        integers.close();
    }

    /**
     * The `main()` method is called upon running the program and 
     * executes the `solveDayOne()` method at that time.
     * 
     * @throws FileNotFoundException If no file is found, an exception 
     *                               will be thrown notifying the User 
     *                               that the file path needs to be 
     *                               updated.
     */
    public static void main(String[] args) throws FileNotFoundException {
        solveDayOne();
    }
}
