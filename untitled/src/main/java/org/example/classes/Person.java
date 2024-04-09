package org.example.classes;

public class Person {
    private String name;
    private int age;
    private String sex;

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Person setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public void walk() {
        System.out.println("사람이 걷습니다");
    }

    public static void run() {
        System.out.println("사람이 뜁니다");
    }

    public static Person create(String name, int age, String sex) {
        return new Person(name, age, sex);
    }
}
