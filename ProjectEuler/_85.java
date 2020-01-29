
public class _85 {

    public static void main(String[] args) {
        int diff = Integer.MAX_VALUE, area = 0;
        for (int x = 1; x <= 2000; x++) {
            for (int y = 1; y <= 2000; y++) {
                int tmp = Math.abs(((y * y + y) / 2) * ((x * x + x) / 2) - 2000000);
                if (tmp < diff) {
                    diff = tmp;
                    area = x * y;
                }
            }
        }
        System.out.println(area);
    }
}
