package virgo_0129;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lwg on 2020/1/29.
 */
public class Test {

    public boolean canJump(int[] nums) {
        int[] steps = new int[nums.length];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != 0) {
                for (int j = i + 1; j <= i + nums[i]; j++) {
                    if (j < nums.length && steps[j] == Integer.MAX_VALUE && steps[i] != Integer.MAX_VALUE) {
                        steps[j] = steps[i] + 1;
                    }
                }
            }
        }
        return nums.length == 1 || (nums[0] > 0 && steps[steps.length - 1] != Integer.MAX_VALUE);
    }


    public int minPathSum(int[][] grid) {
        int gridRows = grid.length;
        int[][] result = new int[gridRows][];
        int gridColumns = grid[0].length;
        for (int i = 0; i < gridRows; i++) {
            int[] tmp = new int[gridColumns];
            Arrays.fill(tmp, Integer.MAX_VALUE);
            result[i] = tmp;
        }
        int sum = 0;
        for (int i = 0; i < gridRows; i++) {
            sum += grid[i][0];
            result[i][0] = sum;
        }
        sum = 0;
        for (int i = 0; i < gridColumns; i++) {
            sum += grid[0][i];
            result[0][i] = sum;
        }
        for (int i = 1; i < gridRows; i++) {
            for (int j = 1; j < gridColumns; j++) {
                result[i][j] = Math.min(result[i][j - 1], result[i - 1][j]) + grid[i][j];
            }
        }
        return result[gridRows - 1][gridColumns - 1];
    }

    public List<List<Integer>> combine(int n, int k) {
        if (1== k) {
            List<List<Integer>> rst = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                List<Integer> item = new ArrayList<>();
                item.add(i);
                rst.add(item);
            }
            return rst;
        }

        if (n== k) {
            List<List<Integer>> rst = new ArrayList<>();
            List<Integer> item = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                item.add(i);
            }
            rst.add(item);
            return rst;
        }
        List<List<Integer>> lists = combine(n-1, k);
        List<List<Integer>> subs = combine(n-1, k-1);
        for (List<Integer> list : subs) {
            list.add(n);
            lists.add(list);
        }
        return lists;
    }
}


