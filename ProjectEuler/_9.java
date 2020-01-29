
public class _9 {

    public static void main(String[] args) {
        for (int a = 1; a < 333; a++) {
            for (int b = a + 1; b < 500; b++) {
                if (a * a + b * b == (1000 - a - b) * (1000 - a - b)) {
                    System.out.println(a * b * (1000 - a - b));
                }
            }
        }
    }
}
