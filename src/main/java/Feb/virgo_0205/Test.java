package Feb.virgo_0205;

import java.util.*;

public class Test {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        Map<Integer, List<Integer>> courses = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            in[prerequisite[0]]++;
            List<Integer> subs = courses.getOrDefault(prerequisite[1], new ArrayList<>());
            subs.add(prerequisite[0]);
            courses.put(prerequisite[1], subs);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            numCourses--;
            List<Integer> subs = courses.getOrDefault(course, new ArrayList<>());
            for (int sub : subs) {
                in[sub]--;
                if (in[sub] == 0) {
                    queue.add(sub);
                }
            }
        }
        return numCourses == 0;
    }
}
