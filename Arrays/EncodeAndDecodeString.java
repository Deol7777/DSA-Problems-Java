import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeString {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("lint", "code", "love", "you"));
        List<String> list1 = new ArrayList<>(Arrays.asList(""));

        String encoded = encode(list1);
        System.out.println(encoded);
        System.out.println(decode(encoded));
      }

      public static String encode(List<String> strs) {
        if (strs.isEmpty()) return "\"\"";
        StringBuilder sb = new StringBuilder();
        for(String str: strs) {
            sb.append(str);
            sb.append(":;:");
        }
        return sb.toString();
      }

      public static List<String> decode(String str) {
        String[] strarr = str.split(":;:");
        List<String> sol = new ArrayList<>(Arrays.asList(strarr));
        //System.out.println(Arrays.toString(strarr));
        return sol;
      }
}
