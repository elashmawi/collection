
import java.math.BigInteger;

public class _56 {

    public static void main(String[] args) {
        long result = 0, tmp2;
        String tmp1;
        for (int a = 0; a < 100; a++) {
            for (int b = 0; b < 100; b++) {
                tmp1 = new BigInteger(Integer.toString(a)).pow(b).toString();
                tmp2 = 0;
                for (int i = 0; i < tmp1.length(); i++) {
                    tmp2 += tmp1.charAt(i) - 48;
                }
                result = Math.max(result, tmp2);
            }
        }
        System.out.println(result);
    }
}
