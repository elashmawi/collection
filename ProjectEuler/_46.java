
import java.util.ArrayList;

public class _46 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[(int) Math.pow(2, 13)];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList();
        for (int i = 3; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        double tmp;
        boolean done = false;
        for (int i = 3; i < sieve.length && !done; i += 2) {
            if (sieve[i]) {
                done = true;
                for (int j = 0; primes.get(j) < i; j++) {
                    tmp = Math.sqrt((i - primes.get(j)) / 2);
                    if (tmp == (int) tmp) {
                        done = false;
                        break;
                    }
                }
                if (done) {
                    System.out.println(i);
                }
            }
        }
    }
}
