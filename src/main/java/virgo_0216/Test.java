package virgo_0216;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    // 零钱兑换
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < coins.length && coins[i] <= amount; i++) {
            dp[coins[i]] = 1;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i - j] != Integer.MAX_VALUE && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // 总和为num的平方数个数最小值
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace(n, new ArrayList<>(), result, 2);
        return result;
    }

    private void backtrace(int n, ArrayList<Integer> temp, List<List<Integer>> result, int start) {
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                temp.add(n / i);
                result.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
                backtrace(n / i, temp, result, i);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean exist(char[][] board, String word) {
        int[][] help = new int[board.length][];
        for (int k = 0; k < help.length; k++) {
            help[k] = new int[board[0].length];
            Arrays.fill(help[k], 1);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int[] start = {i, j};
                if (findWord(board, help, word, start, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, int[][] help, String word, int[] start, int position) {
        if (position == word.length() - 1 && board[start[0]][start[1]] == word.charAt(position)) {
            return true;
        }
        if (word.charAt(position) == board[start[0]][start[1]]) {
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] direction : directions) {
                int[] next = {start[0] + direction[0], start[1] + direction[1]};
                if (isInArea(board, next) && help[next[0]][next[1]] == 1) {
                    help[next[0]][next[1]] = 0;
                    if (findWord(board, help, word, next, position + 1))
                        return true;
                    help[next[0]][next[1]] = 1;
                }
            }
        }
        return false;
    }

    private boolean isInArea(char[][] board, int[] next) {
        return next[0] < board.length && next[1] < board[0].length && next[0] >= 0 && next[1] >= 0;
    }
}
