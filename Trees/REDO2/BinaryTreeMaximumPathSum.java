package REDO2;

public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { -10, 9, 20, null, null, 15, 7 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(maxPathSum(tree.root));
    }

    private static int sol = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return sol;
    }

    private static int maxPathSumHelper(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxPathSumHelper(root.left);
        int right = maxPathSumHelper(root.right);
        int maxReturn = Math.max(root.val, Math.max(root.val + left, root.val + right));
        sol = Math.max(sol, Math.max(left + right + root.val, maxReturn));
        return maxReturn;

    }

}
