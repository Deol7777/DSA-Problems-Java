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
            dfs2(root, sol);
            return sol[0];
        }

        private static int dfs(TreeNode root, int[] sol) {
            if(root == null)
                return -1001;
            int c = root.val;
            int l = dfs(root.left, sol);
            int r = dfs(root.right, sol);
            int toReturn = getMax(l+c,r+c,c);
            sol[0] = getMax(sol[0], l,r,toReturn, l+r+c);
            return toReturn;
        }

        private static int getMax(int... items) {
            int max = Integer.MIN_VALUE;
            for(int i: items)
                max = Math.max(max, i);
            return max;
        }

        private static int dfs2(TreeNode root, int[] sol) {

            if(root == null)
                return 0;
            
            int maxFromLeft = Math.max(dfs2(root.left, sol), 0);
            int maxFromRight = Math.max(dfs2(root.right, sol), 0);
            
            sol[0] = Math.max(sol[0], maxFromLeft + maxFromRight + root.val);
            return Math.max(maxFromLeft + root.val, maxFromRight + root.val);

        }
}
