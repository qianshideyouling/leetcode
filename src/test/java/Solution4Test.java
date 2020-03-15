import org.junit.Test;
import past.Solution4;

public class Solution4Test {

    @Test
    public void buildTree() {
        int[] inOrder = {1,2};
        int[] postOrder = {2,1};
        Solution4.TreeNode treeNode = new Solution4().buildTree(inOrder, postOrder);
        System.out.println(treeNode);
    }
}