import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinMeetingRooms {
    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
            new Interval(1, 5),
            new Interval(5, 10),
            new Interval(10, 15),
            new Interval(15, 20)
        );
        System.out.println(minMeetingRooms(intervals));
    }

    // approach: add the end of meetings to minheap. For every new meeting remove
    // the ends in heap which end earlier than when the current starts, which means
    // that the current is not concurrent with those ones and nor will any future
    // meetings be. The ones that remain in heap are concurrent with this meeting
    // and heap size is the min days required to have these meetings. The max heap
    // size at any point is the answer.
    public static int minMeetingRooms(List<Interval> intervals) {
        if (intervals.size() == 0)
            return 0;
        // put everything into array
        int[][] arr = new int[intervals.size()][2];
        for (int i = 0; i < arr.length; i++) {
            Interval in = intervals.get(i);
            arr[i][0] = in.start;
            arr[i][1] = in.end;
        }

        // sort the array
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int days = 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(arr[0][1]);

        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0];
            // remove heap top where end is less than cur start
            while (!minHeap.isEmpty() && minHeap.peek() <= start)
                minHeap.poll();
            minHeap.add(arr[i][1]);
            days = Math.max(days, minHeap.size());
        }
        return days;

    }

    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
