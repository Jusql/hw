package work0123;

import java.util.Scanner;

public class StudentScore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("此时共有" + Student.getCount() + "名学生信息请输入序号(1,2...)并按序号查询:");
        int num = scanner.nextInt();
        //查询开始时
        long start = System.currentTimeMillis();

        Student student = new Student("ZhangSan (0)", "男", 18, "123", "LiSi", Subjects.MATH, Subjects.CHINESE, Subjects.ENGLISH, new Class("大一", "一班"));
        student.Math.score = 80;
        student.Chinese.score = 90;
        student.English.setScore(90);
        Student student1 = new Student("LiHua", "男", 19, "456", "小明", Subjects.MATH, Subjects.CHINESE, Subjects.ENGLISH, new Class("大二", "一班"));
        student.Math.score = 83;
        student.Chinese.score = 93;
        student.English.setScore(93);

        Student student2 = new Student("WangWu", "女", 20, "789", "ZhaoLiu", Subjects.MATH, Subjects.CHINESE, Subjects.ENGLISH, new Class("大四", "一班"));
        student2.Math.score = 91;
        student2.Chinese.score = 99;
        student2.English.score = 9;

        Student student3 = new Student("XiaoMing", "男", 32, "123", "大军", Subjects.MATH, Subjects.CHINESE, Subjects.ENGLISH, new Class("大三", "七班"));
        student3.Math.score = 100;
        student3.Chinese.score = 99;
        student3.English.score = 109;

        //测试equals()方法
        //学号不同
        System.out.println(student.equals(student1));
        //学号相同
        System.out.println(student.equals(student3));
        //不是Student的子类
        System.out.println(student.equals(new Class("nihao", "xio")));

        Student[] students = new Student[Student.getCount()];
        students[0] = student;
        students[1] = student1;
        students[2] = student2;
        students[3] = student3;
//
//        for (Student stu :
//                students) {
//            System.out.println(stu.toString());
//        }

        if (0 < num && num <= students.length) {
            for (int i = 0; i < students.length; i++) {
                if (i == num - 1) {
                    System.out.println(students[i].toString());
                }
            }
        }
        //程序结束时
        long end = System.currentTimeMillis();
        //总计用时 毫秒
        long time = end - start;
        System.out.println(time);//  总计 3ms
//        System.out.println(students[0].toString());

    }
}

class Student {
    private String name;
    private String gender;
    private int age;
    private String id;
    private String classTeacher;
    public Subjects Math;
    public Subjects Chinese;
    public Subjects English;
    public Class cla;

    public Student() {
        count++;
    }

    public Student(String name, String gender, int age, String id, String classTeacher, Subjects math, Subjects chinese, Subjects english, Class cla) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.id = id;
        this.classTeacher = classTeacher;
        Math = math;
        Chinese = chinese;
        English = english;
        this.cla = cla;
        count++;
    }

    private static int count = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    @Override
    public String toString() {
        return "work0116.Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", 班主任='" + classTeacher + '\'' +
                ", mathScore=" + this.Math.score +
                ", chineseScore=" + this.Chinese.score +
                ", englishScore=" + this.Chinese.score +
                ",班级是" + this.cla.getGrade() + " " + this.cla.getRoom() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Student) {
            Student stu = (Student) obj;
            return this.id == ((Student) obj).id;
        }
        return false;
    }

    static int getCount() {
        return count;
    }
}

class Class {
    private String grade;
    private String room;

    public Class(String grade, String room) {
        this.grade = grade;
        this.room = room;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}


enum Subjects {
    MATH(),
    CHINESE(),
    ENGLISH();
    public int score;

    Subjects(int score) {
        this.score = score;
    }

    Subjects() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}





