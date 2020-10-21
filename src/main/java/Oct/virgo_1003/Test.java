package Oct.virgo_1003;

import java.util.*;

public class Test {
    public int search(int[] nums, int target) {
        int position = -1;
        if (nums.length == 0) {
            return position;
        }
        int left = 0, right = nums.length - 1, middle = (left + right) / 2;
        while (left < middle) {
            if (nums[middle] == target) {
                position = middle;
                break;
            } else if (nums[middle] < target) {
                left = middle;
            } else {
                right = middle;
            }
            middle = (left + right) / 2;
        }
        position = nums[left] == target ? left : position;
        position = nums[right] == target ? right : position;
        return position;
    }

    /*
        假设按照升序排序的数组在预先未知的某个点上进行了旋转。
        ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
        搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
        你可以假设数组中不存在重复的元素。
        链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     */
    public int search1(int[] nums, int target) {
        int position = -1;
        if (nums.length == 0) {
            return position;
        }
        int left = 0, right = nums.length - 1, middle = (left + right) / 2;
        while (left < middle) {
            if (nums[middle] == target) {
                position = middle;
                break;
            }
            if (nums[left] < nums[middle]) {
                if (nums[left] <= target && target <= nums[middle]) {
                    right = middle;
                } else {
                    left = middle;
                }
            } else {
                if (nums[middle] <= target && target <= nums[right]) {
                    left = middle;
                } else {
                    right = middle;
                }
            }
            middle = (left + right) / 2;
        }
        position = nums[left] == target ? left : position;
        position = nums[right] == target ? right : position;
        return position;
    }

    /*
    请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
    链接：https://leetcode-cn.com/problems/daily-temperatures
     */
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int item = stack.pop();
                result[item] = i - item;
            }
            stack.push(i);
        }
        return result;
    }

    /*
    给定一个无序的整数数组，找到其中最长上升子序列的长度。
    示例:
    输入: [10,9,2,5,3,7,101,18]
    输出: 4
    解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

    链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    /*
    给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
    示例 1:

    输入: [[0, 30],[5, 10],[15, 20]]
    输出: 2

    链接：https://leetcode-cn.com/problems/meeting-rooms-ii
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            ends[i] = intervals[i][1];
        }
        Arrays.sort(ends);
        int max = 0;
        int[] array = new int[ends[ends.length - 1]];
        for (int[] interval : intervals) {
            for (int j = interval[0]; j < interval[1]; j++) {
                array[j]++;
                max = Math.max(max, array[j]);
            }
        }
        return max;
    }

    /*
    现在你总共有 n 门课需要选，记为 0 到 n-1。
    在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
    给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
    可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
    示例 1:
    输入: 2, [[1,0]]
    输出: [0,1]

    链接：https://leetcode-cn.com/problems/course-schedule-ii
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        for (int[] pre : prerequisites) {
            in[pre[0]]++;
        }
        int[] result = new int[numCourses];
        int position = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int item = queue.poll();
            result[position++] = item;
            for (int[] pre : prerequisites) {
                if (item == pre[1]) {
                    in[pre[0]]--;
                    if (in[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }
        return position == numCourses ? result : new int[0];
    }

    public int maxSubArray(int[] nums) {
        int[] sums = new int[nums.length];
        int sum = 0;
        int max = sum;
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max) {
                max = sum;
                pos = i;
            }
            sums[i] = sum;
        }
        Arrays.sort(sums, 0, pos);
        return max - sums[0];
    }

    /*
    给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
    示例 1 :
    输入:nums = [1,1,1], k = 2
    输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。

    链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int result = 0;
        for (int num : nums) {
            sum += num;
            if (map.get(sum - k) != null) {
                result = result + map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public List<List<Integer>> twoSum(List<Integer> nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = start + 1; i < nums.size(); i++) {
            if (map.containsKey(target - nums.get(i))) {
                List<Integer> list = Arrays.asList(nums.get(i),
                        nums.get(map.get(target - nums.get(i))));
                result.add(list);
            }
            map.put(nums.get(i), i);
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (set.add(list.get(i))) {
                List<List<Integer>> items = twoSum(list, -list.get(i), i);
                if (items.size() > 0) {
                    for (List<Integer> item : items) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(list.get(i));
                        tmp.addAll(item);
                        result.add(tmp);
                    }
                }
            }
        }
        return result;
    }
}
