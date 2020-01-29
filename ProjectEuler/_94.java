
public class _94 {

    public static void main(String[] args) {
        long result = 0, a = 5, h = -4, tmp;
        while (a <= 333333333) {
            if (h % 2 == 0 || (a + 1) % 2 == 0) {
                result += 3 * a + 1;
                System.out.println(a);
            }
            tmp = a;
            a = 7 * a - 8 * h - 2;
            h = 7 * h - 6 * tmp + 2;
        }
        a = 17;
        h = -15;
        while (a <= 333333333) {
            if (h % 2 == 0 || (a - 1) % 2 == 0) {
                result += 3 * a - 1;
                System.out.println(a);
            }
            tmp = a;
            a = 7 * a - 8 * h + 2;
            h = 7 * h - 6 * tmp - 2;
        }
        System.out.println(result);
    }
}
