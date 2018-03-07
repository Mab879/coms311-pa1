// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
 * @author Hugh Potter
 */

public class Tuple {
    private int key;
    private String value;

    /**
     * Tuple Constructor
     *
     * @param keyP
     * @param valueP
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
        return key == t.getKey() && value.equals(t.getValue());
    }
}