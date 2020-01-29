
import java.math.BigInteger;

public class _97 {

    public static void main(String[] args) {
        String s = new BigInteger("2").pow(7830457).multiply(new BigInteger("28433")).add(BigInteger.ONE).toString();
        System.out.println(s.substring(s.length() - 10, s.length()));
    }
}
