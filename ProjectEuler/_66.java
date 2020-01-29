
import java.math.BigInteger;
import java.util.ArrayList;

public class _66 {

    static ArrayList<Long> sqrtContdFrcExp(long S) {
        ArrayList<Long> result = new ArrayList();
        long m = 0, d = 1, a0 = (long) Math.floor(Math.sqrt(S)), a = a0;
        while (a != 2 * a0) {
            m = d * a - m;
            d = (S - m * m) / d;
            a = (a0 + m) / d;
            result.add(a);
        }
        return result;
    }

    public static void main(String[] args) {
        long result = 13;
        BigInteger x = new BigInteger("649"), a, h2, h1, h, k2, k1, k;
        double tmp;
        ArrayList<Long> tmp2;
        for (int i = 8; i <= 1000; i++) {
            tmp = Math.sqrt(i);
            a = new BigInteger(Integer.toString((int) Math.floor(tmp)));
            if (tmp != (int) tmp) {
                h2 = BigInteger.ZERO;
                h1 = BigInteger.ONE;
                k2 = BigInteger.ONE;
                k1 = BigInteger.ZERO;
                h = a.multiply(h1).add(h2);
                k = a.multiply(k1).add(k2);
                tmp2 = sqrtContdFrcExp(i);
                for (int j = 0; !h.pow(2).subtract(k.pow(2).multiply(new BigInteger(Integer.toString(i)))).equals(BigInteger.ONE); j++) {
                    a = new BigInteger(Long.toString(tmp2.get(j % tmp2.size())));
                    h2 = h1;
                    h1 = h;
                    k2 = k1;
                    k1 = k;
                    h = a.multiply(h1).add(h2);
                    k = a.multiply(k1).add(k2);
                }
                if (h.compareTo(x) == 1) {
                    x = h;
                    result = i;
                }
            }
        }
        System.out.println(result);
    }
}
