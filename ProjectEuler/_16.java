
import java.math.BigInteger;

public class _16 {

    public static void main(String[] args) {
        String s = new BigInteger("2").pow(1000).toString();
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += s.charAt(i) - 48;
        }
        System.out.println(result);
    }
}
