
import java.math.BigInteger;
import java.util.*;

public class _29 {

    public static void main(String[] args) {
        TreeSet<BigInteger> set = new TreeSet(new Comparator<BigInteger>() {
            @Override
            public int compare(BigInteger o1, BigInteger o2) {
                return o1.compareTo(o2);
            }
        });
        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                set.add(new BigInteger(Integer.toString(a)).pow(b));
            }
        }
        System.out.println(set.size());
    }
}
