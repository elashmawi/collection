
public class _15 {

    static long binCoeff(long n, long k) {
        long result = 1;
        long uL = Math.min(k, n - k);
        for (int i = 1; i <= uL; i++) {
            result *= (n + 1 - i);
            result /= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(binCoeff(40, 20));
    }
}
