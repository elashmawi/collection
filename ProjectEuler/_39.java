
public class _39 {

    public static void main(String[] args) {
        int result = 1000, max = 1, tmp;
        for (int p = 1; p <= 10000; p++) {
            tmp = 0;
            for (int a = 1; a <= p / 3; a++) {
                for (int b = a; b <= (p - a) / 2; b++) {
                    if (a * a + b * b == (p - a - b) * (p - a - b)) {
                        tmp++;
                    }
                }
            }
            if (tmp > max) {
                result = p;
                max = tmp;
            }
        }
        System.out.println(result);
    }
}
