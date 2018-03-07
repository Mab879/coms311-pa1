/**
 * @author Matthew Burket
 * @author Joel May
 */
abstract class AbstractHashSimilarity extends AbstractSimilarity {
    /** A hash table for the shingles of s1. */ // A random guess at a good initial size.
    private HashTable s1HT = new HashTable(10);
    /** An iterable array of Tuples in s1HT. */
    private Tuple[] s1Arr;
    /** A hash table for the shingles of s2. */ // A random guess at a good initial size.
    private HashTable s2HT = new HashTable(10);
    /** An iterable array of Tuples in s2HT. */
    private Tuple[] s2Arr;

    /**
     * Constructor to initialize the values.
     *
     * @param s1      string 1 to compare
     * @param s2      string 2 to compare
     * @param sLength the shingle size for comparisons
     */
    protected AbstractHashSimilarity(String s1, String s2, int sLength) {
        super(sLength);
        s1Arr = loadHashes(s1, sLength, s1HT);
        s2Arr = loadHashes(s2, sLength, s2HT);
    }

    @Override
    public float lengthOfS1() {
        return vectorLength(s1Arr, s1HT);
    }

    @Override
    public float lengthOfS2() {
        return vectorLength(s2Arr, s2HT);
    }

    @Override
    public float similarity() {
        throw new UnsupportedOperationException();
        // implementation
    }

    private static float vectorLength(Tuple[] s1Arr, HashTable s1HT) {
        int sum = 0;
        for (Tuple t : s1Arr) {
            sum += (countOccurrences(t.getValue(), s1HT)) ^ 2;
        }
        return (float) Math.sqrt(sum);
    }

    private static int countOccurrences(String s, HashTable ht) {
        Tuple search = new Tuple(s.hashCode(), s);
        return ht.search(search);
    }

    private Tuple[] loadHashes(String s, int sLength, HashTable ht) {
        final int alpha = 31;
        final int alphaToNMinusOne = alpha ^ (sLength - 1);

        // Stores an iterable array
        Tuple[] out = new Tuple[s.length() - sLength + 1];
        int outIndex = 0;

        String shingle;
        int rollingHash;
        Tuple shingleTuple;

        // Seed the initial hash
        shingle = s.substring(0, sLength);
        rollingHash = shingle.hashCode();
        shingleTuple = createTuple(rollingHash, shingle);
        // Add the initial hash
        ht.add(shingleTuple);
        out[outIndex++] = shingleTuple;

        // Calculate other hashes by rolling
        for (int i = 1; i < s.length() - sLength; i++) {
            // Move string window.  O(sLength)
            shingle = s.substring(i, sLength);
            // Move hash window.  O(1)
            rollingHash -= s.charAt(i - 1) * alphaToNMinusOne;
            rollingHash *= alpha;
            rollingHash += s.charAt(i + sLength - 1);

            assert rollingHash == shingle.hashCode();
            // Add to the table
            shingleTuple = createTuple(rollingHash, shingle);
            ht.add(shingleTuple);
            out[outIndex++] = shingleTuple;
        }

        return out;
    }

    protected abstract Tuple createTuple(int hash, String s);
}
