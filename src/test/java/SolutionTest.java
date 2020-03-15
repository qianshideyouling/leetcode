import org.junit.Test;
import past.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SolutionTest {

    @Test
    public void removeKdigits() {
        String num = "1789";
        System.out.println(new Solution().removeKdigits(num, 1));
        List<Integer> rst = new ArrayList<>();
        rst.add(1);
        rst.add(2);
        System.out.println(rst.subList(0,2));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] arrays = new int[4][2];
        arrays[0] = p1;
        arrays[1] = p2;
        arrays[2] = p3;
        arrays[3] = p4;
        Arrays.sort(arrays, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int rst = o1[1] - o2[1];
                return rst == 0 ? o1[0] - o2[0] : rst;
            }
        });
        int[] result = new int[4];
        result[0] = arrays[0][0] - arrays[1][0];
        result[1] = arrays[0][1] - arrays[2][1];
        result[2] = arrays[1][1] - arrays[3][1];
        result[3] = arrays[2][0] - arrays[3][0];
        return result[0] == result[1] && result[2] == result[3] && result[1] == result[2];
    }
}