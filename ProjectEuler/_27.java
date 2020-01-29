
import java.util.ArrayList;

public class _27 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[(int) Math.pow(2, 14)];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList();
        for (int i = 2; i < 1000; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        int[] result = {1, 41};
        int max = 39, counter, n, tmp;
        boolean done;
        for (int b : primes) {
            for (int a = (2 - b) / 39 - 39; a <= 1000; a++) {
                counter = 0;
                n = 1;
                done = false;
                while (!done) {
                    tmp = n * n + a * n + b;
                    if (tmp > 1 && !sieve[tmp]) {
                        counter++;
                        n++;
                    } else {
                        done = true;
                    }
                }
                if (max < counter) {
                    result = new int[]{a, b};
                    max = counter;
                }
            }
        }
        System.out.println(result[0] * result[1]);
    }
}
