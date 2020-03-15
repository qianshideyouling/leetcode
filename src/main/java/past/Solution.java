package past;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public String removeKdigits(String num, int k) {
        if (num == null || num.equals("") || num.length() == k) {
            return 0 + "";
        }
        String result = num;
        for (int i = 0; i < k; i++) {
            result = getResult(result);
        }
        return formatResult(result);
    }

    private String getResult(String result) {
        if (result.length() == 1) {
            return "0";
        } else {
            char[] chars = result.toCharArray();
            char first = chars[0];
            int position = 0;
            for (int i = 1; i < result.length(); i++) {
                char item = chars[i];
                if (item > first) {
                    position = i;
                    first = chars[i];
                    continue;
                }
                if (item < first) {
                    break;
                }
            }
            return result.substring(0, position) + result.substring(position+1);
        }
    }

    private String formatResult(String result) {
        int j = 0;
        for (; j < result.length(); j++) {
            if (result.toCharArray()[j] != '0') {
                break;
            }
        }
        String substring = result.substring(j);
        if ("".equals(substring)) {
            substring = "0";
        }
        return substring;
    }

    public List<Integer> findDuplicates(int[] nums) {
        Arrays.sort(nums);
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < nums.length- 1; i++) {
            if (nums[i] == nums[i++]) {
                rst.add(nums[i]);
            }
        }
        return rst;
    }
}
