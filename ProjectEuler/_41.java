
import java.util.*;

public class _41 {

    public static void main(String[] args) {
        String s = "123456789";
        boolean[] sieve = new boolean[87654322];
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
        char[] tmp;
        for (int i = primes.size() - 1; i >= 0; i--) {
            tmp = primes.get(i).toString().toCharArray();
            Arrays.sort(tmp);
            if (s.substring(0, tmp.length).equals(new String(tmp))) {
                System.out.println(primes.get(i));
                break;
            }
        }
    }
}
