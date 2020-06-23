
import java.util.*;

public class NumberTheory {

    static long mod(long a, long b) {
        return (a % b + b) % b;
    }

    //toDo: implement extended euclidean
    static long gcd(long a, long b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    static boolean[] sieve(int n) {
        boolean[] notPrime = new boolean[Math.max(2, n + 1)];
        notPrime[0] = true;
        notPrime[1] = true;
        for (int i = 2; i < Math.sqrt(notPrime.length + 1); i++) {
            if (!notPrime[i]) {
                for (int j = i * i; j < notPrime.length; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        return notPrime;
    }

    static int[] primes(boolean[] notPrime) {
        ArrayDeque<Integer> tmp = new ArrayDeque();
        for (int i = 2; i < notPrime.length; i++) {
            if (!notPrime[i]) {
                tmp.add(i);
            }
        }
        int[] primes = new int[tmp.size()];
        for (int i = 0; i < primes.length; i++) {
            primes[i] = tmp.poll();
        }
        return primes;
    }

    static long[][] trialDivision(long n) {
        TreeMap<Long, Integer> tmp1 = new TreeMap(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) Math.signum(o1 - o2);
            }
        });
        while (n % 2 == 0) {
            tmp1.put(2l, tmp1.getOrDefault(2l, 0) + 1);
            n /= 2;
        }
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                tmp1.put(i, tmp1.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        if (n != 1) {
            tmp1.put(n, 1);
        }
        Map.Entry<Long, Integer>[] itr = tmp1.entrySet().toArray(new Map.Entry[tmp1.size()]);
        long[][] factorization = new long[itr.length][2];
        for (int i = 0; i < itr.length; i++) {
            factorization[i][0] = itr[i].getKey();
            factorization[i][1] = itr[i].getValue();
        }
        return factorization;
    }

    static long[] divisors(long[][] factorization, int exponent) {
        int length = 1;
        for (long[] i : factorization) {
            length *= i[1] / exponent + 1;
        }
        long[] result = new long[length];
        result[0] = 1;
        int tmp1 = 1;
        for (long[] i : factorization) {
            int tmp2 = tmp1;
            for (int j = exponent; j <= i[1]; j += exponent) {
                for (int k = 0; k < tmp2; k++) {
                    result[tmp1] = result[k] * (long) Math.pow(i[0], j);
                    tmp1++;
                }
            }
        }
        return result;
    }

    static int eulerPhi(int n, int[] primes) {
        int result = n;
        for (int i : primes) {
            if (n % i == 0) {
                result /= i;
                result *= i - 1;
                while (n % i == 0) {
                    n /= i;
                }
            }
            if (n == 1) {
                break;
            }
        }
        return result;
    }

    static int legendre(long n, int p) {
        int result = 0;
        long tmp = p;
        for (; tmp <= n; tmp *= p) {
            result += n / tmp;
        }
        return result;
    }
}
