import java.util.HashSet;

class ContainsDuplicate {
  public static void main(String[] args) {
    int[] a = {1, 2, 3, 3};
    System.out.println(hasDuplicate(a));
  }

  public static boolean hasDuplicate(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for(int i: nums) {
      if ( set.contains(i))
        return true;
      else
        set.add(i);
    }

    return false;
  }
}

