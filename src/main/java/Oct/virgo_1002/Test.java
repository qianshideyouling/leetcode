package Oct.virgo_1002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test {

    /*
        给定一个整数 n, 返回从 1 到 n 的字典顺序。
        例如，
        给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
        https://leetcode-cn.com/problems/lexicographical-numbers/
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        zidianxu(n, result, 0);
        return result;
    }

    private void zidianxu(int n, List<Integer> result, int tmp) {
        for (int i = 0; i <= 9; i++) {
            int item = tmp * 10 + i;
            if (0 < item && item <= n) {
                result.add(item);
                zidianxu(n, result, item);
            }
        }
    }

    /*
        实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
        如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
        必须原地修改，只允许使用额外常数空间。
        以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
        1,2,3 → 1,3,2
        3,2,1 → 1,2,3
        1,1,5 → 1,5,1

        链接：https://leetcode-cn.com/problems/next-permutation
     */
    public void nextPermutation(int[] nums) {
        int down = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                down = i;
                break;
            }
        }
        if (down == 0) {
            Arrays.sort(nums);
            return;
        }
        int big = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[down - 1]) {
                big = i;
                break;
            }
        }
        int tmp = nums[down - 1];
        nums[down - 1] = nums[big];
        nums[big] = tmp;
        Arrays.sort(nums, down, nums.length);
    }


    /* ?
    给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
    示例:
    输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    输出: [3,3,5,5,6,7]

    链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[0];
        int[] result = new int[nums.length - k + 1];
        int max = -1;
        int left = 0;
        int right = k - 1;
        for (; right < nums.length; right++, left++) {
            if (max < left) {
                max = left;
                for (int i = left; i <= right; i++) {
                    max = nums[i] >= nums[max] ? i : max;
                }
            } else {
                max = nums[right] >= nums[max] ? right : max;
            }
            result[left] = nums[max];
        }
        return result;
    }

    /*
    给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    说明：你不能倾斜容器，且 n 的值至少为 2。

    链接：https://leetcode-cn.com/problems/container-with-most-water
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                area = Math.max(area, height[left] * (right - left));
                left++;
            } else {
                area = Math.max(area, height[right] * (right - left));
                right--;
            }
        }
        return area;
    }

    /*
    第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
    每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
    返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
    输入：people = [1,2], limit = 3
    输出：1
    解释：1 艘船载 (1, 2)

    链接：https://leetcode-cn.com/problems/boats-to-save-people
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int sum = 0;
        int right = 0;
        int left = people.length - 1;
        while (right < left) {
            if (people[left] + people[right] <= limit) {
                right++;
            }
            left--;
            sum++;
        }
        if (right == left) {
            sum++;
        }
        return sum;
    }

    /* ?
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     */
    public int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? heights.length - 1 : stack.peek() - 1;
            stack.push(i);
        }
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            area = Math.max(area, (right[i] - left[i] + 1) * heights[i]);
        }
        return area;
    }
}
