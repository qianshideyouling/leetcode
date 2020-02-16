package virgo_0127;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class TwoSum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode num1 = l1;
        ListNode num2 = l2;
        ListNode item = null;
        int next = 0;
        while (num1 != null && num2 != null) {
            int s = num1.val + num2.val + next;
            next = s / 10;
            int value = s % 10;
            ListNode  sum = new ListNode(value);
            if (null == result) {
                item = sum;
                result = item;
            } else {
                item.next = sum;
            }
            num1 = num1.next;
            num2 = num2.next;
            item = sum;
        }
        while (num1 != null) {
            int s = num1.val + next;
            next = s / 10;
            int value = s % 10;
            ListNode sum = new ListNode(value);
            item.next = sum;
            num1 = num1.next;
            item = sum;
        }
        while (num2 != null) {
            int s = num2.val + next;
            next = s / 10;
            int value = s % 10;
            ListNode sum = new ListNode(value);
            num2.next = sum;
            num2 = num2.next;
            item = sum;
        }
        if (next != 0) {
            item.next = new ListNode(1);
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        StringBuilder sub = new StringBuilder();
        for (char ch : s.toCharArray()) {
            String item = ch + "";
            if (!sub.toString().contains(item)) {
                sub.append(item);
            } else {
                int index = sub.indexOf(item);
                result = Math.max(sub.length(), result);
                sub = new StringBuilder(sub.substring(index + 1) + item);
            }
        }
        return Math.max(sub.length(), result);
    }

    public int lengthOfLongestSubstring2(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sub = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                String item = s.charAt(j) + "";
                if (!sub.toString().contains(item)) {
                    sub.append(item);
                } else {
                    break;
                }
            }
            result = Math.max(result, sub.length());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("123".replace("1", ""));
    }

}
