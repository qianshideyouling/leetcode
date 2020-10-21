package Sep.virgo_0920;

import java.util.Arrays;

public class virgo_0920 {

    public String longestPalindrome(String str) {
        int size = str.length();
        int[][] result = new int[size][size];
        int start = 0, end = 0;
        int max = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j + i < size; j++) {
                int k = j + i;
                if (i == 0) {
                    result[j][k] = 1;
                } else if (i == 1) {
                    result[j][k] = str.charAt(j) == str.charAt(k) ? 2 : 0;
                } else if (str.charAt(j) == str.charAt(k) && result[j + 1][k - 1] != 0) {
                    result[j][k] = result[j + 1][k - 1] + 2;
                }
                if (max <= result[j][k]) {
                    start = j;
                    end = k+1;
                }
            }
        }
        return str.substring(start, end);
    }
}
