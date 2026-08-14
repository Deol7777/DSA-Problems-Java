package REDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    // prerequisites[i] = {a, b} means you must take b before a.
    // Return true iff every course can be finished, i.e. the graph is acyclic.

    public static void main(String[] args) {
        test("simple chain of two", 2, new int[][] { { 1, 0 } }, true);
        test("two-cycle", 2, new int[][] { { 1, 0 }, { 0, 1 } }, false);

        test("no prerequisites at all", 5, new int[][] {}, true);
        test("single course", 1, new int[][] {}, true);

        // a course that requires itself
        test("self loop", 1, new int[][] { { 0, 0 } }, false);

        test("long chain", 4, new int[][] { { 1, 0 }, { 2, 1 }, { 3, 2 } }, true);

        // diamond: 0 -> 1, 0 -> 2, both -> 3. Node 3 is reached twice but no cycle,
        // so a search that treats "already seen" as "cycle" fails here.
        test("diamond dag", 4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }, true);

        // cycle buried behind an acyclic branch
        test("cycle plus extra edge", 4,
                new int[][] { { 1, 0 }, { 2, 1 }, { 0, 2 }, { 3, 0 } }, false);

        // two disconnected components, only the second one is cyclic
        test("disconnected, one cyclic", 6,
                new int[][] { { 1, 0 }, { 3, 2 }, { 2, 3 }, { 5, 4 } }, false);

        // isolated node the search must still visit
        test("disconnected, all acyclic", 6,
                new int[][] { { 1, 0 }, { 3, 2 }, { 5, 4 } }, true);

        // same edge listed twice is not a cycle
        test("duplicate edge", 3, new int[][] { { 1, 0 }, { 1, 0 }, { 2, 1 } }, true);

        // every course depends on course 0
        test("star", 5, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 } }, true);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int numCourses, int[][] prerequisites, boolean expected) {
        boolean actual = new CourseSchedule().canFinish(numCourses, prerequisites);
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> outMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int processed = 0;

        // indegree array and outmap - for which courses this course is a prerequisite
        // for
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
            List<Integer> temp = outMap.getOrDefault(pre[1], new ArrayList<>());
            temp.add(pre[0]);
            outMap.put(pre[1], temp);
        }

        // insert all the 0 indegrees into the queue
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            processed++;
            if (processed == numCourses)
                return true;
            // for whichever this was a prerequisite, reduce the indegree
            List<Integer> temp = outMap.get(cur);
            if (temp == null)
                continue;
            for (Integer in : temp) {
                indegree[in]--;
                if (indegree[in] == 0)
                    queue.add(in);
            }
        }

        // if not all courses are processed, not possible
        return false;
    }
}
