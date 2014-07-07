package org.arcie.dong.interview.java;
/**
 * @author 刘春东
 * @version 0.1
 * @time 2014年4月1日上午10:56:35
 */
public class TestKeywordFinal extends Test {
	//public  final TestKeywordFinal() { //Constructor of TestKeywordFinal，构造方法不能为final型
	public TestKeywordFinal(){
		System.out.println("Test!"); 
	}
	
	public void testFinalMethodCanOverride(){//Cannot override the final method from Test
		System.out.println("nothing is here!");
	}
	public void testFinalMethodeCanOverride(int a){//Can overload the final method from Test
		
	}
	
	public static void main(String[] args) {

	}
}

class Test{
	public Test(){
		
	}
	public void testFinalMethodCanOverride(){//This method is 'final'
	
	}
}
