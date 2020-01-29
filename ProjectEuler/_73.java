
import java.util.ArrayDeque;

public class _73 {
    
    public static void main(String[] args) {
        int n = 1000000;
        ArrayDeque<Integer> primes = NumberTheory.primes(NumberTheory.sieve(n));
        long result = 0;
        for (int d = 2; d <= n; d++) {
            result += NumberTheory.eulerPhi(d, primes);
        }
        System.out.println(result);
    }
}
