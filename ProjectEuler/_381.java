
import java.util.ArrayDeque;

public class _381 {

    //toDo: use extended euclidean from NumberTheory after implementation (modInverse of BigInt takes too much time)
    static long inverse(long a, long n) {
        long t = 0, newt = 1, r = n, newr = a;
        while (newr != 0) {
            long quotient = r / newr;
            long tmp = newt;
            newt = t - quotient * newt;
            t = tmp;
            tmp = newr;
            newr = r - quotient * newr;
            r = tmp;
        }
        if (t < 0) {
            t = t + n;
        }
        return t;
    }

    static long S(int p) {
        //https://en.wikipedia.org/wiki/Factorial#Number_theory
        long result = p - 1;
        long tmp = result;
        for (int i = 1; i < 5; i++) {
            tmp = (tmp * inverse(p - i, p)) % p;
            result = (result + tmp) % p;
        }
        return result;
    }

    public static void main(String[] args) {
        int limit = 100000000;
        ArrayDeque<Integer> primes = NumberTheory.primes(NumberTheory.sieve(limit - 1));
        long result = 0;
        while (primes.peek() < 5) {
            primes.poll();
        }
        for (int i : primes) {
            result += S(i);
        }
        System.out.println(result);
    }
}
