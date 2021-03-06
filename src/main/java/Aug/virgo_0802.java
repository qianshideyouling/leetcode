package Aug;

import java.util.Arrays;

public class virgo_0802 {

    /*
        给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
        示例 1:
        输入: coins = [1, 2, 5], amount = 11
        输出: 3
        解释: 11 = 5 + 5 + 1
        链接：https://leetcode-cn.com/problems/coin-change
     */
    public int coinChange(int[] coins, int amount) {
        int[] result = new int[amount+1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                int subChange = i - coin;
                if (subChange >= 0 && result[subChange] != Integer.MAX_VALUE) {
                    result[i] = Math.min(result[i], result[subChange] + 1);
                }
            }
        }
        return result[amount] == Integer.MAX_VALUE ? -1 : result[amount];
    }
}
