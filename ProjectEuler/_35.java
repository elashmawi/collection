
import java.util.*;

public class _35 {

    public static void main(String[] args) {
        boolean[] sieve = new boolean[(int) Math.pow(2, 20)];
        for (int i = 2; i < Math.sqrt(sieve.length + 1); i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < sieve.length; j += i) {
                    sieve[j] = true;
                }
            }
        }
        TreeSet<Integer> primes = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 2; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        int result = 0;
        String tmp;
        boolean circular;
        /*while (!primes.isEmpty()) {
            tmp = primes.first().toString();
            circular = true;
            for (int i = 0; i < tmp.length() && circular; i++) {
                circular = primes.remove(Integer.parseInt(tmp));
                tmp = (tmp + tmp.charAt(0)).substring(1);
            }
            if (circular) {
                result += tmp.length();
            } else if (Arrays.equals(tmp.toCharArray(), (tmp + tmp.charAt(0)).substring(1).toCharArray())) {
                result++;
            }
        }*/
        while (!primes.isEmpty()) {
            tmp = primes.pollFirst().toString();
            if (Arrays.equals(tmp.toCharArray(), (tmp + tmp.charAt(0)).substring(1).toCharArray())) {
                result++;
            } else {
                circular = true;
                for (int i = 0; i < tmp.length() - 1 && circular; i++) {
                    tmp = (tmp + tmp.charAt(0)).substring(1);
                    circular = primes.remove(Integer.parseInt(tmp));
                }
                if (circular) {
                    result += tmp.length();
                }
            }
        }
        System.out.println(result);
    }
}
