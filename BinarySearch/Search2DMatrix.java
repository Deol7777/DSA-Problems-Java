package BinarySearch;

public class Search2DMatrix {

    public static void main(String[] args) {
        int[][] a = {
            {1},
            {3}
        };
        System.out.println(searchMatrix(a, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        
        int l = 0; int r = matrix.length-1;
        int row = binarySearchRow(matrix, l, r, target);
        System.out.println(row);
        return false;
        //return row == -1 ? false : binarySearchColumn(matrix, 0, matrix[0].length-1, target, row);

    }

    private static int binarySearchRow(int[][] a, int l, int r, int target) {

        while( l <=  r) {
            int mid = l + (r - l)/2;
            
            if(target == a[mid][0]) return mid;

            else if( target < a[mid][0] )
                return binarySearchRow(a, l, mid-1, target);
            
            else{
                    if(target <= a[mid][a[0].length-1])
                        return mid;
                    if(r - l <= 1) {
                        if(target <= a[r][a[0].length-1])
                            return r;
                        else
                            return -1;
                    }
                    return binarySearchRow(a, mid, r, target);
                }
        }
        return -1;
    }

    private static boolean binarySearchColumn(int[][]a, int l, int r, int target, int row) {
        
        while ( l <= r) {
            int mid = l + (r-l)/2;

            if(a[row][mid] == target)
                return true;

            else if ( target < a[row][mid])
                return binarySearchColumn(a, l, mid-1, target, row);
            else
                return binarySearchColumn(a, mid+1, r, target, row);
        }
        return false;
    }

}
