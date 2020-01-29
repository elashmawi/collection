
public class _19 {

    public static void main(String[] args) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int currentDay = 366, result = 0;
        for (int i = 1901; i <= 2000; i++) {
            for (int j = 0; j < 12; j++) {
                if (currentDay % 7 == 0) {
                    result++;
                }
                currentDay += days[j];
                if (j == 1 && ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0)) {
                    currentDay++;
                }
            }
        }
        System.out.println(result);
    }
}
