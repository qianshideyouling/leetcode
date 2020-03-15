package Mar.virgo_0315;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
