
public class _518 {

    public static void main(String[] args) {
        int n = 100000000;
        boolean[] notPrime = NumberTheory.sieve(n);
        int[] primes = NumberTheory.primes(notPrime);
        long result = 0;
        for (int a : primes) {
            long[] squareDivisors = NumberTheory.divisors(NumberTheory.trialDivision(a + 1), 2);
            for (long tmp : squareDivisors) {
                int q = (int) Math.round(Math.sqrt(tmp));
                for (int p = q + 1; (a + 1) / (q * q) * p * p <= n; p++) {
                    if (NumberTheory.gcd(p, q) == 1) {
                        int b = (a + 1) / q * p - 1, c = (a + 1) / (q * q) * p * p - 1;
                        if (!notPrime[b] && !notPrime[c]) {
                            result += a + b + c;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
}
