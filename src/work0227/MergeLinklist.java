package work0227;


public class MergeLinklist {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
            public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
                ListNode dummy = new ListNode(0), p = dummy;

                while (l1 != null && l2 != null) {
                    if (l1.val < l2.val) {
                        p.next = l1;
                        l1 = l1.next;
                    } else {
                        p.next = l2;
                        l2 = l2.next;
                    }
                    p = p.next;
                }

                if (l1 != null) p.next = l1;
                if (l2 != null) p.next = l2;
                return dummy.next;
            }



    public static void main(String[] args) {
        MergeLinklist mergeLinklist = new MergeLinklist();
        ListNode listNode = mergeLinklist.new ListNode(1);
        listNode.next = mergeLinklist.new ListNode(4);
        listNode.next.next = mergeLinklist.new ListNode(7);

        ListNode listNode1 = mergeLinklist.new ListNode(2);
        listNode1.next = mergeLinklist.new ListNode(4);
        listNode1.next.next = mergeLinklist.new ListNode(6);
        ListNode listNode2 =  mergeLinklist.mergeTwoLists(listNode1,listNode);
       while(listNode2 != null){


           System.out.println(listNode2.val);
           listNode2 = listNode2.next;
       }



    }
}



