package past;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Solution4 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<List<Integer>> result;

    public List<List<Integer>> levelOrder(TreeNode root) {
        result = new ArrayList<List<Integer>>();
        if (null != root) {
            int level = 0;
            putTreeNode2List(root, level);
        }
        return result;
    }

    private void putTreeNode2List(TreeNode root, int level) {
        if (null != root) {
            if (result.size() <= level) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(root.val);
                result.add(list);
            } else {
                result.get(level).add(root.val);
            }
            if (null != root.left) {
                putTreeNode2List(root.left, level + 1);
            }
            if (null != root.right) {
                putTreeNode2List(root.right, level + 1);
            }
        }
    }

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    public int minDepth(TreeNode root) {
        minTreeDeep(root, 1);
        return rst;
    }

    private int rst = Integer.MAX_VALUE;
    private void minTreeDeep(TreeNode root, int level) {
        if (null != root) {
            int level1 = level + 1;
            if (root.right == null && root.left == null) {
                if (level < rst) {
                    rst = level;
                }
            }
            minTreeDeep(root.left, level1);
            minTreeDeep(root.right, level1);
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        List<Integer> in = Arrays.stream(inorder).boxed().collect(Collectors.toList());
        List<Integer> post = Arrays.stream(postorder).boxed().collect(Collectors.toList());
        return buildTreeNode(in, post);
    }

    private TreeNode buildTreeNode(List<Integer> inorder, List<Integer> postorder) {
        if (inorder.size() == postorder.size() && inorder.size() > 0) {
            if (inorder.size() == 1) {
                return new TreeNode(inorder.get(0));
            } else {
                Integer root = postorder.remove(postorder.size() - 1);
                TreeNode treeNode = new TreeNode(root);
                int rootPos = inorder.indexOf(root);
                int leftPos = rootPos - 1;
                if (leftPos >= 0) {
                    Integer left = inorder.remove(leftPos);
                    treeNode.left = new TreeNode(left);
                    postorder.remove(left);
                }

                inorder.remove(root);
                treeNode.right = buildTreeNode(inorder, postorder);
                return treeNode;
            }
        } else {
            return null;
        }
    }
}
