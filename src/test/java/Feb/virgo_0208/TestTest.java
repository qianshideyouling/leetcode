package Feb.virgo_0208;

import org.junit.Test;

import java.util.List;

public class TestTest {

    @Test
    public void permute() {
        int[] nums = {1,2,3};
        List<List<Integer>> list = new Feb.virgo_0208.Test().permute(nums);
        System.out.println(list);
    }
}