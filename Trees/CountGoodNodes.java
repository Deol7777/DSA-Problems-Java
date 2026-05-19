public class CountGoodNodes {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1,2,-1,3,4 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(goodNodes(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static int goodNodes(TreeNode root) {
        int[] sol = new int[]{0};
        dfs(root, sol, Integer.MIN_VALUE);
        return sol[0];

    }

    private static void dfs(TreeNode cur, int[] sol, int max)
    {
        if(cur == null)
            return;
        if(cur.val >= max)
            sol[0]++;
        dfs(cur.left, sol, Math.max(max, cur.val));
        dfs(cur.right, sol, Math.max(max, cur.val));
    }
}
