package BinarySearch;

public class KokoEatingBananas {
    
    public static void main(String[] args) {
        int[] a = new int[]{25,10,23,4};
        System.out.println(minEatingSpeed(a, 4));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int maxBananas = Integer.MIN_VALUE;
        for (int i : piles) {
            maxBananas = Math.max(maxBananas, i);
        }

        int l = 1; int r = maxBananas;

        while( l < r) {
            int k = l + (r-l)/2;
            int passed = 0;
            for (int pile : piles) {
                passed += Math.ceil(((double)pile) / k);
                if(passed > h) {
                    l = k + 1;
                    break;
                }
            }
            if (passed <= h ) r = k;
        }
        return r;
    }
}
