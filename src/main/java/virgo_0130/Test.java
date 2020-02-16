package virgo_0130;

import java.util.*;
import java.util.function.Predicate;

public class Test {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        List<Double> list = new ArrayList<>();
        for (int i : nums) {
            if (!list.contains((double) i)) {
                list.add((double) i);
            }
        }
        List<Double> newList = new LinkedList<>();
        if (nums.length == 0 || lower < nums[0]) {
            newList.add((double) lower);
        }
        if (nums.length == 0 || upper > nums[nums.length - 1]) {
            newList.add((double) upper);
        }
        for (Double aDouble : list) {
            newList.add(aDouble + 1);
            newList.add(aDouble - 1);
        }
        newList.sort(Comparator.comparingDouble(o -> o));
        newList.removeIf(integer -> list.contains(integer) || integer > upper || integer < lower);
        for (int i = 0; i < newList.size() - 1; i = i + 2) {
            if (newList.get(i).equals(newList.get(i + 1))) {
                result.add("" + newList.get(i).intValue());
            } else {
                result.add(newList.get(i).intValue() + "->" + newList.get(i + 1).intValue());
            }
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        ListNode item = head;
        ListNode pre = null;
        for (int i = 0; i < size - n; i++) {
            pre = item;
            item = item.next;
        }
        if (null == pre) {
            head = head.next;
        } else {
            pre.next = item.next;
            item.next = null;
        }
        return head;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode quick = head;
        ListNode slow = head;
        ListNode pre = null;
        int order = 0;
        while (null != quick) {
            if (order >= n) {
                pre = slow;
                slow = slow.next;
            }
            quick = quick.next;
            order++;
        }
        if (null == pre) {
            head = head.next;
        } else {
            pre.next = slow.next;
            slow.next = null;
        }
        return head;
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int position = -1;
        for (int i = nums.length-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                position = i;
                break;
            }
        }
        int sortPosition = 0;
        if (position != -1) {
            for (int j = nums.length-1; j > 0; j--) {
                if (nums[j] > nums[position]) {
                    sortPosition = j;
                    break;
                }
            }
            int tmp = nums[position];
            nums[position] = nums[sortPosition];
            nums[sortPosition] = tmp;
        }
        Arrays.sort(nums, position+1, nums.length);
    }

}


