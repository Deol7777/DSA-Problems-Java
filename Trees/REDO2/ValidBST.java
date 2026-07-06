package REDO2;

public class ValidBST {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 2, 1, 3 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(isValidBST(tree.root));

    }

    public static boolean isValidBST(TreeNode root) {

        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    private static boolean isValidBSTHelper(TreeNode root, long lowest, long highest) {

        if (root == null)
            return true;

        if (root.val <= lowest || root.val >= highest)
            return false;

        return (isValidBSTHelper(root.left, lowest, root.val) && isValidBSTHelper(root.right, root.val, highest));

    }

}
