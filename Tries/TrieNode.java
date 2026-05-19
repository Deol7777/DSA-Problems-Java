import java.util.HashMap;

public class TrieNode {
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
