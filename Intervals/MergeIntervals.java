import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {

        int[][] arr = { { 1, 2 }, { 3, 5 }, { 5, 10 } };

        int[][] sol = merge(arr);
        for (int[] row : sol) {
            System.out.print(Arrays.toString(row) + "  ");
        }

    }

    public static int[][] merge(int[][] intervals) {
        // sort by start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> sol = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];

        // start from second. if cur is part of prev update start and end, otherwise add
        // to list and update start and end
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
            } else {
                sol.add(new int[] { start, end });
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }

        // still need to end the last one
        sol.add(new int[] { start, end });

        return sol.toArray(new int[0][]);

    }

}
