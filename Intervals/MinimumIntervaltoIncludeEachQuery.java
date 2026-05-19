import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumIntervaltoIncludeEachQuery {

    public static void main(String[] args) {

        int[][] intervals = {
                { 4, 5 }, { 5, 8 }, { 1, 9 }, { 8, 10 }, { 1, 6 }
        };
        int[] queries = { 7, 9, 3, 9, 3 };
        System.out.println(Arrays.toString(minInterval(intervals, queries)));

    }


    //very good attempt - unfortunately times out on leetcode. See neetcode solution
    public static int[] minInterval(int[][] intervals, int[] queries) {
        // sort the array
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // before sorting queries put indeces in hashmap
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            List<Integer> temp = map.getOrDefault(queries[i], new ArrayList<>());
            temp.add(i);
            map.put(queries[i], temp);
        }
        Arrays.sort(queries);

        int[] result = new int[queries.length];
        Arrays.fill(result, Integer.MAX_VALUE);
        int intStart = 0;
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            while (intStart < intervals.length && intervals[intStart][1] < q)
                intStart++;

            int localIntStart = intStart;
            while (localIntStart < intervals.length && intervals[localIntStart][0] <= q) {
                if (intervals[localIntStart][0] <= q && intervals[localIntStart][1] >= q) {
                    result[map.get(q).get(0)] = Math.min(result[map.get(q).get(0)],
                            intervals[localIntStart][1] - intervals[localIntStart][0] + 1);
                }
                localIntStart++;
            }

            //update for other indeces as well where this value is located
            for (int j = 1; j < map.get(q).size(); j++) {
                result[map.get(q).get(j)] = result[map.get(q).get(0)];
            }
        }

        for (int i = 0; i < result.length; i++)
            result[i] = result[i] == Integer.MAX_VALUE ? -1 : result[i];

        return result;
    }
}
