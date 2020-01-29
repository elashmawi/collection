
public class _120 {

    public static void main(String[] args) {
        int result = 0;
        for (int a = 3; a <= 1000; a++) {
            int r = 0;
            for (int n = 1; n < a; n++) {
                r = (int) Math.max(r, (2 * n * a) % (a * a));
            }
            result += r;
        }
        System.out.println(result);
    }
}
