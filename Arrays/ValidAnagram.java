public class ValidAnagram {
    public static void main(String[] args) {
        String a = "abc";
        String b = "bcv";

        ValidAnagram ana = new ValidAnagram();
        System.out.println(ana.isAnagram(a, b));
      }

      public boolean isAnagram(String s, String t) {
        int[] alphabets = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabets[(int)s.charAt(i) - 97] ++;
        }

        for (int i = 0; i < t.length(); i++) {
            alphabets[(int)t.charAt(i) - 97]--;
        }

        for(int i : alphabets){
            if(i != 0)
                return false;
        }
        return true;
      }
}
