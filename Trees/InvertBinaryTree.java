public class InvertBinaryTree {
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = {1,2,3,4,5,6,7};
        tree.buildFromLevelOrder(levelOrder);

        invertTree(tree.root);
        System.out.println(tree.toLevelOrderArray());
        // Optional: You can add code here to print or verify the tree structure
    }

    public static TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
