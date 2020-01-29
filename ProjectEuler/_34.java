
public class _34 {

    public static void main(String[] args) {
        int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        int result = 0, tmp2;
        String tmp1;
        for (int i = 3; i < 9999999; i++) {
            tmp1 = Integer.toString(i);
            tmp2 = 0;
            for (int j = 0; j < tmp1.length(); j++) {
                tmp2 += factorial[tmp1.charAt(j) - 48];
            }
            if (tmp2 == i) {
                result += i;
            }
        }
        System.out.println(result);
    }
}
