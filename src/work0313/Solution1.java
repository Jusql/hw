package work0313;

import sun.reflect.generics.tree.Tree;

import java.util.List;

public class Solution1 {


    public ListNode sort(ListNode head, int num) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null){
            if(head.val < num){
                small.next = head;
                small = small.next;
            }else{
                large.next=head;
                large = large.next;
            }
            head = head.next;
        }
        large.next= null;
        small.next = largeHead.next;

        return smallHead.next;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(3);

        listNode.next = new ListNode(7);
        listNode.next.next= new ListNode(5);
        listNode.next.next.next= new ListNode(2);
        listNode.next.next.next.next= new ListNode(1);
        Solution1 solution1 = new Solution1();
        ListNode list =  solution1.sort(listNode,4);
        while(list != null){
            System.out.println(list.val);
            list = list.next;
        }


    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
    void add(int val){
        ListNode newNode = new ListNode(val);
        this.next = newNode;
        newNode.next = null;
    }

}