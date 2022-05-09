package work0227;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * 合并两个升须排列的链表为一个链表，
 */
public class Solution {

    public LinkedlistNode mergeLinkedList(LinkedlistNode linkedlistNode1,LinkedlistNode linkedlistNode2){
        LinkedlistNode p = new LinkedlistNode(0), q =p;
        while(linkedlistNode1 != null && linkedlistNode2 != null){
            if(linkedlistNode1.val < linkedlistNode2.val){
                p.next = linkedlistNode1;
                linkedlistNode1 = linkedlistNode1.next;
            }
             else{
                p.next = linkedlistNode2;
                linkedlistNode2 = linkedlistNode2.next;
            }
             p = p.next;
        }
        if(linkedlistNode1 != null) p.next =linkedlistNode1;
        else if (linkedlistNode2 != null) p.next = linkedlistNode2;

        return q.next;
    }

    public static void main(String[] args) {
        LinkedlistNode l1 = new LinkedlistNode(1);
        l1.next = new LinkedlistNode(3);
        l1.next.next = new LinkedlistNode(5);
        l1.next.next.next = new LinkedlistNode(7);
        LinkedlistNode l2 = new LinkedlistNode(2);
        l2.next = new LinkedlistNode(4);
        l2.next.next = new LinkedlistNode(6);
        l2.next.next.next = new LinkedlistNode(8);
        Solution solution = new Solution();
        LinkedlistNode pp = solution.mergeLinkedList(l1, l2);
        while ( pp!= null  ){
            System.out.println(pp.val);
            pp = pp.next;
        }

    }
}

class LinkedlistNode{
    int val;
    LinkedlistNode next;

    public LinkedlistNode(int val) {
        this.val = val;
    }
}
