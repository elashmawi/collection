
import java.util.ArrayList;

public class _24 {

    static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int index = 999999, factorial;
        ArrayList<Integer> digits = new ArrayList();
        for (int i = 0; i < 10; i++) {
            digits.add(i);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            factorial = factorial(9 - i);
            result.append((char) (48 + digits.remove(index / factorial)));
            index -= (int) (index / factorial) * factorial;
        }
        System.out.println(result);
    }
}
