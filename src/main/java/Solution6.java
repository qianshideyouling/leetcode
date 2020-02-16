import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution6 {

    public int getStepFromStart2Target(int[][] maze, int[] start, int[] target) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        distance[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
        while(null != queue.peek()) {
            int[] point = queue.poll();
            for (int[] ints : direction) {
                int x = point[0] + ints[0];
                int y = point[1] + ints[1];
                if (x >= 0 && x < maze[0].length && y >= 0 && y < maze.length && maze[x][y] == 0 && distance[x][y] == Integer.MAX_VALUE) {
                    distance[x][y] = distance[point[0]][point[1]] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return distance[target[0]][target[1]] == Integer.MAX_VALUE ? -1 : distance[target[0]][target[1]];
    }

    public int getStepFromStart2Target1(int[][] maze, int[] start, int[] target) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        distance[start[0]][start[1]] = 0;
        Stack<int[]> stack = new Stack<>();
        stack.add(start);
        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!stack.isEmpty()) {
            int[] point = stack.pop();
            for (int[] ints : direction) {
                int x = point[0] + ints[0];
                int y = point[1] + ints[1];
                int count = 0;
                while (x >= 0 && x < maze[0].length && y >= 0 && y < maze.length && maze[x][y] == 0) {
                    x = x + ints[0];
                    y = y + ints[1];
                    count++;

                }
                if (distance[point[0]][point[1]] + count < distance[x - ints[0]][y - ints[1]]) {
                    distance[x - ints[0]][y - ints[1]] = distance[point[0]][point[1]] + count;
                    stack.add(new int[]{x - ints[0], y - ints[1]});
                }
            }
        }
        return distance[target[0]][target[1]] == Integer.MAX_VALUE ? -1 : distance[target[0]][target[1]];
    }

}
