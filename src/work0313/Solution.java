package work0313;

import java.util.*;

/**
 * 作业：
 * 1、分析AVLtree remove方法，下节课进行抽查
 * 2、分析mergeBySentry方法，下节课抽查
 * 3、假设有一棵二叉树：
 *                     要求蛇形输出这棵二叉树，打印输出结果
 *     1
 *    2  3
 *   7  6 5   4
 * 4、给你一个链表的头节点，和一个特定值x，对链表进行分隔，使得所有小于x的节点都出现在大于x的节点之前，保证每个节点的初始相对位置不变。
 */
public class Solution {

    public List<List<Integer>> zigzagLevelOrder(Node root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Deque<Node> deque = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();

        deque.offer(root);

        for (int i = 0; !deque.isEmpty(); i++) {
            int size = deque.size();
            Node[] array = new Node[size];
            for (int j = 0; j < size; j++) {
                array[j] = deque.remove();
            }
            List<Integer> list = new ArrayList<>();
            lists.add(list);
            for (int k = 0; k < array.length; k++) {
                Node node = array[k];
                if (null != node) {
                    deque.offer(node.left);
                    deque.offer(node.right);
                }
            }
            for (int k = 0; k < array.length; k++) {
                int offset = k;
                //如果是偶数层，默认从0层开始，则从后往前打印 否则从前往后打印
                if (i % 2 != 0) {
                    offset = array.length - 1 - k;
                }
                Node node = array[offset];
                if (null != node) {
                    list.add(node.val);
                }
            }
            if (list.isEmpty()) {
                lists.remove(list);
            }
        }
        return lists;
    }

    public static void main(String[] args) {

        Node root = new Node(1,new Node(2,new Node(4),new Node(5)),new Node(3,new Node(6),new Node(7)));
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.zigzagLevelOrder(root);
        System.out.println(lists.get(1));
        System.out.println(lists.size());
        for (int i = 0; i < 3; i++) {
            System.out.println(lists.get(i));
        }
    }
}
class Node{
    int val;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
