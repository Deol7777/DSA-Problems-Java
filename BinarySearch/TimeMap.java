package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeMap {

    private HashMap<String, List<Pair>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {

        List<Pair> temp = map.get(key);
        if(temp != null) {
            temp.add(new Pair(value, timestamp));
        }
        else {
            
            List<Pair> pairList = new ArrayList<>();
            pairList.add(new Pair(value, timestamp));
            map.put(key, pairList);
        }

    }

    public String get(String key, int timestamp) {

        List<Pair> outer = map.get(key);
        if(outer == null)
            return "";
        if(outer.get(0).timestamp > timestamp)
            return "";
        
        int largestTime = outer.get(outer.size()-1).timestamp;
        if(largestTime < timestamp)
            return outer.get(outer.size()-1).value;
        
        int l = 0; int r = outer.size()-1;

        while (l <= r) {
            int m = l + (r-l)/2;
            
            int timeAtM = outer.get(m).timestamp;
            int timeAtR = outer.get(r).timestamp;
            
            if(timeAtM == timestamp)
                return outer.get(m).value;
            if(r - l <= 1)
            {
                if (timeAtR == timestamp)
                    return outer.get(r).value;
                return outer.get(l).value;
            } 
            if(timestamp < timeAtM)
                r = m-1;
            else
                l = m;
        }
        return outer.get(l).value;
            
    }

    static class Pair {
        public String value;
        public int timestamp;
        public Pair(String v, int t) {
            value = v;
            timestamp = t;
        }
    }



    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("alice", "happy", 1);  // store the key "alice" and value "happy" along with timestamp = 1.
        System.out.println(timeMap.get("alice", 1));           // return "happy"
        System.out.println(timeMap.get("alice", 2));           // return "happy", there is no value stored for timestamp 2, thus we return the value at timestamp 1.
        timeMap.set("alice", "sad", 10);    // store the key "alice" and value "sad" along with timestamp = 3.
        System.out.println(timeMap.get("alice", 7));  
    }



}
