package com.github.liuxboy.jdk.source.code.nio;// $Id$

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class CreateBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put((byte) 'a');
        byteBuffer.put((byte) 'b');
        byteBuffer.put((byte) 'c');
        //mark <= position <= limit <= capacity
        /*           position\    /capacity
         *      |_|_|_|_|_|_|_|_|_|
         * mark/        limit/
         */
        byteBuffer.flip();  //弹一弹byteBuffer，让其limit = position, position = 0 mark = -1
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());

        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.put('1');
        charBuffer.put('2');
        charBuffer.put('3');
        charBuffer.flip();

        /*
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get()); //看看什么反应，结果：如果position > limit，会抛出BufferUnderflowException，所有
        */
        while (charBuffer.limit() != charBuffer.position()) {
            System.out.println(charBuffer.get());
        }
    }
}
