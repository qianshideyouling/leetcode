package Sep.virgo_0926;

import java.util.*;

public class Test {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, new Stack<Integer>(), result);
        return result;
    }

    private void combinationSum(int[] array, int target, int start, Stack<Integer> tmp,
                                List<List<Integer>> result) {
        if (target == 0 && !result.contains(tmp)) {
            result.add(new ArrayList<>(tmp));
        }
        for (int i = start; i < array.length; i++) {
            if (array[i] <= target) {
                tmp.push(array[i]);
                combinationSum(array, target - array[i], i + 1, tmp, result);
                tmp.pop();
            }
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.contains(s)) {
            return true;
        }
        boolean result = false;
        for (int i = 0; i < s.length(); i++) {
            if (wordDict.contains(s.substring(0, i))) {
                result = result || wordBreak(s.substring(i), wordDict);
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> result = new HashMap<>();
        int sum = 0;
        int n = 0;
        result.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (result.containsKey(sum - k)) {
                n += result.get(sum - k);
            }
            if (result.containsKey(sum)) {
                result.put(sum, result.get(sum) + 1);
            } else {
                result.put(sum, 1);
            }
        }
        return n;
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        findSubsequence(nums, 0, new Stack<>(), result);
        return result;
    }

    private void findSubsequence(int[] nums, int start, Stack<Integer> tmp,
                                 List<List<Integer>> result) {
        if (tmp.size() > 1 && !result.contains(tmp)) {
            result.add(new ArrayList<>(tmp));
        }
        for (int i = start; i < nums.length; i++) {
            boolean used = false;
            if (tmp.isEmpty()) {
                used = true;
                tmp.push(nums[i]);
            } else if (nums[i] >= tmp.peek()) {
                used = true;
                tmp.push(nums[i]);
            }
            findSubsequence(nums, i + 1, tmp, result);
            if (used) {
                tmp.pop();
            }
        }
    }
}
