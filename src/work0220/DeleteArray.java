package work0220;

import java.util.Arrays;

public class DeleteArray {
//给定一个按升序排列的数组，原地删除重复的元素，并返回不重复的数组元素个数
    public int remove(int[] arr){
        int n=arr.length;
        int j=0;
        for (int i = 0; i <n; i++) {
            if(arr[i]!=arr[j]){
                arr[++j] = arr[i];
            }
        }
        return j+1;//返回已删除重复元素的个数：return n-j-1;

    }

    public static void main(String[] args) {
        int[] a ={1, 1,1, 1, 2, 2, 2, 3,3, 4,5,5,6};
       int b = new DeleteArray().remove(a);
        System.out.println(b);


    }
}
