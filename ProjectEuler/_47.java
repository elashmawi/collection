
import java.util.*;

public class _47 {

    public static void main(String[] args) {
        int n = (int) Math.pow(2, 18);
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
        ArrayList<Integer[]> four = new ArrayList();
        ArrayDeque<Integer> tmp;
        int remainder;
        for (int i = 2; i < n; i++) {
            tmp = new ArrayDeque();
            tmp.add(i);
            remainder = i;
            for (int j = 0; remainder != 1 && tmp.size() < 7 && j < primes.size(); j++) {
                if (remainder % primes.get(j) == 0) {
                    tmp.add(1);
                    while (remainder % primes.get(j) == 0) {
                        remainder /= primes.get(j);
                        tmp.add(tmp.pollLast() * primes.get(j));
                    }
                }
            }
            if (tmp.size() == 5) {
                four.add(tmp.toArray(new Integer[5]));
            }
        }
        TreeSet<Integer> distinct;
        for (int i = 0; i < four.size() - 3; i++) {
            if (four.get(i)[0] + 1 == four.get(i + 1)[0] && four.get(i)[0] + 2 == four.get(i + 2)[0] && four.get(i)[0] + 3 == four.get(i + 3)[0]) {
                distinct = new TreeSet();
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        distinct.add(four.get(i + j)[k + 1]);
                    }
                }
                if (distinct.size() == 16) {
                    System.out.println(four.get(i)[0]);
                    break;
                }
            }
        }
    }
}
