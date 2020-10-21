package April.virgo_0405;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    private Map<Integer, Integer> map;
    private int size = 0;

    public Test(int capacity) {
        map = new LinkedHashMap<>();
        size = capacity;
    }

    public int get(int key) {
        Integer rst = map.get(key);
        if (rst != null) {
            return rst;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.size() < size) {
            map.put(key, value);
        } else {
            map.keySet();
        }
    }

    public static void main(String[] args) {
        System.out.println(new HashMap<Integer, Integer>(10));
    }
}
