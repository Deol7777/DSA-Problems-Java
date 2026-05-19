import java.util.Arrays;

public class TwoIntegerSum {
    
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(twoSum(a, 3)));
      }

      public static int[] twoSum(int[] numbers, int target) {
        int i = 0; int j = numbers.length -1;
        while (true) {
            if(numbers[i] + numbers[j] == target) {
                return new int[]{i,j};
            }
            if(numbers[i] + numbers[j] > target) {
                j--;
            }
            else
                i++;
        }
      }
    
}
