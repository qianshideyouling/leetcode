import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Solution2Test {

    @Test
    public void minimumTotal() {
        List<List<Integer>> array = new ArrayList<>();
        List<Integer> li1 = Arrays.asList(1);
        List<Integer> li2 = Arrays.asList(1, 2);
        array.add(li1);
        array.add(li2);
//        new Solution2().minimumTotal(array);
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(new Solution2().ladderLength("hit", "cog", wordList));
    }
}