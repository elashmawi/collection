
public class _4 {

    static boolean palindrome(int n) {
        String s = Integer.toString(n);
        double l = (double) s.length() / 2;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int result = 10000;
        for (int i = 999; i > 99; i--) {
            for (int j = i; j > 99; j--) {
                if (i * j > result && palindrome(i * j)) {
                    result = i * j;
                }
            }
        }
        System.out.println(result);
    }
}
