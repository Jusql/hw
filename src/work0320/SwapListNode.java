package work0320;

import work0227.LevelTraverseTree;

import java.nio.file.NotDirectoryException;

/**
 * 作业：
 * 1、编写一个方法，求一个数组中的第二大元素
 * 2、写一个栈溢出的程序例子
 * 3、写一个OutofMemery的程序，并解释，为什么会内存溢出
 * 4、给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。注意：不要修改链表节点内部的值。
 *     比如：1->2->3->4
 *     结果：2->1->4->3
 */
class Node{
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
public class SwapListNode {


    private Node swapNode(Node node) {
        if( null == node ||  null == node.next ){
            return node;
        }
//        Node first = node.next;
        Node rest = node.next.next;
        Node first = node.next;
        first.next = node;
        node.next = swapNode(rest);
//        while (node.next.next != null) {
////     if(node.next != null && node.next.next != null){
//            node.next = node.next.next;
//            node.next.next = node;
//            swapNode(node.next);
////        }
//        }
        return first;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
//        Node first = node;
//        node.next = node.next.next;
//        first.next.next = node;


        SwapListNode swapListNode = new SwapListNode();
       Node  first =swapListNode.swapNode(node);
        while (first != null) {
            System.out.println(first.val);
            first = first.next;
//        }
        }

    }
}
