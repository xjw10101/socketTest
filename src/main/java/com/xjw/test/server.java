package main.java.com.xjw.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class server {
    public static void main(String[] args) {
        String name = "";

        List<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.forEach(a->System.out.println(a));
        objects.stream().forEach(item->{
            System.out.println(item);
        });
        int age = 0;
        Student student = new Student(name, age);
        Student student1 = new Student();
        student.getName();
        student.getAge();
        student.setAge(27);
        student.setName("xujingwen");
        System.out.println(student);

    }

}

class Student {
    private String name;
    private int age;


    //有参构造方法
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //无参构造方法
    public Student() {
    }

    //name属性get方法
    public String getName() {
        return name;
    }

    //name属性set方法
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //重写toString方法
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
