class PalindromicSubstrings {


    public static void main(String args[]) {

        String str = "abbc";
        System.out.println(longestPalindrome(str));
    }

    public static String longestPalindrome(String s) {
        
        int start=0; int end=0;
        int max = Integer.MIN_VALUE;

        //for odd
        for (int i = 0; i < s.length(); i++) {
            
            int j = i, k=i;
            while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))) {
                if(k - j + 1 > max) {
                    start = j;
                    max = k-j+1;
                    end = k;
                }
                j--; k++;
            }
        }

        //for even
        for (int i = 0, l = 1; l < s.length(); i++, l++) {
            int j = i; int k = l;
            while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))) {
                if(k - j + 1 > max) {
                    start = j;
                    max = k-j+1;
                    end = k;
                }
                j--; k++;
                
            }
        }
        return s.substring(start, end+1);


    }
}