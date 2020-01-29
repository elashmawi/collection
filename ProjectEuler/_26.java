
import java.util.*;

public class _26 {

    public static void main(String[] args) {
        LinkedList<String[]> cycles = new LinkedList();
        String decimal;
        boolean[] rest;
        int tmp;
        for (int i = 2; i < 1000; i++) {
            tmp = 1;
            decimal = "";
            rest = new boolean[1000];
            rest[1] = true;
            while (true) {
                tmp = (int) Math.pow(10, (int) (Math.log10(i) - Math.log10(tmp)) + 1) * tmp;
                if (tmp % i == 0) {
                    break;
                }
                decimal += tmp / i;
                tmp = tmp % i;
                if (rest[tmp]) {
                    int j;
                    for (j = 0; j < decimal.length(); j++) {
                        if (decimal.charAt(j) == (int) Math.pow(10, (int) (Math.log10(i) - Math.log10(tmp)) + 1) * tmp / i + 48) {
                            break;
                        }
                    }
                    cycles.add(new String[]{decimal.substring(j), Integer.toString(i)});
                    break;
                }
                rest[tmp] = true;
            }
        }
        Collections.sort(cycles, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o2[0].length() - o1[0].length();
            }
        });
        System.out.println(cycles.peek()[1]);
    }
}
