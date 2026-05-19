import java.util.Collections;
import java.util.PriorityQueue;

//have 2 heap, l is max and r is min. Always make sure they are either equal length or the left is at max one
//larger than the right one (when total entries are odd ) (r shud never be larger)
public class MedianFinder {
    
    PriorityQueue<Integer> l;
    PriorityQueue<Integer> r;
    int elements;
    public MedianFinder() {
        l = new PriorityQueue<>(Collections.reverseOrder());
        r = new PriorityQueue<>();
        elements = 0;
    }
    
    public void addNum(int num) {
        if(elements == 0 || elements == 1) {
            if(elements == 0)
                l.add(num);
            if(elements == 1)
            {
                if(num > l.peek())
                    r.add(num);
                //if 2nd entry smaller than 1st, then we need to switch them in heaps
                else{
                    r.add(l.poll());
                    l.add(num);
                }
            }
            elements++;
            return;
        }
        
        if ( num <= r.peek())
            l.add(num);
        else
            r.add(num);
        
        elements++;

        //if sizes according to top condition then okay and return
        if(l.size() == r.size() || l.size() == r.size() + 1)
            return;
        
        PriorityQueue<Integer> big = l.size() > r.size() ? l : r;
        PriorityQueue<Integer> small = l.size() > r.size() ? r : l;
        small.add(big.poll());

    }
    
    public double findMedian() {
        if(elements % 2 == 0)
            return ((double)r.peek() + l.peek())/2;
        return l.peek();
    }

}
