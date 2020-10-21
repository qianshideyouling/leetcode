package Oct.virgo_1011;


import java.util.*;

public class Test {
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int position = stack.pop();
                result[position] = i - position;
            }
            stack.push(i);
        }
        return result;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix.length < 1) return 0;
        int[][] rst = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            rst[0][j] = matrix[0][j] - '0';
            max = Math.max(max, rst[0][j]);
        }
        for (int i = 1; i < matrix.length; i++) {
            rst[i][0] = matrix[i][0] - '0';
            max = Math.max(max, rst[i][0]);
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    rst[i][j] = Math.min(Math.min(rst[i - 1][j], rst[i][j - 1]), rst[i - 1][j - 1]) + 1;
                    max = Math.max(max, rst[i][j]);
                }
            }
        }
        return max * max;
    }

    /*
        在未排序的数组中找到第 k
        个最大的元素。请注意，
        你需要找的是数组排序后的第 k
        个最大的元素，
        而不是第 k
        个不同的元素。
        示例 1:
        输入:[3,2,1,5,6,4]
        和 k = 2
        输出:5

        链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
    */

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.comparingInt(num -> num));
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() == k + 1) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
        给定一个二叉树，返回它的中序 遍历。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderAccess(root, list);
        return list;
    }

    private void inOrderAccess(TreeNode root, List<Integer> list) {
        if (null != root) {
            inOrderAccess(root.left, list);
            list.add(root.val);
            inOrderAccess(root.right, list);
        }
    }

    public Mar.virgo_0314.Test.ListNode sortList(Mar.virgo_0314.Test.ListNode head) {
        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
        给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
        每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
        返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
        链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        int[] rst = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        int position = 0;
        node = head;
        while (node != null) {
            while (!stack.isEmpty() && list.get(stack.peek()) < node.val) {
                rst[stack.peek()] = node.val;
                stack.pop();
            }
            stack.add(position);
            position++;
            node = node.next;
        }
        return rst;
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
        if (board.length < 1) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1)) {
                    travel(board, i, j);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 0) {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void travel(char[][] board, int x, int y) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Integer> xs = new LinkedList<>();
        Queue<Integer> ys = new LinkedList<>();
        xs.offer(x);
        ys.offer(y);
        while (!xs.isEmpty() && !ys.isEmpty()) {
            int tmpX = xs.poll();
            int tmpY = ys.poll();
            board[tmpX][tmpY] = 0;
            for (int[] direction : directions) {
                int itemX = tmpX + direction[0];
                int itemY = tmpY + direction[1];
                if (0 <= itemX && itemX < board.length && 0 <= itemY && itemY < board[0].length && board[itemX][itemY] == 'O') {
                    xs.offer(itemX);
                    ys.offer(itemY);
                }
            }
        }
    }

    /*
    班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
    给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
    示例 1：
    输入：
    [[1,1,0],
     [1,1,0],
     [0,0,1]]
    输出：2

    链接：https://leetcode-cn.com/problems/friend-circles
     */
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    Integer item = queue.poll();
                    visited[item] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[item][j] == 1 && visited[j] == 0) {
                            queue.offer(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
}
