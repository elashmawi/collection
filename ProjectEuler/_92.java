
public class _92 {

    public static void main(String[] args) {
        int result = 0, tmp;
        String s;
        for (int i = 2; i < 10000000; i++) {
            tmp = i;
            while (tmp != 1 && tmp != 89) {
                s = Integer.toString(tmp);
                tmp = 0;
                for (int j = 0; j < s.length(); j++) {
                    tmp += (s.charAt(j) - 48) * (s.charAt(j) - 48);
                }
            }
            if (tmp == 89) {
                result++;
            }
        }
        System.out.println(result);
    }
}
