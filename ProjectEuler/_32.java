
import java.util.*;

public class _32 {

    public static void main(String[] args) {
        TreeSet<Integer> products = new TreeSet();
        char[] concat;
        for (int i = 1; i < 1000000; i++) {
            for (int j = i + 1; (int) (Math.log10(i)) + (int) (Math.log10(j)) + (int) (Math.log10(i * j)) + 3 <= 9; j++) {
                concat = (Integer.toString(i) + Integer.toString(j) + Integer.toString(i * j)).toCharArray();
                Arrays.sort(concat);
                if ("123456789".equals(new String(concat))) {
                    products.add(i * j);
                }
            }
        }
        int result = 0;
        Set<Integer> s = products.descendingSet();
        for (int i : s) {
            result += i;
        }
        System.out.println(result);
    }
}
