
import java.util.*;

public class _650 {

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

    public static void main(String[] args) {
        int n = 20000;
        long modulus = 1000000007;
        //long modulus = 1111111111111111111l;
        //long modulus = 2305843009213693951l;

        //preprocessing
        long[][][] f = new long[n + 1][][];//factorizations
        for (int i = 2; i <= n; i++) {
            f[i] = NumberTheory.trialDivision(i);
        }
        long[][][] ff = new long[n + 1][][];//factorial factorizations
        TreeMap<Long, Long> tmp1 = new TreeMap(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) Math.signum(o1 - o2);
            }
        });
        for (int i = 2; i <= n; i++) {
            for (long[] j : f[i]) {
                tmp1.put(j[0], tmp1.getOrDefault(j[0], 0l) + j[1]);
            }
            Map.Entry<Long, Long>[] itr = tmp1.entrySet().toArray(new Map.Entry[tmp1.size()]);
            ff[i] = new long[itr.length][2];
            for (int j = 0; j < itr.length; j++) {
                ff[i][j][0] = itr[j].getKey();
                ff[i][j][1] = itr[j].getValue();
            }
        }

        long result = 1, d = 1;
        tmp1 = new TreeMap(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) Math.signum(o1 - o2);
            }
        });
        for (int i = 2; i <= n; i++) {
            for (long[] j : f[i]) {
                long tmp2 = tmp1.getOrDefault(j[0], j[0] % modulus);
                d = (d * inverse(NumberTheory.mod(tmp2 - 1, modulus), modulus)) % modulus;
                for (int k = 0; k < i * j[1]; k++) {
                    tmp2 = (tmp2 * j[0]) % modulus;
                }
                d = (d * NumberTheory.mod(tmp2 - 1, modulus)) % modulus;
                tmp1.put(j[0], tmp2);
            }
            for (long[] j : ff[i]) {
                long tmp2 = tmp1.get(j[0]);
                d = (d * inverse(NumberTheory.mod(tmp2 - 1, modulus), modulus)) % modulus;
                long pInv = inverse(j[0], modulus);
                for (int k = 0; k < j[1]; k++) {
                    tmp2 = (tmp2 * pInv) % modulus;
                }
                d = (d * NumberTheory.mod(tmp2 - 1, modulus)) % modulus;
                tmp1.put(j[0], tmp2);
                tmp1.remove(j[0], j[0] % modulus);
            }
            result = (result + d) % modulus;
        }
        System.out.println(result);
    }
}
