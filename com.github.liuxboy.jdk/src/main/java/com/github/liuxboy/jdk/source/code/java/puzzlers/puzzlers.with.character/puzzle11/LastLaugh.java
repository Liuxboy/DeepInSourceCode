package com.github.liuxboy.jdk.source.code.java.puzzlers.puzzlers.with.character.puzzle11;

public class LastLaugh {
    public static void main(String args[]) {
        /**下面的打印，方法原型是:
         public void println(String x) {
            synchronized (this) {
                print(x);
                newLine();
            }
         } 所以是两个字符串连接
         */
        System.out.println("H" + "a");
        /**下面的打印，访问原型是:
        public void println(int x) {
            synchronized (this) {
                print(x);
                newLine();
            }
        }
        */
        System.out.println('H' + 'a');
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('H');
        stringBuffer.append('a');
        System.out.println(stringBuffer);

        System.out.println("2 + 2 = " + 2 + 2 );
        System.out.printf("%c%c", 'H', 'a');
    }
}
