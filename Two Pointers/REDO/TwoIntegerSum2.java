package REDO;

import java.util.Arrays;

public class TwoIntegerSum2 {
        public static void main(String[] args) {
            // original
            int[] a = new int[]{1,2,3,4};
            int[] expA = new int[]{1,2};
            int[] actA = twoSum(a, 3);
            System.out.println("Input: [1,2,3,4], target=3 -> expected=" + Arrays.toString(expA) + " actual=" + Arrays.toString(actA));

            // additional testcases
            int[] b = new int[]{2,7,11,15};
            int[] expB = new int[]{1,2};
            int[] actB = twoSum(b, 9);
            System.out.println("Input: [2,7,11,15], target=9 -> expected=" + Arrays.toString(expB) + " actual=" + Arrays.toString(actB));

            int[] c = new int[]{1,3,4,6,8,11};
            int[] expC = new int[]{4,5};
            int[] actC = twoSum(c, 14);
            System.out.println("Input: [1,3,4,6,8,11], target=14 -> expected=" + Arrays.toString(expC) + " actual=" + Arrays.toString(actC));

            int[] d = new int[]{-3,-1,0,2,4};
            int[] expD = new int[]{2,4};
            int[] actD = twoSum(d, 1);
            System.out.println("Input: [-3,-1,0,2,4], target=1 -> expected=" + Arrays.toString(expD) + " actual=" + Arrays.toString(actD));

            int[] e = new int[]{1,2,3,4,4,9};
            int[] expE = new int[]{4,5};
            int[] actE = twoSum(e, 8);
            System.out.println("Input: [1,2,3,4,4,9], target=8 -> expected=" + Arrays.toString(expE) + " actual=" + Arrays.toString(actE));

            int[] f = new int[]{1,5};
            int[] expF = new int[]{1,2};
            int[] actF = twoSum(f, 6);
            System.out.println("Input: [1,5], target=6 -> expected=" + Arrays.toString(expF) + " actual=" + Arrays.toString(actF));

            int[] g = new int[]{1,2,3,4};
            int[] expG = new int[]{};
            int[] actG = twoSum(g, 100);
            System.out.println("Input: [1,2,3,4], target=100 -> expected=" + Arrays.toString(expG) + " actual=" + Arrays.toString(actG));
      }

          public static int[] twoSum(int[] numbers, int target) {

            int i = 0; int j = numbers.length-1;
            int cur = 0;
            while(i < j) {
                cur = numbers[i] + numbers[j];
                if(cur < target)
                    i++;
                else if(cur > target)
                    j--;
                else
                    return new int[]{i+1, j+1}; 
            }
            return new int[]{};
          }
}
