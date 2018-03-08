/**
 * @author Matthew Burket
 * @author Joel may
 * 
 * Class for helper methods
 */
class Utils {
    /**
     * Gets the next prime bigger than num
     *
     * @param num starting point for finding a prime
     * @return prime number bigger or equal to num
     */
    static int getPrime(int num) {
        while (true) {
            if (isPrime(num)) {
                return num;
            }
            num++;
        }
    }

    /**
     * Checks if a number if prime
     *
     * @param num number to check
     * @return true if prime, otherwise false
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num) + 0.001; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
