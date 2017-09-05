package understanding.the.jvm.chapter02;

/**
 * Package: understanding.jvm.chapter02 <br>
 * Author: liuchundong <br>
 * Date: 2017/8/17 <br>
 * Time: 18:21 <br>
 * Desc:
 * JVM Options: -Xss:2M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true){

        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(() -> dontStop()).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }
}
