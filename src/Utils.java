public class Utils {
    public static int getPrime(int num) {
        while (true) {
            if (isPrime(num)) {
                return num;
            }
            num++;
        }
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (i % 2 == 0) {
                return false;
            }
        }
        return true;
    }
}
