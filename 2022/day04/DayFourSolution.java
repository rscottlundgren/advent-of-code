import java.util.*;
import java.io.*;

/**
 * The "DayFourSolution" class solves both parts of the "Advent of 
 * Code" puzzle for December 4th, 2022.
 */
class DayFourSolution {

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The `checkForOverlappingAssignmentPairs()` method accepts five 
     * integer parameters - 'part', 'startPairOne', 'closePairOne', 
     * 'startPairTwo', 'closePairTwo' - returning an integer value of 1 
     * if an overlap is present and an integer value of 0 if an overlap 
     * is not present. The 'part' parameter determines which 
     * conditional configuration is used to determine overlap, while 
     * the remaining four parameters are the tested values in a set 
     * configuration to determine either complete or partial overlap.
     * 
     * @param part An integer value representing which part of the 
     *             problem the User is looking to solve.
     * @param startPairOne An integer value representing the start of 
     *                     the first assignment pair's range of 
     *                     sections to clean.
     * @param closePairOne An integer value representing the close of 
     *                     the first assignment pair's range of 
     *                     sections to clean.
     * @param startPairTwo An integer value representing the start of 
     *                     the second assignment pair's range of 
     *                     sections to clean.
     * @param closePairTwo An integer value representing the close of 
     *                     the second assignment pair's range of 
     *                     sections to clean.
     * @return An integer value representing whether an overlap between 
     *         the sections was present (1) or absent (0).
     */
    private static int checkForOverlappingAssignmentPairs(Integer part,
            Integer startPairOne,
            Integer closePairOne,
            Integer startPairTwo,
            Integer closePairTwo) {
        int overlap = switch (part) {
            case 1 -> {
                if ((startPairOne <= startPairTwo && closePairOne >= closePairTwo) ||
                        (startPairTwo <= startPairOne && closePairTwo >= closePairOne)) {
                    yield 1;
                }
                yield 0;
            }
            case 2 -> {
                if ((startPairOne <= startPairTwo && closePairOne >= startPairTwo) ||
                        (startPairTwo <= startPairOne && closePairTwo >= startPairOne)) {
                    yield 1;
                }
                yield 0;
            }
            default -> {
                yield -1;
            }
        };
        return overlap;
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The `printResult()` method accepts two integer parameters - 
     * 'complete', 'partial' - returning no values. The method passes 
     * the parameters to a printf statement which displays the results 
     * in the terminal for the User in a "human-readable" format.
     * 
     * @param complete An integer value representing the number of 
     *                 section ranges that are completely overlapped by 
     *                 the other section range in the assignment pair. 
     * @param partial An integer value representing the number of 
     *                section ranges that are partially overlapped by 
     *                the other section range in the assignment pair.
     */
    private static void printResult(Integer complete, Integer partial) {
        System.out.printf("\nThere are %d assignment pairs where "
                + "ONE PAIR FULLY CONTAINS THE OTHER.\n\nThere are "
                + "%d assignment pairs where ONE PAIR PARTIALLY "
                + "CONTAINS THE OTHER.\n\n", complete, partial);
    }

    /**
     * The `solveDayFour()` method accepts no parameters and returns no
     * values. Using a Scanner, the method reads four data points from 
     * the "DayFourInput.txt" file and evaluates each data point twice 
     * using the `checkForOverlappingAssignmentPairs()` method - once 
     * for a solution to Part 1 and once for a solution to Part 2. The 
     * integer result of each evaluation is then added to the 
     * appropriate running sum. Once the file has been read completely, 
     * the `printResults()` method is called, which displays the 
     * results in the terminal for the User in a "human-readable" 
     * format with context.
     * 
     * @throws FileNotFoundException If no file is found an exception
     *                               will be thrown notifying the User
     *                               that the file path needs to be
     *                               updated.
     */
    static void solveDayFour() throws FileNotFoundException {

        // Instantiate File & Scanner, Set Scanner Delimiter
        File inputDayFour = new File(
                "/Users/jarvis/.dev/advent-of-code/2022/day04/DayFourInput.txt");
        Scanner assignmentPairs = new Scanner(inputDayFour);
        assignmentPairs.useDelimiter("-|,|\n");

        // Instantiate Variables
        Integer startSectionOne, closeSectionOne, startSectionTwo, closeSectionTwo, totalCompleteOverlaps = 0,
                totalPartialOverlaps = 0;

        // Method Behavior
        while (assignmentPairs.hasNextInt()) {
            startSectionOne = assignmentPairs.nextInt();
            closeSectionOne = assignmentPairs.nextInt();
            startSectionTwo = assignmentPairs.nextInt();
            closeSectionTwo = assignmentPairs.nextInt();

            totalCompleteOverlaps += checkForOverlappingAssignmentPairs(1,
                    startSectionOne,
                    closeSectionOne,
                    startSectionTwo,
                    closeSectionTwo);
            totalPartialOverlaps += checkForOverlappingAssignmentPairs(2,
                    startSectionOne,
                    closeSectionOne,
                    startSectionTwo,
                    closeSectionTwo);
        }
        printResult(totalCompleteOverlaps, totalPartialOverlaps);
        assignmentPairs.close();
    }

    /**
     * The `main()` method is called upon running the program and
     * executes the `solveDayFour()` method at that time.
     * 
     * @throws FileNotFoundException If no file is found an exception
     *                               will be thrown notifying the User
     *                               that the file path needs to be
     *                               updated.
     */
    public static void main(String[] args) throws FileNotFoundException {
        solveDayFour();
    }
}
