
import java.util.Arrays;

public class _104 {

    static char[] digits = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    static boolean pandigital(long n) {
        char[] tmp = Long.toString(n).toCharArray();
        if (tmp.length < 9) {
            return false;
        }
        Arrays.sort(tmp);
        return Arrays.equals(tmp, digits);
    }

    public static void main(String[] args) {
        double log10phi = Math.log10((Math.sqrt(5) + 1) / 2), log10sqrt5 = Math.log10(5) / 2, log10;
        long f = 2, f1 = 1, tmp, result = 3;
        while (true) {
            if (pandigital(f)) {
                log10 = result * log10phi - log10sqrt5;
                if (pandigital((long) Math.pow(10, log10 - Math.floor(log10) + 8))) {
                    break;
                }
            }
            tmp = f;
            f = (f + f1) % 10000000000L;
            f1 = tmp;
            result++;
        }System.out.println(result);
    }
}
