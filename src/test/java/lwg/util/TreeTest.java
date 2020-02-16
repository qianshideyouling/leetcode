package lwg.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class TreeTest {

    private Tree<Integer> tree = new Tree<Integer>();

    public TreeTest() {

    }

    @Before
    public void setUp() throws Exception {
        Integer[] array = {1, 3, 2, 5, 0};
        tree.buildTree(array, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    public void broadFirst() {
        List<Integer> result = tree.broadFirst();
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }

    @Test
    public void depthFirst() {
        List<Integer> result = tree.depthFirst();
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }
}