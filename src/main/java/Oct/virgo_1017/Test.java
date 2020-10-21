package Oct.virgo_1017;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Test {
    /*
        背包问题
     */
    public int backpack(int[][] items, int v) {
        int[][] records = new int[items.length + 1][v + 1];
        for (int i = 1; i <= items.length; i++) {
            for (int j = 1; j <= v; j++) {
                if (j >= items[i - 1][0]) {
                    records[i][j] = Math.max(records[i - 1][j],
                            records[i - 1][j - items[i - 1][0]] + items[i - 1][1]);
                } else {
                    records[i][j] = records[i - 1][j];
                }
            }
        }
        return records[items.length][v];
    }

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
        ListNode node = head;
        ListNode rst = null;
        while (null != node) {
            rst = addNode(rst, node);
            node = node.next;
        }
        return rst;
    }

    private ListNode addNode(ListNode rst, ListNode node) {
        ListNode nodeCopy = new ListNode(node.val);
        if (rst == null) {
            return nodeCopy;
        }
        if (nodeCopy.val <= rst.val) {
            nodeCopy.next = rst;
            return nodeCopy;
        }
        ListNode item = rst;
        while (item.next != null) {
            if (nodeCopy.val < item.next.val) {
                ListNode tmp = item.next;
                item.next = nodeCopy;
                nodeCopy.next = tmp;
                return rst;
            }
            item = item.next;
        }
        item.next = nodeCopy;
        return rst;
    }

    /*
    输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
    输入：1->2->4, 1->3->4
    输出：1->1->2->3->4->4

    链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode rst = new ListNode(0);
        ListNode r1 = rst;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                r1.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                r1.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            r1 = r1.next;
        }
        r1.next = p1 == null ? p2 : p1;
        return rst.next;
    }

    public int calculate(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 0);
        map.put('-', 0);
        map.put('*', 1);
        map.put('/', 1);
        s = s.trim();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> symbol = new Stack<>();
        for (char ch : s.toCharArray()) {
            if ('0' <= ch && ch <= '9') {
                nums.add(Integer.parseInt(String.valueOf(ch)));
                continue;
            }

        }
        return 0;
    }
}
