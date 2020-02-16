package virgo_0131;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] memo = new int[nums.length];
        Arrays.fill(memo, Integer.MAX_VALUE);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = memo[i] != Integer.MAX_VALUE ? memo[i] : maxLength(nums, i, memo);
            if (tmp > result) {
                result = tmp;
            }
        }
        return result;
    }

    private int maxLength(int[] nums, int start, int[] memo) {
        int base = nums[start];
        List<Integer> next = new ArrayList<>();
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > base) {
                next.add(i);
            }
        }
        if (next.isEmpty()) {
            return 1;
        } else {
            int result = 0;
            for (int item : next) {
                int tmp = memo[item] != Integer.MAX_VALUE ? memo[item] : maxLength(nums, item, memo);
                memo[item] = tmp;
                if (tmp > result) {
                    result = tmp;
                }
            }
            return result + 1;
        }
    }

}
