package Feb.virgo_0209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test {

    public int LIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        Arrays.sort(dp);
        return dp[dp.length - 1];
    }

    public int LIS_2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = nums.length-1; i >= 0; i--) {
            for (int j = nums.length-1; j >= i; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        Arrays.sort(dp);
        return dp[dp.length-1];
    }

    public int findNumberOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        List<List<Integer>> result = new ArrayList<>();
        getResult(list, new Stack<>(), result, 0);
        return result.size();
    }

    private void getResult(List<Integer> nums, Stack<Integer> stack, List<List<Integer>> result, int start) {
        if (start == nums.size()) {
            result.add(new ArrayList<>(stack));
        } else {
            for (int i = start; i < nums.size(); i++) {
                boolean add = stack.empty() || nums.get(i) > stack.peek();
                if (add) {
                    stack.push(nums.get(i));
                }
                getResult(nums, stack, result, i + 1);
                if (add) {
                    stack.pop();
                }
            }
        }
    }
}
