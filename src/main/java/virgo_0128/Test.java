package virgo_0128;

import java.util.*;

public class Test {

    public static List<Integer> twoSum(List<Integer> list, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> rst = null;
        for (int num : list) {
            if (map.get(num) != null) {
                Integer[] array = {num, map.get(num)};
                rst =  new ArrayList<>(Arrays.asList(array));
            } else {
                map.put(sum-num, num);
            }
        }
        return rst;
    }
}
