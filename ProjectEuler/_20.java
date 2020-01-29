
import java.math.BigInteger;

public class _20 {

    public static void main(String[] args) {
        BigInteger n = BigInteger.ONE;
        for (int i = 100; i > 1; i--) {
            n = n.multiply(new BigInteger(Integer.toString(i)));
        }
        String s = n.toString();
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += s.charAt(i) - 48;
        }
        System.out.println(result);
    }
}
