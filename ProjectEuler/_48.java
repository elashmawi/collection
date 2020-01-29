
import java.math.BigInteger;

public class _48 {
    
    public static void main(String[] args) {
        BigInteger tmp = BigInteger.ZERO;
        for (int i = 1; i <= 1000; i++) {
            tmp = tmp.add(new BigInteger(Integer.toString(i)).pow(i));
        }
        String result = tmp.toString();
        System.out.println(result.substring(result.length() - 10, result.length()));
    }
}
