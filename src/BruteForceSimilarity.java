// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
 * @author Hugh Potter
 */

public class BruteForceSimilarity extends AbstractSimilarity {
    /** String 1 to compare. */
    private String s1;
    /** String 2 to compare. */
    private String s2;

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
    }

    @Override
    public float lengthOfS1() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float lengthOfS2() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float similarity() {
        throw new UnsupportedOperationException();
    }
}