
public class _76 {

    public static void main(String[] args) {
        int n = 100;
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                long tmp = 0;
                for (int k = 1; k <= j; k++) {
                    tmp += dp[i - j][k];
                }
                dp[i][j] = tmp;
            }
        }
        long result = 0;
        for (int i = 1; i < n; i++) {
            result += dp[n][i];
        }
        System.out.println(result);
    }
}
