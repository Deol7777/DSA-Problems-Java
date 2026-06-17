package REDO2;


public class LowestCommonAncestor {
        public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {5,3,8,1,4,7,9,null,2};
        tree.buildFromLevelOrder(levelOrder);
            TreeNode p = new TreeNode(3);
            TreeNode q = new TreeNode(8);
        System.out.println(lowestCommonAncestor(tree.root, p, q).val);
        // Optional: You can add code here to print or verify the tree structure
    }
    
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val <= q.val)
            return lcaHelper(root, p, q);
        return lcaHelper(root, q, p);

    }

    private static TreeNode lcaHelper(TreeNode root, TreeNode s, TreeNode b) {
        if(root == null)
            return null;
        if(s.val <= root.val && b.val>= root.val)
            return root;
        if(root.val == s.val || root.val == b.val)
            return root.val == s.val ? s : b;
        TreeNode fromLeft = lcaHelper(root.left, s, b);
        if(fromLeft != null)
            return fromLeft;
        return lcaHelper(root.right, s, b);
    }

}
