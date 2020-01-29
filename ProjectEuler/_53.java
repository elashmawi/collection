
import java.math.BigInteger;

public class _53 {

    static BigInteger binCoeff(int n, int k) {
        BigInteger result = BigInteger.ONE;
        int uL = Math.min(k, n - k);
        for (int i = 1; i <= uL; i++) {
            result = result.multiply(new BigInteger(Integer.toString(n + 1 - i)));
            result = result.divide(new BigInteger(Integer.toString(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        int result = 0;
        BigInteger million = BigInteger.TEN.pow(6);
        for (int n = 1; n <= 100; n++) {
            for (int r = 0; r <= n; r++) {
                if (binCoeff(n, r).compareTo(million) == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
