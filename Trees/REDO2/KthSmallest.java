public class KthSmallest {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 4, 3, 5, 2, null };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(kthSmallest(tree.root, 3));

    }

    int counter = 0;
    int smallest = -1;

    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return k;
    }

    private void helper(TreeNode root, int k) {

        if (root == null)
            return;

        helper(root.left, k);
        if (smallest != -1)
            return;
        counter++;
        if (counter == k)
            smallest = root.val;
        else {
            helper(root.right, k);
        }
    }

}
