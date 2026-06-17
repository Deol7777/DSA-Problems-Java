package REDO2;

public class SubTreeOfAnotherTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();

        Integer[] levelOrder = {1,2,3,4,5,null,null,6};
        Integer[] levelOrder2 = {2,4,5};
        tree.buildFromLevelOrder(levelOrder);
        tree2.buildFromLevelOrder(levelOrder2);

        System.out.println(isSubtree(tree.root, tree2.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null)
            return true;
        if(root == null)
            return false;
        if(root.val == subRoot.val)
            if(isSameTree(root, subRoot))
                return true;
        boolean inLeft = isSubtree(root.left, subRoot);
        if(inLeft)
            return true;
        return isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null) {
            if(root == null && subRoot == null)
                return true;
            return false;
        }

        if(root.val != subRoot.val)
            return false;
        boolean isLeftSame = isSubtree(root.left, subRoot.left);
        if(!isLeftSame)
            return false;
        return isSubtree(root.right, subRoot.right);

    }
}
