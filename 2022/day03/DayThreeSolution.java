import java.util.*;
import java.io.*;

/**
 * The "DayThreeSolution" class solves both parts of the "Advent of Code"
 * puzzle for December 3rd, 2022.
 */
class DayThreeSolution {

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART ONE ONLY !!!!!!!!!!!!!
     * 
     * The private `locateMispackedItemReturnPriority()` method accepts 
     * a single String parameter - 'rucksack' and returns an integer 
     * value representing the "mis-packed item"'s priority. The method 
     * takes the 'rucksack', determines the 'midpoint', and creates two 
     * HashSets - one for each compartment of the rucksack. The method 
     * then calls the `organizeCompartmentOne()` and 
     * `organizeCompartmentTwo()` methods which update the HashSets 
     * accordingly. The HashSets are then compared against each other 
     * to determine synchronous elements. The HashSet containing that 
     * synchronous element is passed to the 
     * `collectBadgeOrItemReturnPriority()` method, which returns the 
     * integer priority evaluation of the element.
     * 
     * @param rucksack A String that represents the entire contents of 
     *                 the Elf's rucksack.
     * @return An integer representing the priority of the located 
     *         "mis-packed item".
     */
    private static int locateMispackedItemReturnPriority(String rucksack) {
        // Instantiate Variables
        int midpoint = rucksack.length() / 2;
        Set<Character> compartmentOneSet = new HashSet<Character>();
        Set<Character> compartmentTwoSet = new HashSet<Character>();

        // Method Behavior
        organizeCompartmentOne(rucksack, midpoint, compartmentOneSet);
        organizeCompartmentTwo(rucksack, midpoint, compartmentTwoSet);
        compartmentOneSet.retainAll(compartmentTwoSet);
        return collectBadgeOrItemReturnPriority(compartmentOneSet);
    }

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART ONE ONLY !!!!!!!!!!!!!
     * 
     * The private `organizeCompartmentOne()` method accepts three 
     * parameters - a String 'rucksack', an integer 'midpoint', and a 
     * HashSet of Characters 'compartment' - returning no values. The 
     * method creates an array of characters from the first half of the 
     * 'rucksack' String, calling the `processContents()` method to 
     * continue the sorting/filtering process. 
     * 
     * @param rucksack A String value representing the total contents 
     *                 of the Elf's rucksack.
     * @param midpoint An integer value representing the middle of the 
     *                 'rucksack' String.
     * @param compartment A HashSet of Characters that will be used to 
     *                    sort/filter the 'rucksack'.
     */
    private static void organizeCompartmentOne(String rucksack, int midpoint, Set<Character> compartment) {
        char[] compartmentOneArray = rucksack.substring(0, midpoint).toCharArray();
        processContents(compartmentOneArray, compartment);
    }

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART ONE ONLY !!!!!!!!!!!!!
     * 
     * The private `organizeCompartmentTwo()` method accepts three 
     * parameters - a String 'rucksack', an integer 'midpoint', and a 
     * HashSet of Characters 'compartment' - returning no values. The 
     * method creates an array of characters from the second half of the 
     * 'rucksack' String, calling the `processContents()` method to 
     * continue the sorting/filtering process.
     * 
     * @param rucksack A String value representing the total contents 
     *                 of the Elf's rucksack.
     * @param midpoint An integer value representing the middle of the 
     *                 'rucksack' String.
     * @param compartment A HashSet of Characters that will be used to 
     *                    sort/filter the 'rucksack'.
     */
    private static void organizeCompartmentTwo(String rucksack, int midpoint, Set<Character> compartment) {
        char[] compartmentTwoArray = rucksack.substring(midpoint).toCharArray();
        processContents(compartmentTwoArray, compartment);
    }
    
    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART TWO ONLY !!!!!!!!!!!!!
     * 
     * The private `locateBadgeReturnPriority()` method accepts three 
     * String parameters - 'knapsack', 'duffel', 'backpack' (so named 
     * so it's easier to identify what's-happening-where in the code 
     * instead of appending numbers to the same generic variable name) 
     * - and returns an integer value representing the badge's 
     * priority. Three HashSets are created for the String parameters. 
     * The `organizeTotalContents()` method is then called three times, 
     * once for each String/HashSet pair. The HashSets are then 
     * compared against each other to determine synchronous elements. 
     * The HashSet containing the synchronous element is then passed to 
     * the `collectBadgeOrItemReturnPriority()` method, which returns 
     * the integer priority evaluation of the element.
     * 
     * @param knapsack A String value representing one of the three 
     *                 'rucksacks' to be evaluated for a badge 
     *                 priority.
     * @param duffel A String value representing one of the three 
     *               'rucksacks' to be evaluated for a badge priority.
     * @param backpack A String value representing one of the three 
     *                 'rucksacks' to be evaluated for a badge 
     *                 priority.
     * @return An integer value representing the badge's priority.
     */
    private static int locateBadgeReturnPriority(String knapsack, String duffel, String backpack) {
        // Instantiate Variables
        Set<Character> knapsackSet = new HashSet<Character>();
        Set<Character> duffelSet = new HashSet<Character>();
        Set<Character> backpackSet = new HashSet<Character>();

        // Method Behavior
        organizeTotalContents(knapsack, knapsackSet);
        organizeTotalContents(duffel, duffelSet);
        organizeTotalContents(backpack, backpackSet);
        knapsackSet.retainAll(duffelSet);
        knapsackSet.retainAll(backpackSet);
        return collectBadgeOrItemReturnPriority(knapsackSet);
    }

    /**
     * !!!!!!!!!!!!! THIS METHOD APPLIES TO PART TWO ONLY !!!!!!!!!!!!!
     * 
     * The private `organizeTotalContents()` method accepts two 
     * parameters - a String 'rucksack' and a HashSet of Characters 
     * 'rucksackSet' - returning no values. The method creates an array 
     * of characters from the 'rucksack' String, calling the 
     * `processContents()` method to continue the sorting/filtering 
     * process.
     * 
     * @param rucksack A String representing the entire contents of the 
     *                 Elf's rucksack.
     * @param rucksackSet A HashSet of Characters that will be used to 
     *                    sort/filter the 'rucksack'.
     */
    private static void organizeTotalContents(String rucksack, Set<Character> rucksackSet) {
        char[] rucksackArray = rucksack.toCharArray();
        processContents(rucksackArray, rucksackSet);
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The private `processContents()` method accepts two parameters - 
     * an Array of characters 'array' and a HashSet of Characters 'bag' 
     * - returning no values. The method creates an Array of Characters 
     * 'inventory' (the same length of the 'array') and loops through 
     * all of the values, inserting each into the 'inventory'. The 
     * 'inventory' is then added to the 'bag'.
     * 
     * @param array An Array of characters representing all of the 
     *              characters in the 'rucksack' String.
     * @param bag A HashSet of Characters which will hold all unique 
     *            characters in the 'rucksack' String.
     */
    private static void processContents(char[] array, Set<Character> bag) {
        Character[] inventory = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            inventory[i] = Character.valueOf(array[i]);
        }
        bag.addAll(Arrays.asList(inventory));
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The private `collectBadgeOrItemReturnPriority()` method accepts 
     * a single HashSet of a characters - 'badgeOrItemSet' - as a 
     * parameter - returning an integer value representing that 
     * badge/item's priority. The method creates an Array of Objects 
     * 'badgeOrItem' and is passed the value contained in the 
     * 'badgeOrItemSet' which will be the synchronous element that was 
     * present in all the previously compared HashSets. The 
     * 'badgeOrItem' Array is then passed to the `returnPriority()` 
     * method which returns the integer value representing that 
     * badge/item's priority. 
     * 
     * @param badgeOrItemSet A Set of Characters containing the 
     *                       synchronous element that was present in 
     *                       all the previously compared HashSets.
     * @return The integer value representing the badge/item's 
     *         priority.
     */
    private static int collectBadgeOrItemReturnPriority(Set<Character> badgeOrItemSet) {
        Object[] badgeOrItem = badgeOrItemSet.toArray();
        return returnPriority(badgeOrItem[0]);
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The private `returnPriority()` method accepts a single Object as 
     * a parameter - 'badgeOrItem' - returning the integer value 
     * representing that badge/item's priority. The method searches 
     * for the index of the 'badgeOrItem' within the 'tally' String and 
     * returns that value, incremented by one, as the priority value 
     * for that badge/item.
     * 
     * @param badgeOrItem An Object representing the badge/item that 
     *                    was the only synchronous element when 
     *                    comparing two or more HashSets against each 
     *                    other.
     * @return The integer value representing the badge/item's 
     *         priority.
     */
    private static int returnPriority(Object badgeOrItem) {
        String tally = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return tally.indexOf(badgeOrItem.toString()) + 1;
    }

    /**
     * !!!!!!!!!!!!!! THIS METHOD APPLIES TO BOTH PARTS !!!!!!!!!!!!!!
     * 
     * The `printResult()` method accepts two integer parameters -
     * 'items' & 'badges' - which it passes to a printf, displaying the
     * results and their context to the User in a "human-readable"
     * format.
     * 
     * @param items  An integer value representing the total of the
     *               priorities for all of the "mispacked" items by the
     *               Elf.
     * @param badges An integer value representing the total of the
     *               priorities for all of the badges for every set of
     *               three Elves.
     */
    private static void printResult(int items, int badges) {
        System.out.printf("\nThe total priority of the 'MIS-PACKED'"
                + " ITEMS is:  %,d\n\nThe total priority of the "
                + "UNSTICKERED BADGES is:  %,d\n\n", items, badges);
    }

    /**
     * The `solveDayThree()` method accepts no parameters and returns
     * no values. Using a Scanner, the method reads data points from
     * the "DayThreeInput.txt" file and evaluations each data point
     * twice - once following the rules outlined in Part One and once
     * following the rules outlined in Part Two. After all values have
     * been evaluated, the end results are printed to the terminal for
     * the User in a "human readable" format with context for each
     * answer.
     * 
     * @throws FileNotFoundException If no file is found an exception
     *                               will be thrown notifying the User
     *                               that the file path needs to be
     *                               updated.
     */
    static void solveDayThree() throws FileNotFoundException {
        // Instantiate File & Scanner
        File inputDayThree = new File(
                "/Users/jarvis/.dev/advent-of-code/2022/day03/DayThreeInput.txt");
        Scanner rucksacks = new Scanner(inputDayThree);

        // Instantiate Variables
        int totalPriorityOfMispackedItems = 0;
        int totalPriorityOfBadges = 0;
        String rucksackOne, rucksackTwo, rucksackThree;

        // Method Behavior
        while (rucksacks.hasNextLine()) {
            rucksackOne = rucksacks.next();
            rucksackTwo = rucksacks.next();
            rucksackThree = rucksacks.next();

            totalPriorityOfMispackedItems += (locateMispackedItemReturnPriority(rucksackOne) +
                    locateMispackedItemReturnPriority(rucksackTwo) +
                    locateMispackedItemReturnPriority(rucksackThree));
            totalPriorityOfBadges += locateBadgeReturnPriority(rucksackOne, rucksackTwo, rucksackThree);
        }
        printResult(totalPriorityOfMispackedItems, totalPriorityOfBadges);
        rucksacks.close();
    }

    /**
     * The `main()` method is called upon running the program and
     * executes the `solveDayThree()` method at that time.
     * 
     * @throws FileNotFoundException If no file is found, an exception
     *                               will be thrown notifying the User
     *                               that the file path needs to be
     *                               updated.
     */
    public static void main(String[] args) throws FileNotFoundException {
        solveDayThree();
    }
}
