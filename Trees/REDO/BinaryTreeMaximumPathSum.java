public class BinaryTreeMaximumPathSum {

    int sol2 = Integer.MIN_VALUE;
      
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {-15,10,20,null,null,15,5,-5};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(maxPathSum(tree.root));
      }

    public static int maxPathSum(TreeNode root) {
        int[] sol = new int[]{Integer.MIN_VALUE};
        dfs(root, sol);
        return sol[0];

    }

    private static int dfs(TreeNode node, int[] sol) {
        if(node == null)
                return 0;
        int left = dfs(node.left, sol);
        int right = dfs (node.right, sol);
        int max = getMax(left, right, node.val);
        sol[0] = Math.max(max, sol[0]);
        sol[0] = Math.max(sol[0], left + right + node.val);
        return max;
    }

    private static int getMax( int l, int r, int c) {
        int a = Math.max(l + c, r + c);
        int b = Math.max(a, c);
        return b;
    }
       

        
}
