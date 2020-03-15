package Feb.virgo_0211;

public class Test {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3; i <= n+1; i++) {
            for (int j = 3; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i-j));
            }
        }
        return dp[n+1];
    }
}
