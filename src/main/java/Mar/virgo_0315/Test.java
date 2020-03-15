package Mar.virgo_0315;

import java.util.*;

public class Test {
    /*
        二叉搜索树删除节点
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int successor(TreeNode treeNode) {
        treeNode = treeNode.right;
        while (null != treeNode.left) {
            treeNode = treeNode.left;
        }
        return treeNode.val;
    }

    private int predecessor(TreeNode treeNode) {
        treeNode = treeNode.left;
        while (null != treeNode.right) {
            treeNode = treeNode.right;
        }
        return treeNode.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    /*
    您需要在二叉树的每一行中找到最大的值。
    输入:

              1
             / \
            3   2
           / \   \
          5   3   9
    输出: [1, 3, 9]

    链接：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();
        if (null != root) {
            queue.add(root);
            level.add(0);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int lev = level.poll();
            if (list.size() > lev) {
                list.set(lev, Math.max(node.val, list.get(lev)));
            } else {
                list.add(node.val);
            }
            if (null != node.right) {
                queue.add(node.right);
                level.add(lev + 1);
            }
            if (null != node.left) {
                queue.add(node.left);
                level.add(lev + 1);
            }
        }
        return list;
    }

    /*
        合并區間
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] current = stack.peek();
            if (intervals[i][0] == current[1]) {
                stack.pop();
                intervals[i][0] = current[0];
            } else if (intervals[i][0] < current[1]) {
                stack.pop();
                intervals[i][0] = current[0];
                intervals[i][1] = Math.max(intervals[i][1], current[1]);
            }
            stack.push(intervals[i]);
        }
        int[][] result = new int[stack.size()][2];
        for (int i = 0; i < stack.size(); i++) {
            result[i] = (int[]) stack.toArray()[i];
        }
        return result;
    }

    /*
        进行下一个挑战：
乘积小于K的子数组
中等
分割数组
中等
餐厅过滤器

     */
    public int minSwaps(int[] data) {
        int num = 0;
        for (int n : data) {
            if (n == 1) {
                num++;
            }
        }
        int i = 0;
        int max = 0;
        for (; i < num; i++) {
            if (data[i] == 1) {
                max++;
            }
        }
        int pre = max;
        for (; i < data.length; i++) {
            if (data[i] != 1) {
                if (data[i - num] == 1) {
                    pre--;
                }
            } else {
                if (data[i - num] != 1) {
                    pre++;
                }
            }
            max = Math.max(max, pre);
        }
        return num - max;
    }

    /*
    给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
    示例 1:
    输入: [[0, 30],[5, 10],[15, 20]]
    输出: 2

    链接：https://leetcode-cn.com/problems/meeting-rooms-ii
     */
    public int minMeetingRooms(int[][] intervals) {
        int max = 0;
        for (int[] interval : intervals) {
            max = Math.max(max, Math.max(interval[0], interval[1]));
        }
        int rst = 0;
        int[] result = new int[max];
        for (int[] interval : intervals) {
            for (int i = interval[0]; i < interval[1]; i++) {
                result[i]++;
                rst = Math.max(result[i], rst);
            }
        }
        return rst;
    }

    /*
    根据一棵树的前序遍历与中序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。
    例如，给出

    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode node = new TreeNode(preorder[0]);
        int i = 0;
        while (inorder[i] != preorder[0]) {
            i++;
        }
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1),
                Arrays.copyOfRange(inorder, 0, i));
        node.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length),
                Arrays.copyOfRange(inorder, i + 1, inorder.length));
        return node;
    }

    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == ']' && result.length() != 0) {
                int i = result.length() - 1;
                while (result.charAt(i) != '[') {
                    i--;
                }
                String temp = result.substring(i + 1);
                i--;
                int j = i;
                while (j >= 0 && result.charAt(j) >= '0' && result.charAt(j) <= '9') {
                    j--;
                }
                int num = Integer.parseInt(result.substring(j + 1, i + 1));
                result = expandResult(new StringBuilder(result.substring(0, j + 1)), temp, num);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private StringBuilder expandResult(StringBuilder result, String temp, int n) {
        for (int i = 0; i < n; i++) {
            result.append(temp);
        }
        return result;
    }

    public String decodeString1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == ']' && !stack.isEmpty()) {
                String temp = "";
                String num = "";
                while (!stack.isEmpty()) {
                    char item = stack.pop();
                    if (item != '[') {
                        temp = item + temp;
                    } else {
                        break;
                    }
                }
                while (!stack.isEmpty()) {
                    char item = stack.peek();
                    if (item >= '0' && item <= '9') {
                        num = item + num;
                        stack.pop();
                    } else {
                        break;
                    }
                }
                int n = Integer.parseInt(num);
                extractStack(stack, temp, n);
            } else {
                stack.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stack) {
            result.append(character);
        }
        return result.toString();
    }

    private void extractStack(Stack<Character> stack, String temp, int n) {
        for (int i = 0; i < n; i++) {
            for (char ch : temp.toCharArray()) {
                stack.push(ch);
            }
        }
    }

}
