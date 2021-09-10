package com.example.uimode.java;


import android.util.Log;

public class Fanshe {


    public  void test() throws ClassNotFoundException {

        Person person = new Student();
        Log.i("11","这个是: "+person.name);

        //方式一:通过对象获得
        Class cl = person.getClass();

        //方法二，通过Class.forName获得
        Class cl2 = Class.forName("com.example.uimode.java.Student");

        //方法三,通过类名.class
        Class cl3 = Student.class;

        //四：基本内置类型得包装类都有一个Type属性
        Class c4 = Integer.TYPE;

    

    }

}

class Person{
    public String name;

    private int age = 0;

    public Person(){

    }
    public Person(String name){
        this.name = name;
    }
}

class Student extends Person{

    public Student(){
        this.name = "学生";
    }
}