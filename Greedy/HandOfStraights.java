import java.util.Arrays;

public class HandOfStraights {
    public static void main(String[] args) {

        int[] hand = {1,2,3,6,2,3,4,7,8};
        //Arrays.sort(hand);
        //System.out.println(Arrays.toString(hand));
        System.out.println(isNStraightHand(hand, 3));

    }
    
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        double noGroupsD = hand.length / groupSize;
        if( noGroupsD % 1 != 0)
            return false;
        
        int start = 0;
        int noGroups = (int)noGroupsD;
        int[][] slots = new int[noGroups][2];
        for (int i = 0; i < slots.length; i++) {
            slots[i][0] = 9999;
            slots[i][1] = 0;
        }
        boolean fit = false;
        Arrays.sort(hand);
        for (int i = 0; i < hand.length; i++) {
            fit = false;
            for (int j = start; j < slots.length; j++) {
                if(hand[i] > slots[j][0]  + 1)
                    return false;
                if(slots[j][0] + 1 == hand[i] || slots[j][0] == 9999) {
                    fit = true;
                    slots[j][0] = hand[i];
                    slots[j][1]++;
                    if(slots[j][1] == groupSize)
                        start++;
                    break;
                }
            }
            if(!fit)
                return false;
        }
        return true;
    }
}
