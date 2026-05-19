import java.util.Arrays;

public class NonOverlappingIntervals {

    public static void main(String[] args) {

        int[][] arr = { {1, 2}, {2, 4}, {3, 4} };
        System.out.println(eraseOverlapIntervals(arr));

    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int end = intervals[0][1];
        int toRemove = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                end = Math.min(end, intervals[i][1]);
                toRemove++;
            }
            else {
                end = intervals[i][1];
            }
        }

        return toRemove;
    }
}
