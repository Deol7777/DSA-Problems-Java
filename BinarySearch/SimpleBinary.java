package BinarySearch;

public class SimpleBinary {
    
    public static void main(String[] args) {
        int[] a = new int[]{5};
        System.out.println(binarySearchMain(a, 33));
    }

    private static int binarySearchMain(int[] a, int target) {

        int l = 0, r = a.length-1;
        return binarySearch(a,l,r,target);

    }

    private static int binarySearch(int[] a, int l, int r, int target) {

        while( l <=  r) {
            int mid = l + (r - l)/2;
            
            if(target == a[mid]) return mid;

            else if( target < a[mid] )
                return binarySearch(a, l, mid-1, target);
            
            else
                return binarySearch(a, mid+1, r, target);
        }
        return -1;
    }
}
