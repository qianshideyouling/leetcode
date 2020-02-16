import java.util.*;

public class Solution2 {
    public int findMin(int[] nums) {
        List<Integer> list = new ArrayList();
        for (int i =0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        return Collections.min(list);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] temp = new int[triangle.size()][];
        for (int i = 0; i < triangle.size(); i++) {
            int[] item = new int[triangle.get(i).size()];
            temp[i] = item;
            for (int j = 0; j < triangle.get(i).size(); j++) {
                temp[i][j] = triangle.get(i).get(j);
            }
        }
        for (int i = 1; i < temp.length; i++) {
            temp[i][0] += temp[i-1][0];
            temp[i][temp[i].length-1] += temp[i-1][temp[i-1].length-1];
            for (int j = 1; j < temp[i].length - 1; j++) {
                temp[i][j] = temp[i][j] + Math.min(temp[i-1][j-1], temp[i-1][j]);
            }
        }
        int[] rst = temp[temp.length - 1];
        Arrays.sort(rst);
        return rst[rst.length-1];
    }

        class Pair{
            int i;
            int times;
            public Pair(int i, int times) {
                this.i = i;
                this.times = times;
            }
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }

            List<String> result = new ArrayList<>();
            result.add(beginWord);
            result.addAll(wordList);

            int[][] array = new int[result.size()][];
            for (int i = 0; i < result.size(); i++) {
                int[] item = new int[result.size()];
                Arrays.fill(item, Integer.MAX_VALUE);
                array[i] = item;
                for (int j = 0; j < result.size(); j++) {
                    if (connect(result.get(i), result.get(j))) {
                        array[i][j] = 0;
                    }
                }
            }
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0, 2));
            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                int value = pair.i;
                for (int i = 0; i < array.length; i++) {
                    int newTime = pair.times + 1;
                    if (array[i][value] == 0) {
                        array[i][value] = pair.times;
                        array[value][i] = pair.times;
                        queue.add(new Pair(i, newTime));
                    }
                }
            }
            int position = result.indexOf(endWord);
            int[] rst = array[position];
            Arrays.sort(rst);
            return rst[0] == Integer.MAX_VALUE ? 0 : rst[0];
        }

        private boolean connect(String str1, String str2) {
            int rst = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i))
                    rst++;
            }
            return rst == 1;
        }

}
