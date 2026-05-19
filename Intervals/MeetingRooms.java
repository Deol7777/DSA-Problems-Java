import java.util.Arrays;
import java.util.List;

public class MeetingRooms {

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20));
        System.out.println(canAttendMeetings(intervals));
    }

    public static boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.size() == 0)
            return true;
        // put everything into array
        int[][] arr = new int[intervals.size()][2];
        for (int i = 0; i < arr.length; i++) {
            Interval in = intervals.get(i);
            arr[i][0] = in.start;
            arr[i][1] = in.end;
        }

        // sort the array
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int end = arr[0][1];

        // if start of cur is less than end of prev than not possible
        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0];
            if (end > start)
                return false;
            end = arr[i][1];
        }
        return true;

    }

    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
