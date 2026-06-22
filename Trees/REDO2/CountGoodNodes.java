package REDO2;

public class CountGoodNodes {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1, 2, -1, 3, 4 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(goodNodes(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static int goodNodes(TreeNode root) {
        int[] sol = new int[] { 0 };
        goodNodesHelper(root, sol, Integer.MIN_VALUE);
        return sol[0];
    }

    private static void goodNodesHelper(TreeNode root, int[] sol, int max) {
        if (root == null)
            return;
        if (root.val >= max)
            sol[0]++;
        goodNodesHelper(root.left, sol, Math.max(max, root.val));
        goodNodesHelper(root.right, sol, Math.max(max, root.val));

    }
}
