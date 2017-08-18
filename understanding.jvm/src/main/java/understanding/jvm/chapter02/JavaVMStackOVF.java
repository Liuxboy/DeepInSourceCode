package understanding.jvm.chapter02;

/**
 * Package: understanding.jvm.chapter02 <br>
 * Author: liuchundong <br>
 * Date: 2017/8/17 <br>
 * Time: 18:13 <br>
 * Desc:
 * JVM Options: -Xss128K
 */
public class JavaVMStackOVF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackOVF javaVMStackOVF = new JavaVMStackOVF();
        try {
            javaVMStackOVF.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length：" + javaVMStackOVF.stackLength);
            e.printStackTrace();
        }
    }
    /*
    Exception in thread "main" java.lang.StackOverflowError
    at understanding.jvm.chapter02.JavaVMStackOVF.stackLeak(JavaVMStackOVF.java:15)
    at understanding.jvm.chapter02.JavaVMStackOVF.stackLeak(JavaVMStackOVF.java:15)
    at understanding.jvm.chapter02.JavaVMStackOVF.stackLeak(JavaVMStackOVF.java:15)
    at understanding.jvm.chapter02.JavaVMStackOVF.stackLeak(JavaVMStackOVF.java:15)
    ...
    */
}
