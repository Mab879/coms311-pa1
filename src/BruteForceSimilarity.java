// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;

/**
 * @author Matthew Burket
 * @author Joel May
 */

public class BruteForceSimilarity extends AbstractSimilarity {
    /**
     * String 1 to compare.
     */
    private String s1;
    /**
     * String 2 to compare.
     */
    private String s2;
    /**
     * shingles for s1
     */
    ArrayList<String> shinglesS1;
    /**
     * shingles for s2
     */
    ArrayList<String> shinglesS2;


    /**
     * Constructor to initialize the values.
     *
     * @param s1      string 1 to compare
     * @param s2      string 2 to compare
     * @param sLength the shingle size for comparisons
     */
    public BruteForceSimilarity(String s1, String s2, int sLength) {
        super(sLength);
        this.s1 = s1;
        this.s2 = s2;
        this.shinglesS1 = shingleString(s1);
        this.shinglesS2 = shingleString(s2);
    }

    @Override
    public float lengthOfS1() {
        return vectorLength(shinglesS1);
    }

    @Override
    public float lengthOfS2() {
        return vectorLength(shinglesS2);
    }

    /**
     * Calculates the vector length
     *
     * @param strings input strings
     * @return vector length of strings
     */
    private float vectorLength(ArrayList<String> strings) {
        int sum = 0;
        ArrayList<String> alreadyDone = new ArrayList<>();
        for (String s : strings) {
            if (alreadyDone.contains(s)) {
                continue;
            }
            int o = countOccurrences(strings, s);
            sum += o * o;
            alreadyDone.add(s);
        }
        return (float) Math.sqrt(sum);
    }

    @Override
    public float similarity() {
        ArrayList<String> result = new ArrayList<>();
        for (String s : shinglesS1) {
            if (!result.contains(s)) {
                result.add(s);
            }
        }

        for (String s : shinglesS1) {
            if (!result.contains(s)) {
                result.add(s);
            }
        }

        int sum = 0;
        for (String s : result) {
            sum += countOccurrences(shinglesS1, s) * countOccurrences(shinglesS2, s);
        }
        return (float) sum / (lengthOfS1() * lengthOfS2());
    }

    /**
     * Takes a string and uses the objects sLength to make shingles
     *
     * @param s string to shingles
     * @return An ArrayList of shingles from s
     */
    private ArrayList<String> shingleString(String s) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < s.length() - sLength + 1; i++) {
            result.add(s.substring(i, i + sLength));
        }
        return result;
    }


    private static int countOccurrences(ArrayList<String> strings, String s) {
        int result = 0;
        for (String string : strings) {
            if (string.equals(s)) {
                result++;
            }
        }
        return result;
    }
}