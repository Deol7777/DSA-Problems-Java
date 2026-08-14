package REDO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule2 {
    // prerequisites[i] = {a, b} means you must take b before a.
    // Return any valid ordering of all numCourses courses, or an empty array if
    // no ordering exists.

    public static void main(String[] args) {
        solvable("simple chain of two", 2, new int[][] { { 1, 0 } });

        // several valid answers here: [0,1,2,3] and [0,2,1,3] both work
        solvable("diamond dag", 4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } });

        solvable("no prerequisites at all", 5, new int[][] {});
        solvable("single course", 1, new int[][] {});

        // only one legal ordering: 0,1,2,3
        solvable("long chain", 4, new int[][] { { 1, 0 }, { 2, 1 }, { 3, 2 } });

        // isolated nodes must still appear in the output
        solvable("disconnected, all acyclic", 6,
                new int[][] { { 1, 0 }, { 3, 2 }, { 5, 4 } });

        // same edge listed twice is not a cycle
        solvable("duplicate edge", 3, new int[][] { { 1, 0 }, { 1, 0 }, { 2, 1 } });

        // every course depends on course 0, so 0 must come first
        solvable("star", 5, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 } });

        impossible("two-cycle", 2, new int[][] { { 1, 0 }, { 0, 1 } });
        impossible("self loop", 1, new int[][] { { 0, 0 } });
        impossible("cycle plus extra edge", 4,
                new int[][] { { 1, 0 }, { 2, 1 }, { 0, 2 }, { 3, 0 } });
        impossible("disconnected, one cyclic", 6,
                new int[][] { { 1, 0 }, { 3, 2 }, { 2, 3 }, { 5, 4 } });
    }

    // Many orderings can be correct, so we validate the returned order against the
    // constraints instead of comparing it to one hard-coded answer.
    static void solvable(String name, int numCourses, int[][] prerequisites) {
        int[] actual = new CourseSchedule2().findOrder(numCourses, prerequisites);
        String problem = validate(actual, numCourses, prerequisites);
        report(name, problem, actual);
    }

    static void impossible(String name, int numCourses, int[][] prerequisites) {
        int[] actual = new CourseSchedule2().findOrder(numCourses, prerequisites);
        String problem = (actual != null && actual.length == 0)
                ? null
                : "expected an empty array (no valid ordering exists)";
        report(name, problem, actual);
    }

    static void report(String name, String problem, int[] actual) {
        System.out.println((problem == null ? "PASS" : "FAIL") + " [" + name + "]");
        if (problem != null)
            System.out.println("  " + problem + "; got " + Arrays.toString(actual));
    }

    // null when the order is valid, otherwise a description of the first problem
    static String validate(int[] order, int numCourses, int[][] prerequisites) {
        if (order == null)
            return "returned null";
        if (order.length != numCourses)
            return "expected " + numCourses + " courses, got " + order.length;

        // position[c] = where course c sits in the order
        int[] position = new int[numCourses];
        Arrays.fill(position, -1);
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < order.length; i++) {
            int c = order[i];
            if (c < 0 || c >= numCourses)
                return "course " + c + " is out of range";
            if (!seen.add(c))
                return "course " + c + " appears more than once";
            position[c] = i;
        }

        for (int[] p : prerequisites) {
            int course = p[0], prereq = p[1];
            if (position[prereq] > position[course])
                return "course " + prereq + " must come before " + course;
        }
        return null;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> outMap = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int[] sol = new int[numCourses];
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
            sol[processed] = cur;
            processed++;
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

        // if not all courses are processed, otherwise empty
        return processed == numCourses ? sol : new int[0];
    }
}
