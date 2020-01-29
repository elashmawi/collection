
import java.util.ArrayDeque;

public class _64 {

    static ArrayDeque<Long> sqrtContdFrcExp(long S) {
        ArrayDeque<Long> result = new ArrayDeque();
        long m = 0, d = 1, a0 = (long) Math.floor(Math.sqrt(S)), a = a0;
        while (a != 2 * a0) {
            m = d * a - m;
            d = (S - m * m) / d;
            a = (a0 + m) / d;
            result.add(a);
        }
        return result;
    }

    public static void main(String[] args) {
        int result = 0;
        for (int i = 2; i <= 10000; i++) {
            double tmp = Math.sqrt(i);
            if (tmp != (int) tmp && sqrtContdFrcExp(i).size() % 2 == 1) {
                result++;
            }
        }
        System.out.println(result);
    }
}
