
import java.util.*;

public class _49 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[10000];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList();
        for (int i = 1000; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        char[] tmp1, tmp2, tmp3;
        for (int i : primes) {
            for (int j = 1; j < (9999 - i) / 2; j++) {
                if (!sieve[i + j] && !sieve[i + 2 * j]) {
                    tmp1 = Integer.toString(i).toCharArray();
                    Arrays.sort(tmp1);
                    tmp2 = Integer.toString(i + j).toCharArray();
                    Arrays.sort(tmp2);
                    tmp3 = Integer.toString(i + 2 * j).toCharArray();
                    Arrays.sort(tmp3);
                    if (Arrays.equals(tmp1, tmp2) && Arrays.equals(tmp2, tmp3)) {
                        System.out.println(i + "" + (i + j) + "" + (i + 2 * j));
                    }
                }
            }
        }
    }
}
