import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public static void main(String[] args) {

        int[][] arr = { { 1, 2 }, { 3, 5 }, { 9, 10 } };

        int[][] sol = insert(arr, new int[] { 5, 9 });
        for (int[] row : sol) {
            System.out.print(Arrays.toString(row) + "  ");
        }

    }

    //both work but bottom one is way better to code
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] { { newInterval[0], newInterval[1] } };
        List<int[]> sol = new ArrayList<>();
        // check the startnig point
        int start = newInterval[0];
        int end = newInterval[1];
        int newEnd = newInterval[1];
        int i = 0;
        while (i < intervals.length && start >= intervals[i][0]) {
            sol.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }

        // check left
        if (i > 0 && intervals[i - 1][1] >= start) {
            sol.remove(sol.size() - 1);
            start = intervals[i - 1][0];
            newEnd = Math.max(end, intervals[i - 1][1]);
        }

        // check right
        while (i < intervals.length && end >= intervals[i][0]) {
            newEnd = Math.max(intervals[i][1], newEnd);
            i++;
        }
        sol.add(new int[] { start, newEnd });
        while (i < intervals.length) {
            sol.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }

        return sol.toArray(new int[0][]);
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> sol = new ArrayList<>();
        int i = 0;

        // add all the ones which end before newInterval starts
        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            sol.add(intervals[i]);
            i++;
        }

        // adjust left and right for new interval. right should go as far as new
        // interval end is greater than start of intervals to right
        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        sol.add(newInterval);

        while (i < intervals.length) {
            sol.add(intervals[i]);
            i++;
        }

        return sol.toArray(new int[sol.size()][]);

    }

}
