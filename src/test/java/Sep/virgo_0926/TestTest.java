package Sep.virgo_0926;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestTest {

    @Test
    public void combinationSum2() {
        int[] array = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> result = new Sep.virgo_0926.Test().combinationSum2(array, 8);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    @Test
    public void wordBreak(){
        List<String> array = Arrays.asList("go","goal","goals","special");
        System.out.println(new Sep.virgo_0926.Test().wordBreak("goalspecial", array));
    }

    @Test
    public void findSubsequences(){
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        System.out.println(new Sep.virgo_0926.Test().findSubsequences(array));
    }
}