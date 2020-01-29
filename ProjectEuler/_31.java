
public class _31 {

    public static void main(String[] args) {
        int n = 200;
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200}, result = new int[n + 1];
        result[0] = 1;
        for (int j = 0; j < coins.length; j++) {
            for (int i = coins[j]; i < result.length; i++) {
                result[i] += result[i - coins[j]];
            }
        }
        System.out.println(result[n]);
    }
}
