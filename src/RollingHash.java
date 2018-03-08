/**
 * @author Matthew Burket
 * @author Joel May
 */
class RollingHash {
    private final int alpha = 31;
    private final long alphaToNMinusOne;

    private int sLength;
    private String s;

    private int nextShinglePos = 0;
    private int rollingHash;

    RollingHash(String s, int sLength) {
        // Validate arguments
        if (s == null) {
            s = "";
        }
        if (sLength < 1) {
            throw new IllegalArgumentException("Shingle size must be positive");
        }

        // Initialize instance variables
        this.s = s;
        this.sLength = sLength;
        alphaToNMinusOne = (long) Math.pow(alpha, sLength - 1);
    }

    Tuple next() {
        if (!hasNext()) {
            return null;
        }

        // Calculate the shingle string
        String shingleStr = s.substring(nextShinglePos, nextShinglePos + sLength);
        // Calculate the hash
        if (nextShinglePos == 0) {
            rollingHash = shingleStr.hashCode();
        } else {
            rollingHash = nextHash();
        }

        nextShinglePos++;
        return new Tuple(rollingHash, shingleStr);
    }

    boolean hasNext() {
        return (nextShinglePos + sLength) <= s.length();
    }

    private int nextHash() {
        // Old rolling hash:
        // s[nextShinglePos-1]*alpha^(n-1) + s[nextShinglePos]*alpha^(n-2) + ... + s[nextShinglePos+sLength-2]
        // New rolling hash:
        // s[nextShinglePos]*alpha^(n-1) + s[nextShinglePos+1]*alpha(n-2) + ... + s[nextShinglePos+sLength-1]
        return (rollingHash
                - (int) (s.charAt(nextShinglePos - 1) * alphaToNMinusOne))  // Remove s[nextShinglePos-1]*alpha^(n-1)
                * alpha                                                     // Increment all alpha exponents by 1
                + s.charAt(nextShinglePos + sLength - 1);                   // Add s[nextShinglePos+sLength-1]
    }
}
