public class GasStation {
    
    public static void main(String[] args) {

        int[] gas = {1,2,3,4};
    int[] cost = {2,2,4,1};
    System.out.println(canCompleteCircuit(gas,cost));

    }

    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int start = 0;
        int dist = 0;
        int idx = 0;
        int travelled = 0;
        boolean circle = false;
        while (true) {
            
            //when arriving at a gas station we couldn't have reached (-ve gas)mark that as start
            if(dist < 0) {
                start = idx;
                dist = 0;
                travelled = 0;
                //if already completed a circle, no other station after the current start would work because we had already reached the end station(last index) from whichver the current start was
                if(circle)
                    return -1;
            }
            travelled++;
            
            //if we reached the start
            if(travelled == gas.length+1)
                return start;
            dist += gas[idx] - cost[idx];
            idx++;
            if(idx >= gas.length) {
                idx = idx%gas.length;
                circle = true;
            }
        }
    }
    //better 
    public static int canCompleteCircuit(int[] gas, int[] cost)
    {   
        int sum = 0;
        
        //if overall gas is length than the cost, return -1, otherwise guranteed a solution exists
        for (int i = 0; i < cost.length; i++) {
            sum += gas[i] - cost[i] ;
        }
        if(sum < 0)
            return -1;
        
        int start = 0;
        sum = 0;
        for (int i = 0; i < cost.length; i++) {
            sum += gas[i] - cost[i];
            //if we can't reach the next, that means we haven't found the soln yet.
            if(sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        return start;
    }
}
