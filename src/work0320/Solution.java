package work0320;
/**
 * 1、编写一个方法，求一个数组中的第二大元素
 * 2、写一个栈溢出的程序例子
 * 3、写一个OutofMemery的程序，并解释，为什么会内存溢出
 * 4、给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。注意：不要修改链表节点内部的值。
 *     比如：1->2->3->4
 *     结果：2->1->4->3
 */

/**
 * 求数组中第二大元素
 * 堆排序
 */
public class Solution {
    private int findSecondBig(int arr[]) {
        buildHeap(arr);
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return Math.min(arr[0], arr[1]);
        }
        return Math.max(arr[2], arr[1]);
    }

    private void buildHeap(int arr[]) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        while (true) {
            // 最大值位置
            int maxPos = i;
            // 与左子节点（i * 2 + 1）比较，获取最大值位置
            if (i * 2 + 1 <= n && arr[i] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 最大值与右子节点（i * 2 + 2）比较，获取最大值位置
            if (i * 2 + 2 <= n && arr[maxPos] < arr[i * 2 + 2]) {
                maxPos = i * 2 + 2;
            }
            // 最大值是当前位置结束循环
            if (maxPos == i) {
                break;
            }
            // 与子节点交换位置
            swap(arr, i, maxPos);

//             以交换后子节点位置接着往下查找
            i = maxPos;
        }
    }

    private void swap(int arr[], int i, int maxPos) {
        int temp = arr[i];
        arr[i] = arr[maxPos];
        arr[maxPos] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int test[] = new int[]{1, 2, 3, 4, 5, 7};
        System.out.println(solution.findSecondBig(test));
    }
}
