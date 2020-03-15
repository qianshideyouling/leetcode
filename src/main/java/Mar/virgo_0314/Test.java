package Mar.virgo_0314;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    /*
    给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
    例如，给出 n = 3，生成结果为：

    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]

    链接：https://leetcode-cn.com/problems/generate-parentheses
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateResult(result, 0, 0, "", n);
        return result;
    }

    private void generateResult(List<String> result, int open, int close, String tmp, int n) {
        if (tmp.length() == n * 2) {
            result.add(tmp);
        }
        if (open < n) {
            generateResult(result, open + 1, close, tmp + "(", n);
        }
        if (close < open) {
            generateResult(result, open, close + 1, tmp + ")", n);
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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
        给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

    示例:
    给定的有序链表： [-10, -3, 0, 5, 9],
    链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
     */
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        return transfer2Tree(list);
    }

    private TreeNode transfer2Tree(List<Integer> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return new TreeNode(list.get(0));
        }
        int middle = list.size() / 2;
        TreeNode node = new TreeNode(list.get(middle));
        node.left = transfer2Tree(list.subList(0, middle));
        node.right = transfer2Tree(list.subList(middle + 1, list.size()));
        return node;
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
        int i = nums.length - 2;
        for (; i >= 0; i--) {
            if (nums[i+1] > nums[i]) {
                break;
            }
        }
        if (i != -1) {
            int j = nums.length - 1;
            for (; j > 0; j--) {
                if (nums[j] > nums[i]) {
                    break;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        Arrays.sort(nums, i+1, nums.length);
    }
}
