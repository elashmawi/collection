
import java.util.ArrayList;

public class _50 {

    public static void main(String[] args) {
        int n = 1000000;
        boolean[] sieve = new boolean[n];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList();
        for (int i = 2; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        int result = 0, terms = 0;
        ArrayList<Integer> sums;
        for (int i = 0; i < primes.size() - terms; i++) {
            sums = new ArrayList();
            sums.add(primes.get(i));
            for (int j = 1; sums.get(j - 1) < n; j++) {
                sums.add(sums.get(j - 1) + primes.get(i + j));
            }
            for (int j = sums.size() - 2; j >= 0; j--) {
                if (!sieve[sums.get(j)] && j + 1 > terms) {
                    result = sums.get(j);
                    terms = j + 1;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
