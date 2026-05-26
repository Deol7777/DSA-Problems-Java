package REDO2;

public class MaxDepthOfBinaryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1,null,2 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(maxDepth(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static int maxDepth(TreeNode root) {
        return dfs(root, 1); 
    }

    private static int dfs(TreeNode root, int max) {
        if( root == null)
            return max-1;
        
        int leftMax = dfs(root.left, max + 1);
        int rightMax = dfs(root.right, max+ 1);
        return Math.max(leftMax, rightMax);

    }
}
