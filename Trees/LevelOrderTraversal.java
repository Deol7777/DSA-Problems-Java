import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        Integer[] levelOrder = { 1,2,3,4,5,6,7};
        tree.buildFromLevelOrder(levelOrder);

        System.out.println(levelOrder(tree.root));
        // Optional: You can add code here to print or verify the tree structure
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> parent = new LinkedList<>();
        Queue<TreeNode> child = new LinkedList<>();
        List<List<Integer>> sol = new ArrayList<>();
        if (root == null)
            return sol;
        List<Integer> temp = new ArrayList<>();
        temp.add(root.val);
        sol.add(temp);
        parent.offer(root);
        while (!parent.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            while (!parent.isEmpty()) {
                TreeNode cur = parent.poll();
                if (cur.left != null) {
                    child.add(cur.left);
                    sublist.add(cur.left.val);
                }
                if (cur.right != null) {
                    child.add(cur.right);
                    sublist.add(cur.right.val);
                }
            }
            if(sublist.size() != 0)
                sol.add(sublist);
            Queue<TreeNode> tempQ = parent;
            parent = child;
            child = tempQ;
        }
        return sol;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> parent = new LinkedList<>();
        List<List<Integer>> sol = new ArrayList<>();
        parent.offer(root);
        while(!parent.isEmpty()) {
            int size = parent.size();
            List<Integer> sublist = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = parent.poll();
                if(cur != null) {
                    sublist.add(cur.val);
                    parent.add(cur.left);
                    parent.add(cur.right);
                }
            }
            if(sublist.size() > 0)
                sol.add(sublist);
        }
        return sol;
    }
}
