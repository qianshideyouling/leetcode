package Mar.virgo_0320;

import java.util.*;

public class Test {

    /*
    给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
    说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
    输入: [3,2,3]
    输出: [3]
    https://leetcode-cn.com/problems/majority-element-ii/
     */
    public List<Integer> majorityElement(int[] nums) {
        int times = nums.length / 3;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            int time = 1;
            if (null != map.get(num)) {
                time = map.get(num) + 1;
            }
            map.put(num, time);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > times) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /*
    这里有 n 个航班，它们分别从 1 到 n 进行编号。
    我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [i, j, k] 意味着我们在从 i 到 j 的每个航班上预订了 k 个座位。
    请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。

    输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
    输出：[10,55,45,25,25]
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        for (int[] booking : bookings) {
            for (int i = booking[0]; i <= booking[1]; i++) {
                result[i - 1] += booking[2];
            }
        }
        return result;
    }

    /*
    这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
    如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
    请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
    https://leetcode-cn.com/problems/count-servers-that-communicate/
     */
    public int countServers(int[][] grid) {
        int[] rows = new int[grid.length];
        int[] columns = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int sum = 0;
            for (int j = 0; j < grid[0].length; j++) {
                sum += grid[i][j];
            }
            rows[i] = sum;
        }

        for (int i = 0; i < grid[0].length; i++) {
            int sum = 0;
            for (int[] row : grid) {
                sum += row[i];
            }
            columns[i] = sum;
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && (rows[i] + columns[j]) > 2) {
                    result++;
                }
            }
        }
        return result;
    }

    /*
    给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
    返回 A 的任意排列，使其相对于 B 的优势最大化。

    输入：A = [2,7,11,15], B = [1,10,4,11]
    输出：[2,11,7,15]

    链接：https://leetcode-cn.com/problems/advantage-shuffle
     */
    public int[] advantageCount(int[] A, int[] B) {
        int[] result = new int[A.length];
        Arrays.fill(result, Integer.MIN_VALUE);
        Arrays.sort(A);
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[j] > B[i]) {
                    result[i] = A[j];
                    A[j] = Integer.MIN_VALUE;
                    break;
                }
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] == Integer.MIN_VALUE) {
                for (int j = 0; j < A.length; j++) {
                    if (A[j] != Integer.MIN_VALUE) {
                        result[i] = A[j];
                        A[j] = Integer.MIN_VALUE;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /*
    给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

    输入:
    A: [1,2,3,2,1]
    B: [3,2,1,4,7]
    输出: 3
    解释:
    长度最长的公共子数组是 [3, 2, 1]。

    链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
     */
    public int findLength(int[] A, int[] B) {
        int[][] result = new int[A.length + 1][B.length + 1];
        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    result[i][j] = result[i - 1][j - 1] + 1;
                    max = Math.max(max, result[i][j]);
                }
            }
        }
        return max;
    }

    /*
    给定一个非负整数数组，你最初位于数组的第一个位置。
    数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个位置。
    输入: [2,3,1,1,4]
    输出: true
    解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。

    链接：https://leetcode-cn.com/problems/jump-game
     */
    public boolean canJump(int[] nums) {
        if (nums.length < 2) return true;
        int[] result = new int[nums.length];
        int not = -1;
        Arrays.fill(result, not);
        result[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && result[i] != -1) {
                for (int j = i + 1; j <= Math.min(nums.length - 1, i + nums[i]); j++) {
                    if (result[j] == not) {
                        result[j] = result[i] + 1;
                    }
                }
            }
        }
        return result[result.length - 1] != not;
    }

    /*
    给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    说明：解集不能包含重复的子集。
    输入: nums = [1,2,3]
    输出:
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]

    链接：https://leetcode-cn.com/problems/subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrace(nums, 0, new Stack<>(), result);
        return result;
    }

    private void backTrace(int[] nums, int start, Stack<Integer> tmp, List<List<Integer>> result) {
        result.add(new ArrayList<>(tmp));
        if (start == nums.length) return;
        for (int i = start; i < nums.length; i++) {
            tmp.push(nums[i]);
            backTrace(nums, i + 1, tmp, result);
            tmp.pop();
        }
    }


    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existPath(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existPath(char[][] board, String word, int i, int j, int order) {
        if (order == word.length() - 1 && word.charAt(order) == board[i][j]) return true;
        if (board[i][j] != word.charAt(order)) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = '0';
        int next = order + 1;
        if ((j + 1 < board[0].length && existPath(board, word, i, j + 1, next))
                || (i + 1 < board.length && existPath(board, word, i + 1, j, next))
                || (j - 1 >= 0 && existPath(board, word, i, j - 1, next))
                || (i - 1 >= 0 && existPath(board, word, i - 1, j, next)))
            return true;
        else {
            board[i][j] = tmp;
            return false;
        }
    }


    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            for (int i = 0; i < n; i++) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> o));
        for (int n : nums) {
            queue.offer(n);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.size() == 0 ? 0 : queue.peek();
    }

}
