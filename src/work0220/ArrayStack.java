package work0220;

public class ArrayStack {
    int[] array;
    int top;
    int maxsize;

    public ArrayStack(int num) {
        array=new int[num];
        maxsize=num;
        top=-1;
    }
    //因为在push和pop之前都要检查栈是否为空或者为满,所以需要isFull和isEmpety方法
    public boolean isFull(){
        return top==maxsize-1;
    }
    public boolean isEmpety(){
        return top==-1;
    }


    public void push(int value){
        if (isFull()){
            System.out.println("栈已经满了");
            return;
        }
        top++;//栈针上移动
        array[top]=value;
    }


    public int pop(){
        if (isEmpety()){
            throw new RuntimeException("栈为空");
        }
        int value=array[top];
        top--;
        return value;
    }
    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(5);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}