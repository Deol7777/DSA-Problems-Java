package REDO2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    TreeNode root;
    BinaryTree(){
        root = null;
    };
    BinaryTree(int value) {
        this.add(value);
    }

    // Public add method to start recursion
    public void add(int value) {
        root = add(root, value);
    }

    // Recursive add method (private helper)
    private TreeNode add(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.val) {
            node.left = add(node.left, value);
        } else {
            node.right = add(node.right, value);
        }
        return node;
    }

public void printTreeTopDown() {
    printTreeTopDown(root, 0);
}

private void printTreeTopDown(TreeNode node, int indent) {
    if (node == null) return;

    for (int i = 0; i < indent; i++) {
        System.out.print("    "); // 4 spaces per indent level
    }
    System.out.println(node.val);

    printTreeTopDown(node.left, indent + 1);
    printTreeTopDown(node.right, indent + 1);
}

// Accepts Integer[] so we can include `null` as a value
    public void buildFromLevelOrder(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return;

        root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }
    }

    public List<Integer> toLevelOrderArray() {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node == null) {
            result.add(null);
            continue;
        }

        result.add(node.val);
        queue.add(node.left);
        queue.add(node.right);
    }

    // Remove trailing nulls to match compact array style
    int i = result.size() - 1;
    while (i >= 0 && result.get(i) == null) {
        result.remove(i);
        i--;
    }

    return result;
}





}
