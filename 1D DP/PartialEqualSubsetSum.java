import java.util.HashSet;

public class PartialEqualSubsetSum {
    public static void main(String args[]) {

        int[] a = new int[]{1,2,3,4};
        System.out.println(canPartition(a));
    }

    public static boolean canPartition(int[] nums) {

        HashSet<Integer> set  = new HashSet<>();
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 == 1)
            return false;
        for (int n : nums) {
            HashSet<Integer> tempSet = new HashSet<>();
            for(Integer i: set) {
                tempSet.add(i + n);
            }
            set.add(n);
            for(Integer i: tempSet) {
                set.add(i);
                if(set.contains(sum/2))
                    return true;
            }
            tempSet.clear();

        }
        return false;
    }
}
