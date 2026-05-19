import apple.laf.JRSUIUtils.Tree;

public class ValidBST {
    
        public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {1,2,3};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(isValidBST(tree.root));

    }

        //is wrong and doesnlt consider previous values earlier than parent
        public static boolean isValidBST2(TreeNode root) {
            if(root == null)
                return true;
            if(root.left != null && root.left.val >= root.val)
                return false;
            if(root.right != null && root.right.val <= root.val)
                return false;
            boolean isLeftValid = isValidBST2(root.left);
            if(!isLeftValid)
                return false;
            boolean isRightValid = isValidBST2(root.right);
            return isRightValid;

    }

    public static boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private static boolean dfs(TreeNode root, long min, long max) {
        if(root == null)
            return true;
        if(root.val < min || root.val > max)
            return false;
        boolean isLeftValid = dfs(root.left, min, root.val);
        if(!isLeftValid)
            return false;
        boolean isRightValid = dfs(root.right, root.val, max);
        return isRightValid;
    }
}
