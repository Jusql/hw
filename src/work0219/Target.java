package work0219;

public class Target {
    public static void main(String[] args) {
        int[] a = {3, 2, 5, 6, 1, 6,8,7,4,9,0};
        int target = 8;
//for循环 是 数组自身相加判断是否为target
        System.out.println("数组中两个整数相加为target的数的index为:");
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == target) {
                    System.out.println(  i + " 和 " + j);
                }
            }
        }



    }
}
