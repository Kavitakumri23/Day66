
    import java.util.*;

public class PreOrderOrInorder {

        static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int val) {
                this.val = val;
            }
        }

        static int index = 0; // Global index for preorder array

        public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
            // 🔹 Step 1: Store inorder elements and their indices in a map
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inOrder.length; i++) {
                map.put(inOrder[i], i);
            }

            // 🔹 Step 2: Call recursive helper
            return helper(preOrder, inOrder, 0, inOrder.length - 1, map);
        }

        // 🔹 Recursive function to build the tree
        private static TreeNode helper(int[] preOrder, int[] inOrder, int left, int right, HashMap<Integer, Integer> map) {
            if (left > right) return null;

            // Pick current node value from preorder using 'index'
            int current = preOrder[index];
            index++;

            TreeNode node = new TreeNode(current);

            // If no children (leaf node)
            if (left == right) return node;

            // Get inorder index of current node
            int inIndex = map.get(current);

            // Build left and right subtrees
            node.left = helper(preOrder, inOrder, left, inIndex - 1, map);
            node.right = helper(preOrder, inOrder, inIndex + 1, right, map);

            return node;
        }

        // 🔹 Utility function to print inorder traversal (to verify)
        public static void printInorder(TreeNode root) {
            if (root == null) return;
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }

        public static void main(String[] args) {
            int[] preorder = {3, 9, 20, 15, 7};
            int[] inorder = {9, 3, 15, 20, 7};

            TreeNode root = buildTree(preorder, inorder);

            System.out.print("✅ Inorder of constructed tree: ");
            printInorder(root);
        }
    }

