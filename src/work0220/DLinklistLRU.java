package work0220;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DLinklistLRU {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public DLinklistLRU(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }


        moveToHead(node);
        return node.value;
    }
/*
 * 1.判断原双链表是否有要被添加的键值对
 * 2.若没有，即可添加;若存在，则替换对应的value
 *
 */
    public void put(int key, int value) {
//        DLinkedNode node = cache.get(key);

        if(!cache.containsKey(key))
        {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            cache.get(key).value = value;
            moveToHead(cache.get(key));
        }
    }

    /**
     * 1.添加到头结点：需要先移除自身所在结点，然后在移动到头结点
     *
     * @param node
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     *如果新put的key已存在，替换相应的value并把结点重新放到第一位
     * @param node
     */
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    public static void main(String[] args) {
        DLinklistLRU dLinklistLRU = new DLinklistLRU(5);
//        dLinklistLRU.removeNode(dLinklistLRU.cache.get(3));
//        dLinklistLRU.put(2,2);
        dLinklistLRU.put(1,1);
        dLinklistLRU.put(2,2);
        dLinklistLRU.put(1,79);
        dLinklistLRU.put(5,5);
        dLinklistLRU.put(4,4);
        dLinklistLRU.put(3,3);
        dLinklistLRU.put(6,6);
//        dLinklistLRU.put(7,7);
//        dLinklistLRU.put(8,8);
        System.out.println(dLinklistLRU.get(1));
//        System.out.println(dLinklistLRU.get(6));
    }
}