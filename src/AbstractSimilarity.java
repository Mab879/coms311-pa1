/**
 * @author Matthew Burket
 * @author Joel May
 */

abstract class AbstractSimilarity {
    /** String 1 to compare. */
    protected String s1;
    /** String 2 to compare. */
    protected String s2;
    /** The shingle size for comparisons. */
    protected int sLength;

    /**
     * Constructor to initialize the values.
     * @param s1 string 1 to compare
     * @param s2 string 2 to compare
     * @param sLength the shingle size for comparisons
     */
    public AbstractSimilarity(String s1, String s2, int sLength) {

    }

    /**
     * The vector length of S1.  sqrt[sum[f(S,i)]^2, {i in S1}], where f(S, i) => number of times i appears in S
     * @return the vector length of S1
     */
    public abstract float lengthOfS1();

    /**
     * The vector length of S2.  sqrt[sum[f(S,i)]^2, {i in S2}], where f(S, i) => number of times i appears in S
     * @return the vector length of S2
     */
    public abstract float lengthOfS2();

    /**
     * Calculates the similarity of the shingles based on the concrete class's implementation.
     * @return (sum[f(S1,i)*f(S2,i), {i in U}])/(lengthOfS1()*lengthOfS2()) where U => union of S1 and S2 after removing
     * the duplicates.
     */
    public abstract float similarity();
}
