/**
 * @author Matthew Burket
 * @author Joel May
 */
abstract class AbstractHashSimilarity extends AbstractSimilarity {
    /** An iterable array of shingles in s1's multi-set. */
    private IterableHashTable s1MultiSet = new IterableHashTable(13); // O(1)
    /** An iterable array of shingles in s2's multi-set. */
    private IterableHashTable s2MultiSet = new IterableHashTable(13); // O(1)
    /** An iterable array of the de-duped union of s1's and s2's multi-sets. */
    private IterableHashTable unionSet;

    // Overall: O(nk+mk-k^2)
    /**
     * Constructor to initialize the values.
     *
     * @param s1      string 1 to compare
     * @param s2      string 2 to compare
     * @param sLength the shingle size for comparisons
     */
    protected AbstractHashSimilarity(String s1, String s2, int sLength) {
        super(sLength); // O(1)

        // The union array is upper bounded by this equation.
        int unionUpperBound = s1.length() + s2.length() - 2 * sLength; // O(1)
        unionSet = new IterableHashTable(unionUpperBound); // 2*O(s1.length+s2.length-k)

        loadHashes(s1, sLength, s1MultiSet); // O(s1.length * k - k^2)
        loadHashes(s2, sLength, s2MultiSet); // O(s2.length * k - k^2)
    }

    @Override
    public float lengthOfS1() {
        return vectorLength(s1MultiSet);
    }

    @Override
    public float lengthOfS2() {
        return vectorLength(s2MultiSet);
    }

    // Overall: O(nk + mk - k^2)
    @Override
    public float similarity() {
        int numerator = 0;
        for (Tuple i : unionSet) { // O(n + m - k)
            Tuple hashForCompare = processSHash(i); // O(1)
            numerator += s1MultiSet.search(hashForCompare) // O(k)
                    * s2MultiSet.search(hashForCompare);   // O(k)
        }
        return (float) numerator / lengthOfS1() // O(nk-k^2)
                / lengthOfS2();                 // O(mk-k^2)
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

    // Overall: O(k)
    private void addUnionHash(Tuple t) {
        if (unionSet.search(t) == 0) { // O(k)
            unionSet.add(t); // O(1)
        }
    }

    // Overall: O(s.length * k - k^2)
    private void loadHashes(String s, int sLength, HashTable sMultiSet) {
        RollingHash rh = new RollingHash(s, sLength); // O(log(k))

        while (rh.hasNext()) { // O(s.length - k)
            // Calculate a hash with its string
            Tuple hashTuple = rh.next(); // O(k)
            // Store the hash in the union, if it doesn't exist.
            addUnionHash(hashTuple); // O(k)
            // Store the hash in the multi-set
            sMultiSet.add(processSHash(hashTuple)); // O(1)
        }
    }

    protected abstract Tuple processSHash(Tuple t);
}
