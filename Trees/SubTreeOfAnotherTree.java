public class SubTreeOfAnotherTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();

        Integer[] levelOrder = {1,2,3,4,5,null,null,6};
        Integer[] levelOrder2 = {2,4,5};
        Integer[] levelOrder3 = {};
        tree.buildFromLevelOrder(levelOrder);
        tree2.buildFromLevelOrder(levelOrder2);

        System.out.println(isSubtree(tree.root, tree2.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null)
            return true;
        if(root == null)
            return false;

        if(root.val == subRoot.val) {
            if(isSameTree(root, subRoot))
                return true;
        }
        boolean fromLeft = isSubtree(root.left, subRoot);
        if(fromLeft)
            return true;
        boolean fromRight = isSubtree(root.right, subRoot);
        return fromRight;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
    
    if( q == null || p == null) {
        if( q == null && p == null)
            return true;
        else
            return false;
    }
    if(p.val != q.val)
        return false;
    
    boolean isLeftSame = isSameTree(p.left, q.left);
    if(!isLeftSame)
        return false;
    boolean isRightSame = isSameTree(p.right, q.right);
    return isRightSame;

    }
}
