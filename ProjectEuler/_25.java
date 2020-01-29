
import java.math.BigInteger;

public class _25 {

    public static void main(String[] args) {
        BigInteger fib1 = BigInteger.ONE, fib2 = BigInteger.ONE, tmp;
        int counter = 1;
        while (fib1.toString().length() < 1000) {
            tmp = fib2;
            fib2 = fib2.add(fib1);
            fib1 = tmp;
            counter++;
        }
        System.out.println(counter);
    }
}
