package REDO2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        // [1,2,3,null,null,4,5]
        runTest("basic", new Integer[] { 1, 2, 3, null, null, 4, 5 });

        // []
        runTest("empty tree", new Integer[] {});

        // [1]
        runTest("single node", new Integer[] { 1 });

        // [1,2,null,3,null,4] -- left skew
        runTest("left skew", new Integer[] { 1, 2, null, 3, null, 4 });

        // [1,null,2,null,3,null,4] -- right skew
        runTest("right skew", new Integer[] { 1, null, 2, null, 3, null, 4 });

        // [1,2,3,4,5,6,7] -- full tree
        runTest("full tree", new Integer[] { 1, 2, 3, 4, 5, 6, 7 });

        // [10,20,30] -- multi-digit values
        runTest("multi-digit", new Integer[] { 10, 20, 30 });

        // [1,-2,-3] -- negative values
        runTest("negatives", new Integer[] { 1, -2, -3 });
    }

    private static void runTest(String name, Integer[] levelOrder) {
        BinaryTree tree = new BinaryTree();
        tree.buildFromLevelOrder(levelOrder);
        List<Integer> expected = tree.toLevelOrderArray();

        String data = "";
        try {
            data = serialize(tree.root);
            TreeNode ans = deserialize(data);

            BinaryTree rebuilt = new BinaryTree();
            rebuilt.root = ans;

            List<Integer> actual = rebuilt.toLevelOrderArray();
            boolean pass = expected.equals(actual);

            System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
            System.out.println("  serialized: \"" + data + "\"");
            System.out.println("  expected:   " + expected);
            System.out.println("  actual:     " + actual);
        } catch (Exception e) {
            System.out.println("ERROR [" + name + "] " + e);
            System.out.println("  serialized: \"" + data + "\"");
            System.out.println("  expected:   " + expected);
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder stb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    stb.append("A");
                    stb.append(";");
                    continue;
                } else
                    stb.append(cur.val);
                stb.append(";");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return stb.toString();

    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals(""))
            return null;
        int index = 0;
        int end = index;
        while (end < data.length() && data.charAt(end) != ';') {
            end++;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(data.substring(index, end)));
        index = end + 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode cur = queue.poll();
                // char valLeft = data.charAt(index++);
                // char valRight = data.charAt(index++);
                end = index;
                while (end < data.length() && data.charAt(end) != ';') {
                    end++;
                }
                String valLeft = data.substring(index, end);
                index = 1 + end++;
                while (end < data.length() && data.charAt(end) != ';') {
                    end++;
                }
                String valRight = data.substring(index, end);
                index = end + 1;

                if (valLeft.equals("A"))
                    cur.left = null;
                else {
                    cur.left = new TreeNode(Integer.parseInt(valLeft));
                    queue.add(cur.left);
                }
                if (valRight.equals("A"))
                    cur.right = null;
                else {
                    cur.right = new TreeNode(Integer.parseInt(valRight));
                    queue.add(cur.right);
                }

            }
        }
        return root;

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
