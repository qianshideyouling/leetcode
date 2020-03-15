package lwg.util;

import java.util.*;

public class Tree<T> {
    private TreeNode<T> root;

    void buildTree(T[] array, Comparator<T> comparator) {
        if (array.length < 1) {
          return;
        }
        TreeNode<T> node = new TreeNode<T>(array[0]);
        for (int i = 1; i < array.length; i++) {
            addNode2TreeNode(node, array[i], comparator);
        }
        root = node;
    }

    private void addNode2TreeNode(TreeNode<T> node, T t, Comparator<T> comparator) {
        if (comparator.compare(t, node.value) > 0) {
            if (null == node.right) {
                node.right = new TreeNode<T>(t);
            } else {
                addNode2TreeNode(node.right, t, comparator);
            }
        } else {
            if (null == node.left) {
                node.left = new TreeNode<T>(t);
            } else {
                addNode2TreeNode(node.left, t, comparator);
            }
        }
    }

    void read(TreeNode treeNode) {
        if (null != treeNode) {
            System.out.println(treeNode.value);
            read(treeNode.left);
            read(treeNode.right);
        }
    }

    List<T> broadFirst() {
        Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        List<T> list = new ArrayList<T>();
        if (null != root) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            list.add(node.value);
            if (null != node.left) {
                queue.add(node.left);
            }
            if (null != node.right) {
                queue.add(node.right);
            }
        }
        return list;
    }

    List<T> depthFirst() {
        Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
        List<T> list = new ArrayList<T>();
        if (null != root) {
            stack.add(root);
        }
        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();
            list.add(node.value);
            if (null != node.left) {
                stack.add(node.left);
            }
            if (null != node.right) {
                stack.add(node.right);
            }
        }
        return list;
    }

    public String customSortString(String S, String T) {

        Character[] array = new Character[T.length()];
        for (int i = 0; i < T.length(); i++) {
            array[i] = T.charAt(i);
        }
        Arrays.sort(array, Comparator.comparingInt(S::indexOf));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T.length(); i++) {
            result.append(array[i]);
        }
        return result.toString();
    }

    public int minEatingSpeed(int[] piles, int H) {
        int rst = 0;
        int time = Integer.MAX_VALUE;
        while (time > H) {
            rst = rst + 1;
            time = getTime(piles, rst);
        }
        return rst;
    }

    private int getTime(int[] piles, int rst) {
        int sum = 0;
        for (int pile : piles) {
            int extra = pile % rst;
            int tmp = pile / rst;
            int result = extra == 0 ? tmp : tmp + 1;
            sum = sum + result;
        }
        return sum;
    }
}
