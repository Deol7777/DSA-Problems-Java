public class DiameterOfBinaryTree {
        public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1,null,2,3,4,5};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(diameterOfBinaryTree(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        int[] sol = new int[]{0};
        maxDiameterHelper(root, sol);
        return sol[0];

    }

    public static int maxDiameterHelper(TreeNode cur, int[] sol) {
        if(cur == null)
            return 0;
        int left = maxDiameterHelper(cur.left, sol);
        int right = maxDiameterHelper(cur.right, sol);
        sol[0] = Math.max(sol[0], left+right);
        return Math.max(left,right) + 1; 
    }
}
