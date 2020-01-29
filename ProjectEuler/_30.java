
public class _30 {

    public static void main(String[] args) {
        String s;
        int result = 0, tmp;
        for (int i = 2; i < 1000000; i++) {
            s = Integer.toString(i);
            tmp = 0;
            for (int j = 0; j < s.length(); j++) {
                tmp += Math.pow(s.charAt(j) - 48, 5);
            }
            if (tmp == i) {
                result += i;
            }
        }
        System.out.println(result);
    }
}
