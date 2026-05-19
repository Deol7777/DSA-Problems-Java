public class TestPrefixTree {
    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();

        prefixTree.insert("dog");

        System.out.println(prefixTree.search("dog"));    // return true
        System.out.println(prefixTree.search("do"));     // return false
        System.out.println(prefixTree.startsWith("do")); // return true

        prefixTree.insert("do");

        System.out.println(prefixTree.search("do"));     // return true
    }
}
