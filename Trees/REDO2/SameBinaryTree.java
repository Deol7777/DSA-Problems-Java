package REDO2;

import java.util.Stack;

public class SameBinaryTree {


    // Test cases
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();

        Integer[] levelOrder = {1,2,3,null,null,4};
        Integer[] levelOrder2 = {1,2,3,null,null,4};
        Integer[] levelOrder3 = {};
        tree.buildFromLevelOrder(levelOrder3);
        tree2.buildFromLevelOrder(levelOrder3);
        System.out.println(isSameTree(tree.root, tree2.root));
    }



    public static boolean isSameTree(TreeNode p, TreeNode q) {

            if(q== null && p == null)
                return true;
            if(q == null && p != null || p == null && q != null || p.val != q.val)
                return false;
            
            boolean isLeftSame = isSameTree(p.left, q.left);
            if(!isLeftSame)
                return false;
            return isSameTree(p.right, q.right);
    }
}
