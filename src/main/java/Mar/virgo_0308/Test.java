package Mar.virgo_0308;

import java.util.*;

public class Test {

    /*
        给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
        注意:
        可以认为区间的终点总是大于它的起点。
        区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
        示例 1:

        输入: [ [1,2], [2,3], [3,4], [1,3] ]
        输出: 1
        解释: 移除 [1,3] 后，剩下的区间没有重叠。

        链接：https://leetcode-cn.com/problems/non-overlapping-intervals
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, this::compareArea);
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = stack.peek();
            int[] current = intervals[i];
            if (pre[1] <= current[0]) {
                stack.push(current);
            }
            if (pre[1] >= current[1]) {
                stack.pop();
                stack.push(current);
            }
        }
        return intervals.length - stack.size();
    }

    private int compareArea(int[] area1, int[] area2) {
        int rst = area1[0] - area2[0];
        return rst != 0 ? rst : area1[1] - area2[1];
    }

    /*
        给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
        示例：

        输入：[1,8,6,2,5,4,8,3,7]
        输出：49

        链接：https://leetcode-cn.com/problems/container-with-most-water
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] >= height[i]) {
                    max = Math.max(max, (i - j) * height[i]);
                } else {
                    max = Math.max(max, (i - j) * height[j]);
                }
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    /*
        给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
    找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

    示例:
    X X X X
    X O O X
    X X O X
    X O X X
    运行你的函数后，矩阵变为：

    X X X X
    X X X X
    X X X X
    X O X X

    链接：https://leetcode-cn.com/problems/surrounded-regions
     */
    public void solve(char[][] board) {
        if (board.length == 0) return;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (0 < i && i < board.length - 1 && 0 < j && j < board[0].length - 1) {
                    continue;
                }
                if (board[i][j] == 'O') {
                    Queue<int[]> queue = new LinkedList<>();
                    int[] position = {i, j};
                    queue.add(position);
                    board[i][j] = 'A';
                    while (!queue.isEmpty()) {
                        int[] pos = queue.poll();
                        for (int[] direction : directions) {
                            int nextI = pos[0] + direction[0];
                            int nextJ = pos[1] + direction[1];
                            if (0 < nextI && board.length - 1 > nextI && 0 < nextJ && board[0].length - 1 > nextJ && board[nextI][nextJ] == 'O') {
                                int[] next = {nextI, nextJ};
                                queue.add(next);
                                board[nextI][nextJ] = 'A';
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    给定一个二叉树，原地将它展开为链表。

    例如，给定二叉树

        1
       / \
      2   5
     / \   \
    3   4   6
    将其展开为：

    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6

    链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     */
    Queue<TreeNode> queue = new LinkedList<>();

    public void flatten(TreeNode root) {
        if (null == root) return;
        access(root);
        TreeNode node = queue.poll();
        while (!queue.isEmpty()) {
            node.right = queue.poll();
            node.left = null;
            node = node.right;
        }
    }

    private void access(TreeNode root) {
        if (null != root) {
            queue.add(root);
            access(root.left);
            access(root.right);
        }
    }

    public int coinChange(int[] coins, int amount) {
        List<List<Long>> result = new ArrayList<>();
        backtrace(coins, result, new Stack<>(), amount);
        result.sort(Comparator.comparingInt(List::size));
        return result.size() == 0 ? -1 : result.get(0).size();
    }

    private void backtrace(int[] coins, List<List<Long>> result, Stack<Long> temp, long amount) {
        if (0 == amount) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (amount < 0) {
            return;
        }
        for (int coin : coins) {
            temp.push((long) coin);
            backtrace(coins, result, temp, amount - coin);
            temp.pop();
        }
    }
}
