package com.github.liuxboy.jdk.source.code.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.github.liuxboy.jdk.source.code.collection <br>
 * Author: liuchundong <br>
 * Date: 2017/3/15 <br>
 * Time: 18:36 <br>
 * Desc:
 */
public class StreamHandle {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("lcd", 30, true));
        personList.add(new Person("lcd", 30, true));
        personList.add(new Person("zw", 26, true));
        personList.add(new Person("wj", 24, false));
        personList.stream()
                .filter(o -> o.isMale())
                .sorted((Person o1, Person o2) -> o1.getAge() > o2.getAge() ? 1 : (o1.getAge() == o2.getAge()) ? 0 : -1)
                .distinct() //重写了hashCode与equals才能去重的
                .forEach(System.out::println);
    }
}

class Person {
    private String name;
    private int age;
    private boolean male;

    public Person(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    @Override
    public String toString() {
        final StringBuilder stbd = new StringBuilder("Person{");
        stbd.append("\"name\":\"").append(name).append('\"');
        stbd.append(",\"age\":").append(age);
        stbd.append(",\"male\":").append(male);
        stbd.append('}');
        stbd.append(super.toString());
        return stbd.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (male != person.male) return false;
        return name != null ? name.equals(person.name) : person.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (male ? 1 : 0);
        return result;
    }
}
