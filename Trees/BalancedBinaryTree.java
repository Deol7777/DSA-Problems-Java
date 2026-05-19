public class BalancedBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {1,2,3,null,null,4};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(isBalanced(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }


    public static boolean isBalanced(TreeNode root) {
        int[] sol = new int[]{0};
        isBalancedHelper(root, sol);
        return sol[0] == 0;
    }

    public static int isBalancedHelper(TreeNode cur, int[] sol) {
        if(cur == null)
            return 0;
        int left = isBalancedHelper(cur.left, sol);
        if(sol[0] == 1)
            return -1;
        int right = isBalancedHelper(cur.right, sol);
        if(Math.abs(left - right) > 1 )
            sol[0] = 1;
        return Math.max(left, right) + 1;

    }
}
