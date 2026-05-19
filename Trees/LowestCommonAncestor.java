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
    
    // public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     TreeNode[] sol = new TreeNode[]{new TreeNode(-1)};
    //     lCaHelper(root, sol, p.val, q.val);
    //     return sol[0];

    // }

    // private static boolean lCaHelper(TreeNode cur, TreeNode[] sol, int p, int q) {
    //     if(cur == null)
    //         return false;
    //     boolean isCurValid = false;
    //     if(cur.val == p || cur.val == q)
    //         isCurValid = true;
    //     boolean l = lCaHelper(cur.left, sol, p, q);
    //     if(sol[0].val != -1)
    //         return true;
    //     boolean r = lCaHelper(cur.right, sol, p, q);

    //     if(l || r) {
    //         if(isCurValid)
    //             sol[0] = cur;
    //         else if(l && r)
    //             sol[0] = cur;
    //         return true;
    //     }
    //     return isCurValid;
    // }

    private static TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
        if(cur == null)
            return null;
        if(cur.val == p.val || cur.val == q.val)
            return cur;
        TreeNode l = lowestCommonAncestor(cur.left, p, q);
        TreeNode r = lowestCommonAncestor(cur.right, p, q);

        if(l != null && r != null) 
            return cur;
        return l == null ? r : l;
    }
}
