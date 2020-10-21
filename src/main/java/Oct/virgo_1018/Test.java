package Oct.virgo_1018;

import java.util.*;

public class Test {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
        在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
        输入: 4->2->1->3
        输出: 1->2->3->4
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode quick = head.next;
        ListNode slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        right = sortList(right);
        ListNode rst = new ListNode(0);
        ListNode node = rst;
        while (left != null & right != null) {
            if (left.val > right.val) {
                node.next = right;
                right = right.next;
            } else {
                node.next = left;
                left = left.next;
            }
            node = node.next;
        }
        node.next = left == null ? right : left;
        return rst.next;
    }

    /*
        给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    注意：答案中不可以包含重复的三元组。
    给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    链接：https://leetcode-cn.com/problems/3sum
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> rst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    rst.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    right--;
                    left++;
                    while (right > left && nums[right] == nums[right + 1]) right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return rst;
    }

    public int subarraysWithKDistinct1(int[] A, int K) {
        List<List<Integer>> rst = new ArrayList<>();
        backpack(A, K, 0, rst, new HashSet<>(), new Stack<Integer>());
        return rst.size();
    }

    private void backpack(int[] a, int k, int start, List<List<Integer>> rst,
                          HashSet<Integer> set, Stack<Integer> stack) {
        if (set.size() == k) {
            System.out.println(stack);
            rst.add(new ArrayList<>(set));
        }
        if (set.size() > k) {
            set.clear();
            return;
        }
        for (int i = start; i < a.length; i++) {
            set.add(a[start]);
            stack.push(a[start]);
            backpack(a, k, start + 1, rst, set, stack);
            set.remove(a[start]);
            stack.pop();
        }
    }

    /*
    给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
    （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
    返回 A 中好子数组的数目。
    输入：A = [1,2,1,2,3], K = 2
    输出：7
    解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

    链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        int rst = 0;
        for (int i = 0; i < A.length && i <= A.length - K; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < A.length; j++) {
                set.add(A[j]);
                if (set.size() > K) {
                    break;
                }
                if (set.size() == K) {
                    rst++;
                }
            }
        }
        return rst;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle = (left + right)/2;
        int rst = -1;
        while (left < right - 1) {
            if (nums[middle] == target) {
                rst = middle;
                break;
            } else if (nums[middle] < target) {
                left = middle;
            } else {
                right = middle;
            }
            middle = (left + right)/2;
        }
        if (target == nums[left]) {
            rst = left;
        }
        if (target == nums[right]) {
            rst = right;
        }
        return rst;
    }
}
