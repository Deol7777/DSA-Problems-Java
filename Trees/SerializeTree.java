import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {} ;
        tree.buildFromLevelOrder(levelOrder);

        TreeNode root2 = deserialize(serialize(tree.root));
        tree.root = root2;
        System.out.println(tree.toLevelOrderArray());

    }

    public static String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return "";
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null)
                    list.add(1001);
                else {
                    list.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = list.size() - 1;
        while (list.get(i) == 1001)
            i--;
        for (int j = 0; j <= i; j++) {
            sb.append(list.get(j));
            sb.append("+");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if( data == "")
            return null;
        String[] words = data.split("\\+");
        int[] digits = new int[words.length];
        
        for (int i = 0; i < digits.length; i++) {
            digits[i] = Integer.parseInt(words[i]);
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(digits[0]);
        int idx = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == 1001)
                    continue;
                if (idx < digits.length && digits[idx] != 1001) {
                    node.left = new TreeNode(digits[idx]);
                    queue.add(node.left);
                }
                idx++;
                if (idx < digits.length && digits[idx] != 1001) {
                    node.right = new TreeNode(digits[idx]);
                    queue.add(node.right);
                }
                idx++;
            }
        }
        return root;

    }
}
