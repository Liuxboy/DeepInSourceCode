package com.github.liuxboy.jdk.source.code.collection;

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
