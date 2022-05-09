package work0227;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTraverseTree {
    Node tree;

    public  class Node {
        int val;
        Node left;
        Node right;

        public Node(int data) {
            this.val = data;
        }
    }

    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (true) {
            if (data > p.val) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }


//    public class TreeNode {
//        private int val;
//        private PutTree.TreeNode left;
//        private PutTree.TreeNode right;
//    }

//     Definition for a binary tree node.


    List<List<Integer>> res=new ArrayList();
    public List<List<Integer>> levelOrder(Node root) {
        if(root==null)return res;                                //边界条件

        Queue<Node> q=new LinkedList();             //创建的队列用来存放结点，泛型注意是TreeNode
        q.add(root);
        while(!q.isEmpty()){                        //队列为空说明已经遍历完所有元素，while语句用于循环每一个层次
            int count=q.size();
            List<Integer> list=new ArrayList();
            while(count>0){                             //遍历当前层次的每一个结点，每一层次的Count代表了当前层次的结点数目
                Node temp=q.peek();
                q.poll();                                        //遍历的每一个结点都需要将其弹出
                list.add(temp.val);
                if(temp.left!=null)
                    q.add(temp.left);      //迭代操作，向左探索
                if(temp.right!=null)
                    q.add(temp.right);
                count--;
            }
            res.add(list);
        }
        return res;

    }

    public static void main(String[] args) {
        LevelTraverseTree levelTraverseTree = new LevelTraverseTree();
        levelTraverseTree.insert(3);
        levelTraverseTree.insert(9);
        levelTraverseTree.insert(5);
        levelTraverseTree.insert(6);
        levelTraverseTree.insert(7);
        System.out.println(levelTraverseTree.levelOrder(levelTraverseTree.tree));


    }
}
