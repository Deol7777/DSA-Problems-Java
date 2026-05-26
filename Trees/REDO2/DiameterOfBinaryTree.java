package REDO2;

public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1, 2 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(diameterOfBinaryTree(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static int diameterOfBinaryTree(TreeNode root) {

        int[] sol = new int[] { Integer.MIN_VALUE };
        dfs(root, sol);
        return sol[0];

    }

    private static int dfs(TreeNode root, int[] sol) {
        if (root == null)
            return 0;
        int leftMax = dfs(root.left, sol);
        int rightMax = dfs(root.right, sol);
        sol[0] = Math.max(sol[0], rightMax + leftMax);
        return Math.max(leftMax, rightMax) + 1;
    }

}
