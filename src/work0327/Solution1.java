package work0327;

import com.sun.org.apache.bcel.internal.generic.BIPUSH;

/**
 * 1、输入一个二叉树的root节点，检查当前二叉树是否轴对称
 * 2、输入一个链表的头节点，请将链表按升序排列后，返回其头节点
 */
 class BinaryTree {
    int val;
    BinaryTree right;
    BinaryTree left;

    public BinaryTree() {
    }

    public BinaryTree(int val) {
        this.val = val;
    }
}
public class Solution1 {


    BinaryTree root;

    public   boolean idSymmetric(BinaryTree root) {
        return check(root, root);
    }

    public boolean check(BinaryTree p, BinaryTree q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;

        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);

    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        root.right = new BinaryTree(2);
    }
}
