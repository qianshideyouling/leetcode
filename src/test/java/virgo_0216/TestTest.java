package virgo_0216;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void coinChange() {
    }

    @Test
    public void numSquares() {
    }

    @Test
    public void getFactors() {
        List<List<Integer>> result = new virgo_0216.Test().getFactors(16);
        for (List list : result) {
            System.out.println(list);
        }
    }

    @Test
    public void exist() {
        char[][] board = {{'a', 'b'}, {'c', 'c'}};
        String str = "abcd";
        System.out.println(new virgo_0216.Test().exist(board, str));
    }

    @Test
    public void restoreIpAddresses() {
        System.out.println("123456".substring(3,6));
    }
}