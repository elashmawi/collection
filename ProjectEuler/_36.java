
public class _36 {

    static boolean palindrome(int n, int radix) {
        String s = Integer.toString(n, radix);
        double l = (double) s.length() / 2;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int result = 0, tmp1;
        for (int i = 1; i < 9999; i++) {
            tmp1 = Integer.parseInt(Integer.toString(i) + "" + new StringBuilder(Integer.toString(i)).reverse());
            if (palindrome(tmp1, 2)) {
                result += tmp1;
            }
            tmp1 = Integer.parseInt(Integer.toString(i) + "" + new StringBuilder(Integer.toString(i)).reverse().substring(1));
            if (palindrome(tmp1, 2)) {
                result += tmp1;
            }
        }
        System.out.println(result);
    }
}
