package virgo_0210;

import java.util.Arrays;

public class Test {
    //最長連續上升子序列

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int pre = nums[0];
        int[] length = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > pre) {
                length[i] = length[i - 1] + 1;
            } else {
                length[i] = 1;
            }
            pre = nums[i];
        }
        Arrays.sort(length);
        return length[length.length - 1]+1;
    }
    //去除重複字符
}
