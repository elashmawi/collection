
public class _357 {

    public static void main(String[] args) {
        int n = 100000000;
        boolean[] notPrime = NumberTheory.sieve(n + 1);
        long result = 0;
        for (int i = 1; i <= n; i++) {
            boolean satisfied = true;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0 && notPrime[j + i / j]) {
                    satisfied = false;
                    break;
                }
            }
            if (satisfied) {
                result += i;
            }
        }
        System.out.println(result);
    }
}
