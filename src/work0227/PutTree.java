package work0227;

public class PutTree {
    public class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;
    }


    /**
     * 树的中序遍历
     * 递推公式：inOrder(r) :  print inOrder(r.left) ---> print r.data --->  print inOrder(r.right)
     */
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    /**
     * 树的后序遍历
     * 递推公式：postOrder(r): print  postOrder(r.left)  --->  print postOrder(r.right)  --->  print  r.data
     */
    private void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);

    }

    /**
     * 树的分层遍历
     */
}
