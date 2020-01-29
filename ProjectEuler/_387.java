
import java.math.BigInteger;

public class _387 {

    static long f(long head, long limit, int certainty) {
        if (10 * head + 1 > limit) {
            return 0;
        }
        int digitSum = 0;
        long tmp = head;
        for (int i = (int) Math.log10(head); i >= 0; i--) {
            digitSum += tmp / Math.pow(10, i);
            tmp %= Math.pow(10, i);
        }
        long result = 0;
        if (new BigInteger(Long.toString(head / digitSum)).isProbablePrime(certainty)) {
            for (int i = 1; i < 10; i += 2) {
                tmp = 10 * head + i;
                if (i != 5 && new BigInteger(Long.toString(tmp)).isProbablePrime(certainty)) {
                    result += tmp;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            tmp = 10 * head + i;
            if (tmp % (digitSum + i) == 0) {
                result += f(tmp, limit, certainty);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long limit = (long) Math.pow(10, 14) - 1, result = 0;
        int certainty = Integer.MAX_VALUE;
        for (int i = 1; i < 10; i++) {
            result += f(i, limit, certainty);
        }
        System.out.println(result);
    }
}
