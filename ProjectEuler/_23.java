
import java.util.*;

public class _23 {

    static int sumPropDiv(int n) {
        int result = 1;
        double sqrt = Math.sqrt(n);
        if (sqrt == (int) sqrt) {
            result += sqrt;
        }
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0) {
                result += i + n / i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        boolean[] abundantTmp = new boolean[28124];
        for (int i = 2; i < abundantTmp.length; i++) {
            if (sumPropDiv(i) > i) {
                abundantTmp[i] = true;
            }
        }
        ArrayList<Integer> abundant = new ArrayList();
        for (int i = 0; i < abundantTmp.length; i++) {
            if (abundantTmp[i]) {
                abundant.add(i);
            }
        }
        TreeSet<Integer> sumOfTwoAbundantsTmp = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i : abundant) {
            for (int j : abundant) {
                if (i + j <= 28123) {
                    sumOfTwoAbundantsTmp.add(i + j);
                }
            }
        }
        int result = 395465626;
        for (int i : sumOfTwoAbundantsTmp) {
            result -= i;
        }
        System.out.println(result);
    }
}
