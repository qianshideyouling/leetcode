package April.virgo_0504;

import java.util.Arrays;

public class Test {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public int jump(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= i + nums[i] && j < length; j++) {
                if (result[i] + 1 < result[j]) {
                    result[j] = result[i] + 1;
                }
            }
        }
        return result[length - 1];
    }

}
