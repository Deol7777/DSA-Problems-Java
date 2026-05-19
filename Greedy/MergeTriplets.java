public class MergeTriplets {
    public static void main(String[] args) {

        int[][] triplets = {
    {2, 5, 3},
    {1, 8, 4},
    {1, 7, 5}
};

int[] target = {2, 7, 5};
        //Arrays.sort(hand);
        //System.out.println(Arrays.toString(hand));
        System.out.println(mergeTriplets(triplets, target));

    }
    
    public static boolean mergeTriplets2(int[][] triplets, int[] target) {
        
        //first row to check if exact digit is there. 2nd row is if something smaller exists
        boolean[][] sol = new boolean[2][3];
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[0].length; j++) {
                sol[i][j] = false;
            }
        }


        for (int i = 0; i < triplets.length; i++) {
            
            //if anything in cur is bigger than the target, can't use it
            if(!isValid(i, triplets, target))
                continue;
            for (int j = 0; j < 3; j++) {
                
                if(triplets[i][j] == target[j]) {
                    
                    //if 2nd equal is found, also make the smaller part true
                    if(sol[0][j] == true )
                        sol[1][j] = true;
                    sol[0][j] = true;
                }
                else if(triplets[i][j] < target[j])
                    sol[1][j] = true;
            }
        }

        //final check to return the ans
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[0].length; j++) {
                if(!sol[i][j])
                    return false;
            }
        }
        return true;


    }

    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        
        //to check if exact digit is there
        boolean[] sol = new boolean[3];
        for (int i = 0; i < sol.length; i++)
                sol[i] = false;
        
        //can be used exit early (or you can just check the sol at end)
        int found = 0;

        for (int i = 0; i < triplets.length; i++) {
            
            //if anything in cur is bigger than the target, can't use it
            if(!isValid(i, triplets, target))
                continue;
            for (int j = 0; j < 3; j++) {
                if(triplets[i][j] == target[j]) {
                    //only update if this position wasn;t already found
                    found = sol[j] ? found : found+1;
                    if(found == 3)
                        return true;
                    sol[j] = true;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int idx, int[][] triplets, int[] target) {
        for (int j = 0; j < target.length; j++) {
            if(triplets[idx][j] > target[j])
                return false;
        }
        return true;
    }
}
