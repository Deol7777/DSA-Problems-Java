public class MaxDepthOfBinaryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1,2,3,null,null,4,null,null,5 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(maxDepth(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static int maxDepth(TreeNode root) {
        //int[] sol = new int[]{0};
        return maxDepthHelper(root, 0);
        //return sol[0];
    }

    private static int maxDepthHelper(TreeNode cur, int depth) {
        if(cur == null)
            return depth;
        depth = Math.max(maxDepthHelper(cur.left, depth+1), maxDepthHelper(cur.right, depth+1));
        return depth;
        
    }
}
