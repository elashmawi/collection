
import java.util.*;

public class _87 {

    public static void main(String[] args) {
        int limit = 50000000;
        ArrayDeque<Integer> tmp = NumberTheory.primes(NumberTheory.sieve((int) Math.sqrt(limit - 25)));
        Integer[] primes = new Integer[tmp.size()];
        tmp.toArray(primes);
        TreeSet<Integer> result = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < primes.length; i++) {
            for (int j = 0; j < primes.length && Math.pow(primes[j], 3) < limit - Math.pow(primes[i], 2) - 16; j++) {
                for (int k = 0; k < primes.length && Math.pow(primes[k], 4) < limit - Math.pow(primes[i], 2) - Math.pow(primes[j], 3); k++) {
                    result.add((int) (Math.pow(primes[i], 2) + Math.pow(primes[j], 3) + Math.pow(primes[k], 4)));
                }
            }
        }
        System.out.println(result.size());
    }
}
