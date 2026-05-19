public class CountSubstrings {
    public static void main(String args[]) {

        String str = "aaa";
        System.out.println(countSubstrings(str));
    }

    public static int countSubstrings(String s) {
        
        int count = s.length();

        //for odd
        for (int i = 0; i < s.length(); i++) {
            
            int j = i-1, k=i+1;
            while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))) {
                count++;
                j--; k++;
            }
        }

        //for even
        for (int i = 0, l = 1; l < s.length(); i++, l++) {
            int j = i; int k = l;
            while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))) {
                count++;
                j--; k++;
                
            }
        }
        return count;


    }
}
