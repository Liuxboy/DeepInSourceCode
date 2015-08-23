package com.github.liuxboy.jdk.source.code.java.puzzlers.puzzlers.with.character.puzzle13;

public class AnimalFarm {
    public static void main(String[] args) {
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out.println("Pig's value: " + pig);
        System.out.println("Dog's value: " + dog);
        System.out.println("Pig's hashCode: " + pig.hashCode());
        System.out.println("Dog's hashCode: " + dog.hashCode());
        /**
         * + 运算优先级比 == 高，所以从左至右，下面第一行与行与第二行等价了。
         * 另外，pig和dog最终指向的都是同一个字符串常量池中的对象(不过要注意hashCode一样，也不能保证两对象是一样的)，
         * 但是要记住，"=="运算的是两个对象的地址，pig, dog是两个不同的引用对象，其所在地址肯定是不同的。所以"=="是不成立的，别的类型这样声明也一样。
         */
        System.out.println("Animals are equal: " + pig == dog);
        System.out.println(("Animals are equal: " + pig) == dog);
        System.out.println("Animals are equal: " + (pig == dog));
        System.out.println("Animals are equal: " + pig.equals(dog));

    }
}
