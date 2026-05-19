public class KthSmallest {
        public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {4,3,5,2,null};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(kthSmallest(tree.root, 3));

    }


    public static int kthSmallest(TreeNode root, int k) {
        int[] sol = new int[]{0,-1};
        dfs(root, k, sol);
        return sol[1];
    }

    private static void dfs(TreeNode cur, int k, int[] sol) {
        if(cur == null)
            return;
        dfs(cur.left, k, sol);
        sol[0]++;
        if(sol[0] == k) {
            sol[1] = cur.val;
            return;
        }
        dfs(cur.right, k, sol);
        
    }
    
}
