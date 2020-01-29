
public class _58 {

    static boolean isPrime(long n) {
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long current = 9, step = 4, primes = 3;
        while ((double) primes / ((step / 2 - 1) * 4 + 1) >= 0.1) {
            for (int i = 0; i < 4; i++) {
                current += step;
                if (isPrime(current)) {
                    primes++;
                }
            }
            step += 2;
        }
        System.out.println(step - 1);
    }
}
