
import java.util.*;

public class _43 {

    static int[] primes = {2, 3, 5, 7, 11, 13, 17};
    static TreeSet<Long> base = new TreeSet(new Comparator<Long>() {
        @Override
        public int compare(Long o1, Long o2) {
            return o1.compareTo(o2);
        }
    }), tmp;
    static StringBuilder acc = new StringBuilder(10);
    static ArrayDeque<Long> pandigitals = new ArrayDeque();

    static void generate(int i) {
        if (base.isEmpty()) {
            pandigitals.add(Long.parseLong(acc.toString()));
        } else {
            tmp = new TreeSet(base);
            for (long c : tmp) {
                if ((i != 0 || c != 0) && (i < 3 || Long.parseLong((acc.charAt(i - 2) + "" + acc.charAt(i - 1) + "" + c)) % primes[i - 3] == 0)) {
                    acc.append(c);
                    base.remove(c);
                    generate(i + 1);
                    base.add(c);
                    acc.deleteCharAt(acc.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (long i = 0; i < 10; i++) {
            base.add(i);
        }
        generate(0);
        long result = 0;
        for (long i : pandigitals) {
            result += i;
        }
        System.out.println(result);
    }
}
