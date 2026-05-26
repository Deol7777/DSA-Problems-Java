package REDO2;

public class BalancedBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {1,2,3,null,null,4,null,5};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(isBalanced(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }


    public static boolean isBalanced(TreeNode root) {
        int[] sol = new int[]{0};
        dfs(root, sol);
        return sol[0] == 0 ? true : false;
    }



    private static int dfs(TreeNode root, int[] sol) {
        if(root == null || sol[0] == -1)
            return 0;
        int leftMax = dfs(root.left, sol);
        int rightMax = dfs(root.right, sol);
        if(Math.abs(rightMax - leftMax) > 1)
            sol[0] = -1;
        return Math.max(leftMax, rightMax) + 1;


    }
}
