public class KthSmallest {
            public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {4,3,5,2,null};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(kthSmallest(tree.root, 3));

    }


    public static int kthSmallest(TreeNode root, int k) {
        int[] sol = new int[]{0,-1};
        dfs(root,k, sol);
        return sol[1];
    }
    private static void dfs(TreeNode root, int k, int[] sol) {
        if(root == null)
            return;
        dfs(root.left, k, sol);
        if(sol[0] == k)
            return;
        sol[0]++;
        if(sol[0] == k) {
            sol[1] = root.val;
            return;
        }
        dfs(root.right, k, sol);
    }
}
