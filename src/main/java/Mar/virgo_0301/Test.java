package Mar.virgo_0301;

import java.util.*;

public class Test {
    /*402
    给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
    注意:
    num 的长度小于 10002 且 ≥ k。
    num 不会包含任何前导零。
    示例 1 :
    输入: num = "1432219", k = 3
    输出: "1219"
    解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。

    链接：https://leetcode-cn.com/problems/remove-k-digits
     */
    public String removeKdigits(String num, int k) {
        for (int i = 0; i < k; i++) {
            num = changeNext(num);
        }
        return num;
    }

    private String changeNext(String num) {
        int i = 0;
        for (; i < num.length() - 1; i++) {
            if (num.charAt(i) > num.charAt(i + 1))
                break;
        }
        return formatResult(num.substring(0, i) + num.substring(i + 1));
    }

    private String formatResult(String num) {
        int i = 0;
        for (; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                break;
            }
        }
        return i == num.length() ? "0" : num.substring(i);
    }


    /* 554
    你的面前有一堵方形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。
    砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。
    如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。
    你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
    示例：
    输入: [[1,2,2,1],
          [3,1,2],
          [1,3,2],
          [2,4],
          [3,1,2],
          [1,3,1,1]]

    输出: 2

    链接：https://leetcode-cn.com/problems/brick-wall
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int sum = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                sum = sum + list.get(i);
                if (map.get(sum) == null) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
            }
        }
        int max = map.size() > 0 ? Collections.max(map.values()) : 0;
        return wall.size() - max;
    }

    /* 957
        8 间牢房排成一排，每间牢房不是有人住就是空着。
        每天，无论牢房是被占用或空置，都会根据以下规则进行更改：
        如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。
        否则，它就会被空置。
        （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）
        我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。
        根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。

        示例 1：

        输入：cells = [0,1,0,1,1,0,0,1], N = 7
        输出：[0,0,1,1,0,0,0,0]

        链接：https://leetcode-cn.com/problems/prison-cells-after-n-days
     */
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> set = new HashSet<>();
        int i = 0;
        for (; i < 1000; i++) {
            changeNext(cells);
            String str = Arrays.toString(cells);
            if (!set.add(str)) {
                break;
            }
        }
        int times = (N + i - 1) % i;
        for (int j = 0; j < times; j++) {
            changeNext(cells);
        }
        return cells;
    }

    private void changeNext(int[] cells) {
        int i = 1;
        int pre = cells[0];
        for (; i < cells.length - 1; i++) {
            int tmp = cells[i];
            cells[i] = (pre == cells[i + 1]) ? 1 : 0;
            pre = tmp;
        }
        cells[0] = 0;
        cells[i] = 0;
    }


    /* 64
        给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
        说明：每次只能向下或者向右移动一步。

        示例:
        输入:
        [
          [1,3,1],
          [1,5,1],
          [4,2,1]
        ]
        输出: 7
        解释: 因为路径 1→3→1→1→1 的总和最小。

        链接：https://leetcode-cn.com/problems/minimum-path-sum
     */
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i != 0 || j != 0) {
                    if (i == 0) {
                        grid[i][j] += grid[i][j - 1];
                    } else if (j == 0) {
                        grid[i][j] += grid[i - 1][j];
                    } else {
                        grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
                    }
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        List<List<String>> result = new ArrayList<>();
        breakWord(s, result, wordDict, new Stack<>());
        return result.size() > 0;
    }

    private void breakWord(String str, List<List<String>> result, List<String> wordDict, Stack<String> tmp) {
        if (str.isEmpty()) {
            result.add(new ArrayList<>(tmp));
        }
        for (String item : wordDict) {
            if (str.indexOf(item) == 0) {
                tmp.push(item);
                breakWord(str.substring(item.length()), result, wordDict, tmp);
                tmp.pop();
            }
        }
    }
}
