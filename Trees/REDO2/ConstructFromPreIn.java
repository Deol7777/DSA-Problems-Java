package REDO2;

import java.util.HashMap;

public class ConstructFromPreIn {

    public static void main(String[] args) {

        // int[] preorder = { 3, 9, 20, 15, 7 };
        // int[] inorder = { 9, 3, 15, 20, 7 };
        int[] preorder = { -1 };
        int[] inorder = { -1 };

        TreeNode root = buildTree(preorder, inorder);

        // print tree to verify
        BinaryTree tree = new BinaryTree();
        tree.root = root;
        tree.printTreeTopDown();

    }

    private static int preindex = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        // put everythin in post in map
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTreeHelper(preorder, inorder, 0, preorder.length - 1, map);
        //

        return root;

    }

    private static TreeNode buildTreeHelper(int[] preorder, int[] inorder, int l, int r,
            HashMap<Integer, Integer> map) {

        // base case
        if (l > r)
            return null;
        int rootValue = preorder[preindex];
        TreeNode root = new TreeNode(rootValue);
        preindex++;

        int mid = map.get(rootValue);
        root.left = buildTreeHelper(preorder, inorder, l, mid - 1, map);
        root.right = buildTreeHelper(preorder, inorder, mid + 1, r, map);
        return root;

    }

}
