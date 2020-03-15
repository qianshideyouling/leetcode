import org.junit.Test;
import past.Solution6;

public class Solution6Test {

    @Test
    public void shortestDistance() {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] dest = {4, 4};
        System.out.println(new Solution6().getStepFromStart2Target(maze, start, dest));
    }
}