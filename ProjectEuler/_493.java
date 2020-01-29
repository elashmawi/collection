
public class _493 {

    public static void main(String[] args) {
        double balls = 70, colors = 7, draws = 20;
        double result = 1;
        for (int i = 0; i < draws; i++) {
            result *= 1 - balls / ((balls - i) * colors);
        }
        result = (1 - result) * colors;
        System.out.println(result);
    }

}
