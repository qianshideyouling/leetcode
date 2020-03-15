package Mar.virgo_0307;

import java.util.Arrays;
import java.util.Stack;

public class Test {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    根据一棵树的前序遍历与中序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]

    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        int val = preorder[0], i = 0;
        TreeNode node = new TreeNode(val);
        while (inorder[i] != val) i++;
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
        node.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length), Arrays.copyOfRange(inorder, i + 1, inorder.length));
        return node;
    }

    /*
    给定一个整数数组 asteroids，表示在同一行的行星。
    对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
    找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

    示例 1:

    输入:
    asteroids = [5, 10, -5]
    输出: [5, 10]
    解释:
    10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。

    链接：https://leetcode-cn.com/problems/asteroid-collision
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        stack.add(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++) {
            int current = asteroids[i];
            if (!stack.isEmpty()){
                int pre = stack.peek();
                boolean dis = false;
                while (current < 0 && pre > 0) {
                    if (pre > -current) {
                        dis = true;
                        break;
                    } else if (-current > pre) {
                        stack.pop();
                        if (!stack.isEmpty()) {
                            pre = stack.peek();
                        } else {
                            break;
                        }
                    } else {
                        stack.pop();
                        dis = true;
                        break;
                    }
                }
                if (!dis)
                    stack.push(current);
            } else {
                stack.push(current);
            }
        }
        int[] result = new int[stack.size()];
        int i = result.length - 1;
        while (!stack.isEmpty()) {
            result[i] = stack.pop();
            i--;
        }
        return result;
    }

    /*
        357
     */
    public int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[11];
        dp[1] = 10;
        dp[2] = 9 * 9;
        for (int i = 3; i <= Math.min(n, 10); i++) {
            dp[i] = dp[i-1] * (11 - i);
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + dp[i];
        }
        return 0 == n ? 1 : sum;
    }

    /*
        1143
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] result = new int[text1.length()+1][text2.length()+1];
        for (int i = 1; i <= text1.length(); i++) {
            for ( int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    result[i][j] = result[i-1][j-1] + 1;
                } else {
                    result[i][j] = Math.max(result[i][j-1], result[i-1][j]);
                }
            }
        }
        return result[text1.length()][text2.length()];
    }

}
