
import java.math.BigInteger;

public class _55 {

    static boolean palindrome(BigInteger n) {
        String s = n.toString();
        double l = (double) s.length() / 2;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int lychrel = 0;
        BigInteger tmp;
        for (int i = 0; i < 10000; i++) {
            tmp = new BigInteger(i + "");
            for (int j = 0; j < 50; j++) {
                tmp = tmp.add(new BigInteger(new StringBuilder(tmp.toString()).reverse().toString()));
                if (palindrome(tmp)) {
                    lychrel++;
                    break;
                }
            }
        }
        System.out.println(10000 - lychrel);
    }
}
