import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1, 2, 3 };
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(rightSideView(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> sol = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                    if (i == size - 1) 
                        sol.add(cur.val);
                    if(cur.left != null)
                        queue.add(cur.left);
                    if(cur.right != null)
                        queue.add(cur.right);
            }
        }
        return sol;
    }
}
