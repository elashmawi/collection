
import java.util.ArrayList;

public class _37 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[(int) Math.pow(2, 20)];
        sieve[0] = true;
        sieve[1] = true;
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList();
        for (int i = 10; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        int result = 0;
        String s;
        boolean truncatable;
        for (Integer i : primes) {
            s = i.toString();
            truncatable = true;
            while (s.length() > 1 && truncatable) {
                s = s.substring(1);
                truncatable = !sieve[Integer.parseInt(s)];
            }
            s = i.toString();
            while (s.length() > 1 && truncatable) {
                s = s.substring(0, s.length() - 1);
                truncatable = !sieve[Integer.parseInt(s)];
            }
            if (truncatable) {
                result += i;
            }
        }
        System.out.println(result);
    }
}
