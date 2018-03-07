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
    /**
     * Constructor to initialize the values.
     *
     * @param s1      string 1 to compare
     * @param s2      string 2 to compare
     * @param sLength the shingle size for comparisons
     */
    public BruteForceSimilarity(String s1, String s2, int sLength) {
        super(s1, s2, sLength);
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