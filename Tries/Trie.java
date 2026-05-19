import java.util.HashMap;

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        insert(this.root, word, 0);
    }

    private static void insert(TrieNode node, String word, int index) {
        Character c = word.charAt(index);
        TrieNode next = node.followUps.get(c);
        if (next == null) {
            TrieNode newNode = new TrieNode(c);
            node.followUps.put(c, newNode);
            if (index == word.length() - 1) {
                newNode.endOfWord = true;
                return;
            }
            insert(newNode, word, index + 1);

        } else {
            if (index == word.length() - 1) {
                next.endOfWord = true;
                return;
            }
            insert(next, word, index + 1);
        }
    }

    public boolean search(String word) {
        return word.length() == 0 ? false : search(this.root, word, 0, false);
    }

    private static boolean search(TrieNode node, String word, int index, boolean prefixSearch) {
        Character c = word.charAt(index);
        TrieNode next = node.followUps.get(c);
        if (next == null)
            return false;
        if (index == word.length() - 1) {
            return prefixSearch ? true : next.endOfWord;
        }
        return search(next, word, index + 1, prefixSearch);

    }

    public boolean startsWith(String prefix) {
        return prefix.length() == 0 ? false : search(this.root, prefix, 0, true);
    }

    static class TrieNode {
        Character val;
    HashMap<Character, TrieNode> followUps;
    boolean endOfWord;
        public TrieNode() {
            endOfWord = false;
            followUps = new HashMap<>();
        }

        public TrieNode(Character val) {
            this.val = val;
            endOfWord = false;
            followUps = new HashMap<>();
        }

        public TrieNode(Character val, boolean isEnd) {
            this.val = val;
            this.endOfWord = isEnd;
            followUps = new HashMap<>();
        }
    }
}
