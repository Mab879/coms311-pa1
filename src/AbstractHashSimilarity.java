/**
 * @author Matthew Burket
 * @author Joel May
 */
abstract class AbstractHashSimilarity extends AbstractSimilarity {
    /** An iterable array of shingles in s1's multi-set. */
    private IterableHashTable s1MultiSet = new IterableHashTable(13);
    /** An iterable array of shingles in s2's multi-set. */
    private IterableHashTable s2MultiSet = new IterableHashTable(13);
    /** An iterable array of the de-duped union of s1's and s2's multi-sets. */
    private IterableHashTable unionSet;

    /**
     * Constructor to initialize the values.
     *
     * @param s1      string 1 to compare
     * @param s2      string 2 to compare
     * @param sLength the shingle size for comparisons
     */
    protected AbstractHashSimilarity(String s1, String s2, int sLength) {
        super(sLength);

        // The union array is upper bounded by this equation.
        int unionUpperBound = s1.length() + s2.length() - 2 * sLength;
        unionSet = new IterableHashTable(unionUpperBound);

        loadHashes(s1, sLength, s1MultiSet);
        loadHashes(s2, sLength, s2MultiSet);
    }

    @Override
    public float lengthOfS1() {
        return vectorLength(s1MultiSet);
    }

    @Override
    public float lengthOfS2() {
        return vectorLength(s2MultiSet);
    }

    @Override
    public float similarity() {
        int numerator = 0;
        for (Tuple i : unionSet) {
            Tuple hashForCompare = processSHash(i);
            numerator += s1MultiSet.search(hashForCompare) * s2MultiSet.search(hashForCompare);
        }
        return (float) numerator / lengthOfS1() / lengthOfS2();
    }

    // Overall: O(s1.length * k - k^2) (or replace s1 with s2)
    private float vectorLength(IterableHashTable S) {
        HashTable countedValues = new HashTable(13); // O(1)
        int sum = 0;
        for (Tuple i : S) { // O(S.size), which is O(s1.length - k)
            Tuple hashForCompare = processSHash(i); // O(1)
            if (countedValues.search(hashForCompare) == 0) { // O(k) when hashForCompare has a string;
                                                             // O(1) when hashForCompare is null
                countedValues.add(hashForCompare); // O(1)
                int occurrences = S.search(hashForCompare); // O(k) when hashForCompare has a string;
                                                            // O(1) when hashForCompare is null
                sum += occurrences * occurrences; // occurrences squared; O(1)
            }
        }
        return (float) Math.sqrt(sum);
    }

    private void addUnionHash(Tuple t) {
        if (unionSet.search(t) == 0) {
            unionSet.add(t);
        }
    }

    private void loadHashes(String s, int sLength, HashTable sMultiSet) {
        RollingHash rh = new RollingHash(s, sLength);

        while (rh.hasNext()) {
            // Calculate a hash with its string
            Tuple hashTuple = rh.next();
            // Store the hash in the union, if it doesn't exist.
            addUnionHash(hashTuple);
            // Store the hash in the multi-set
            sMultiSet.add(processSHash(hashTuple));
        }
    }

    protected abstract Tuple processSHash(Tuple t);
}
