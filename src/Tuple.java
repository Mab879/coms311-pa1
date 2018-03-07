// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
 * @author Matthew Burket
 * @author Joel May
 */

/**
 * A key-value mapping from an int to a string.
 */
public class Tuple {
    /**
     * The key for this tuple.
     */
    private int key;
    /**
     * The value for the tuple.
     */
    private String value;

    /**
     * Tuple Constructor
     *
     * @param keyP   key of tuple
     * @param valueP value of the tuple
     */
    public Tuple(int keyP, String valueP) {
        key = keyP;
        value = valueP;
    }

    /**
     * Get the key of the tuple
     *
     * @return key of the tuple
     */
    public int getKey() {
        return key;
    }

    /**
     * Get the value of the tuple
     *
     * @return value of the tuple
     */
    public String getValue() {
        return value;
    }

    /**
     * Check equality
     *
     * @param t tuple to compare
     * @return true if this tuple and the given tuple are equal
     */
    public boolean equals(Tuple t) {
        if (t == null) {
            return false;
        }
        if (key == t.getKey()) {
            if (value == null && t.getValue() == null) {
                return true;
            }
            if (value == null) {
                return false;
            }
            return value.equals(t.getValue());
        }
        return false;
    }
}