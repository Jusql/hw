package work0116;

public class Person {
    private String name;
    private int age;
    private boolean gender;
    private String hobby;
    private static int count = 0;

    public Person() {
        count++;
    }

    static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Person person0 = new Person();
        Person person1 = new Person();
        Person person2 = new Person();

        System.out.println(Person.getCount());

    }
}
