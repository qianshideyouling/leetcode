package virgo_0208;

import java.util.*;

public class Test {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        return getResult(list);
    }

    private List<List<Integer>> getResult(List<Integer> list) {
        if (list.size() == 1) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>(Collections.singletonList(list.get(0))));
            return result;
        } else {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                List<Integer> sub = getSubList(list, i);
                List<List<Integer>> tmp = getResult(sub);
                for (List<Integer> item : tmp) {
                    item.add(list.get(i));
                    result.add(item);
                }
            }
            return result;
        }
    }

    private List<Integer> getSubList(List<Integer> list, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i != n) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    // https://leetcode-cn.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> nums = new ArrayList<>();
        for (int i : candidates) {
            nums.add(i);
        }
        dfs(nums, new Stack<>(), target, result);
        return result;
    }

    private void dfs(List<Integer> candidates, Stack<Integer> stack, int target, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(stack));
        } else if (target > 0) {
            for (int i = 0; i < candidates.size(); i++) {
                stack.push(candidates.get(i));
                dfs(candidates.subList(i, candidates.size()), stack, target - candidates.get(i), result);
                stack.pop();
            }
        }
    }

//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> nums = new ArrayList<>();
//        for (int i : candidates) {
//            nums.add(i);
//        }
//        dfs(nums, new ArrayList<>(), target, result);
//        return result;
//    }
//
//    private void dfs(List<Integer> candidates, List<Integer> list, int target, List<List<Integer>> result) {
//        if (target == 0) {
//            result.add(list);
//        } else if (target > 0) {
//            for (int i = 0; i < candidates.size(); i++) {
//                List<Integer> tmp = new ArrayList<>(list);
//                tmp.add(candidates.get(i));
//                dfs(candidates.subList(i, candidates.size()), tmp, target - candidates.get(i), result);
//            }
//        }
//    }
}
