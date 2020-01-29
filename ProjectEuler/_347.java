
import java.util.ArrayDeque;

public class _347 {

    static int M(int p, int q, int N) {
        int max = 0;
        for (int a = (int) Math.floor((Math.log(N) - Math.log(q)) / Math.log(p)); a > 0; a--) {
            int b = (int) Math.floor((Math.log(N) - a * Math.log(p)) / Math.log(q));
            max = (int) Math.max(max, Math.pow(p, a) * Math.pow(q, b));
        }
        return max;
    }

    public static void main(String[] args) {
        int limit = 10000000;
        ArrayDeque<Integer> tmp = NumberTheory.primes(NumberTheory.sieve(limit / 2));
        Integer[] primes = new Integer[tmp.size()];
        tmp.toArray(primes);
        long result = 0;
        for (int i = 0; i < primes.length; i++) {
            for (int j = i + 1; j < primes.length && primes[i] * primes[j] <= limit; j++) {
                result += M(primes[i], primes[j], limit);
            }
        }
        System.out.println(result);
    }
}
