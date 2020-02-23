package virgo_0223;

import java.util.*;

/*
    给你这棵「无向树」，请你测算并返回它的「直径」：这棵树上最长简单路径的 边数。
    我们用一个由所有「边」组成的数组 edges 来表示一棵无向树，其中 edges[i] = [u, v] 表示节点 u 和 v 之间的双向边。
    树上的节点都已经用 {0, 1, ..., edges.length} 中的数做了标记，每个节点上的标记都是独一无二的。

    示例 1：
    输入：edges = [[0,1],[0,2]]
    输出：2
    解释：
    这棵树上最长的路径是 1 - 0 - 2，边数为 2。
    示例 2：

    输入：edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
    输出：4
    解释：
    这棵树上最长的路径是 3 - 2 - 1 - 4 - 5，边数为 4。

    链接：https://leetcode-cn.com/problems/tree-diameter
 */
public class Test {

    private int maxDep;
    private int dest = 0;

    public int treeDiameter(int[][] edges) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int[] edge : edges) {
            putEdge(tree, edge, 0, 1);
            putEdge(tree, edge, 1, 0);
        }
        broadRead(edges[0][0], tree);
        broadRead(dest, tree);
        return maxDep;
    }

    private void putEdge(Map<Integer, List<Integer>> tree, int[] edge, int start, int end) {
        if (tree.get(edge[start]) == null) {
            tree.put(edge[start], new ArrayList<>(Collections.singleton(edge[end])));
        } else {
            tree.get(edge[start]).add(edge[end]);
        }
    }

    private void broadRead(int start, Map<Integer, List<Integer>> tree) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> list = new LinkedList<>();
        queue.add(start);
        list.add(0);
        List<Integer> out = new ArrayList<>();
        maxDep = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            int dep = list.poll();
            if (dep > maxDep) {
                maxDep = dep;
                dest = poll;
            }
            out.add(poll);
            for (Integer i : tree.get(poll)) {
                if (!out.contains(i)) {
                    queue.add(i);
                    list.add(dep + 1);
                }
            }
        }
    }

    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<Integer> position1 = findPostion(words, word1);
        List<Integer> position2 = findPostion(words, word2);
        int shortDistance = Integer.MAX_VALUE;
        for (Integer p1 : position1) {
            for (Integer p2 : position2) {
                int rst = Math.abs(p1 - p2);
                if (shortDistance > rst && rst != 0) {
                    shortDistance = rst;
                }
            }
        }
        return shortDistance;
    }

    private List<Integer> findPostion(String[] words, String word1) {
        List<Integer> position = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                position.add(i);
            }
        }
        return position;
    }

    /*
    给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

    每次转换只能改变一个字母。
    转换过程中的中间单词必须是字典中的单词。
    说明:

    如果不存在这样的转换序列，返回 0。
    所有单词具有相同的长度。
    所有单词只由小写字母组成。
    字典中不存在重复的单词。
    你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
    示例 1:

    输入:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    输出: 5

    链接：https://leetcode-cn.com/problems/word-ladder
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<String> list = new ArrayList<>(Collections.singleton(beginWord));
        list.addAll(wordList);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (connect(list.get(i), list.get(j))) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }
        int[] read = new int[list.size()];
        Queue<Integer> queue = new LinkedList<>(Collections.singleton(0));
        Queue<Integer> level = new LinkedList<>(Collections.singleton(0));
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            int distance = level.poll();
            read[poll] = 1;
            if (list.get(poll).equals(endWord) && distance < min) {
                min = distance;
                continue;
            }
            if (distance > min) {
                continue;
            }
            for (Integer num : map.get(poll)) {
                if (read[num] == 0) {
                    queue.add(num);
                    level.add(distance + 1);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min + 1;
    }

    private boolean connect(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

        示例:

        s = "3[a]2[bc]", 返回 "aaabcbc".
        s = "3[a2[c]]", 返回 "accaccacc".
        s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

    链接：https://leetcode-cn.com/problems/decode-string
     */
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) != ']') {
                stack.push(s.charAt(i));
            } else {
                String item = getResult(stack);
                for (int j = item.length() - 1; j >= 0; j-- ) {
                    stack.push(item.charAt(j));
                }
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }

    private String getResult(Stack<Character> stack) {
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() != '[') {
            str.append(stack.pop());
        }
        stack.pop();
        StringBuilder num = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() <= '9' && stack.peek() >= '0') {
            num.insert(0, stack.pop());
        }
        StringBuilder rst = new StringBuilder();
        int time = Integer.parseInt(num.toString());
        for (int j = 0; j < time; j++) {
            rst.append(str);
        }
        return rst.toString();
    }
}
