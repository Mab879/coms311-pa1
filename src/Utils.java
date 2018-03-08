class Utils {
    static int getPrime(int num) {
        while (true) {
            if (isPrime(num)) {
                return num;
            }
            num++;
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num) + 0.001; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
