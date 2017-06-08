package com.github.liuxboy.jdk.source.code.nio;// $Id$

import java.nio.*;

public class CreateBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 'a');
        byteBuffer.put((byte) 'b');
        byteBuffer.put((byte) 'c');
        //mark <= position <= limit <= capacity
        /*           position\    /capacity
         *      |_|_|_|_|_|_|_|_|_|
         * mark/        limit/
         */
        byteBuffer.flip();  //弹一弹byteBuffer，让其limit = position, position = 0, mark = -1
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get(1));
        byteBuffer.position(0);
        byte[] dst0 = new byte[byteBuffer.limit()];
        System.out.println(byteBuffer.get(dst0));
        byteBuffer.position(0);
        byte[] dst1 = new byte[byteBuffer.limit()];
        System.out.println(byteBuffer.get(dst1, 0, byteBuffer.limit()));

        ShortBuffer shortBuffer = ShortBuffer.allocate(10);
        shortBuffer.put((short) 10);
        shortBuffer.put((short) 11);
        shortBuffer.put((short) 12);

        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(100);
        intBuffer.put(200);
        intBuffer.put(300);

        LongBuffer longBuffer = LongBuffer.allocate(10);
        longBuffer.put(1000000L);
        longBuffer.put(2000000L);
        longBuffer.put(3000000L);
        System.out.println(longBuffer.get());

        FloatBuffer floatBuffer = FloatBuffer.allocate(10);
        floatBuffer.put(3.1415F);
        floatBuffer.put(2.7182F);
        floatBuffer.put(0.6180F);


        DoubleBuffer doubleBuffer = DoubleBuffer.allocate(10);
        doubleBuffer.put(3.1415D);
        doubleBuffer.put(2.7182D);
        doubleBuffer.put(0.6180D);

        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.put('A');
        charBuffer.put('B');
        charBuffer.put('C');
        charBuffer.flip();
        /*
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get()); //看看什么反应，结果：如果position > limit，会抛出BufferUnderflowException，所有
        */
        //while (charBuffer.limit() < charBuffer.position()) {
        while (charBuffer.hasRemaining()) {
            System.out.println(charBuffer.get());
        }
        System.out.println("wait");
    }
}
