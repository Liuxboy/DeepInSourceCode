package com.github.liuxboy.interview.code;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author 刘春东
 * @version 0.1
 * @time 2014年3月16日下午2:00:39
 * from URL:http://blog.csdn.net/lifetragedy/article/details/9751079
 * 
 * Q1: What if the main method is declared as private?
 * A1: The program compiles properly but at run time it will give "Main method not public." message.
 * 
 * Q2: What if the static modifier is removed from the signature of the main method?
 * A2: Program compiles. But at run time throws an error "NoSuchMethodError".
 * 
 * Q3: What if I write static public void instead of public static void?
 * A3:  Program compiles and runs properly.
 * 
 * Q4: What if I do not provide the String array as the argument to the method?
 * A4:  Program compiles but throws a run time error "NoSuchMethodError".
 * 
 * Q5: What is the first argument of the String array in main method?
 * A5:  The String array is empty. It does not have any element. 
 * This is unlike C/C++（读作plus plus) where the first element by default is the program name.
 * 
 * Q6: If I do not provide any arguments on the command line, then the String array of Main method will be empty or null?
 * A6:  It is empty. But not null.
 * 
 * Q7: How can one prove that the array is notnull but empty using one line of code?
 * A7:  Print args.length. It will print 0. That means it is empty. But if it would have been null then it would have thrown a 
 * NullPointerException on attempting to print args.length.
 */


public class MainTest {
/*	private static void main(String[] args) {
		
	}*/
	
/*	public static  void main(String[] args) {
		System.out.println("this is a right main method");
	}*/
/*static public void main(String[] args) {
		System.out.println("this is a right main method");
	}*/
/*	public static void main() {
		System.out.println("this is a right main method");
	}	*/
/*	public static void main(String[] args) {
		System.out.println(args.length);	//look A7
	}*/
	public static void main(String[] args) {
		
	}
	public boolean compareTwoArray(Object[] o1, Object[] o2){
		Arrays.equals(o1, o2);//两对象数组比较
		
		
		return false;
	}
}
