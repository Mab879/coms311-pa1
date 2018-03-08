/**
 * @author Matthew Burket
 * @author Joel May
 */
class RollingHash {
    private final int alpha = 31;
    private final int alphaToNMinusOne;

    private int sLength;
    private String s;

    private int nextShinglePos = 0;
    private String shingleStr;
    private int rollingHash;

    RollingHash(String s, int sLength) {
        this.s = s;
        this.sLength = sLength;
        alphaToNMinusOne = alpha ^ (sLength - 1);
    }

    Tuple next() {
        if (!hasNext()) {
            return null;
        }

        // Calculate the hash
        if (nextShinglePos == 0) {
            rollingHash = shingleStr.hashCode();
        } else {
            rollingHash = nextHash();
        }
        // Calculate the shingle string
        shingleStr = s.substring(nextShinglePos, sLength);

        nextShinglePos++;
        assert rollingHash == shingleStr.hashCode();
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
                - s.charAt(nextShinglePos - 1) * alphaToNMinusOne)  // Remove s[nextShinglePos-1]*alpha^(n-1)
                * alpha                                             // Increment all alpha exponents by 1
                + s.charAt(nextShinglePos + sLength - 1);           // Add s[nextShinglePos+sLength-1]
    }
}
