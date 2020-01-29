
public class _112 {

    static boolean increasing(StringBuilder sb) {
        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.charAt(i) > sb.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        double counter = 0;
        for (int i = 100; counter / (i - 1) < 0.99; i++) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            if (!increasing(sb) && !increasing(sb.reverse())) {
                counter++;
            }
        }
        System.out.println(counter/0.99);
    }
}
