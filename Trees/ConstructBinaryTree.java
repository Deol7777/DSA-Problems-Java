import java.util.HashMap;

public class ConstructBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        int[] predex = new int[]{1,2,3,4};
        int[] ordex = new int[]{2,1,3,4};
        tree.root = buildTree(predex, ordex);
        System.out.println(tree.toLevelOrderArray());

        // Optional: You can add code here to print or verify the tree structure
    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //store the indeces of inorder array as keys in hashmap.
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i );
        }
        //maintaingn global index for preorder array. bascially the node we create at each step
        int[] predex = new int[]{0};
        return dfs(0, preorder.length-1, predex, preorder, map);
    }

    private static TreeNode dfs(int l, int r, int[] preIdx, int[] preorder, HashMap<Integer, Integer> map) {
        
        if(l > r) {
            return null;
        }
        //current value of node we create
        int curVal = preorder[preIdx[0]];
        preIdx[0]++;
        TreeNode cur = new TreeNode(curVal);
        
        //the index of the value we just created in the inorder array. Everything to the left of this index will go in 
        //left subtree of cur, everything to the right will go in right. We update the l and r pointer respectively in the subsequent call.
        int mid = map.get(curVal);
        cur.left = dfs(l, mid - 1, preIdx, preorder, map);
        cur.right = dfs(mid + 1, r, preIdx, preorder, map);
        return cur;

    }
}
