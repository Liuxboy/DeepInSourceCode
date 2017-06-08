package com.github.liuxboy.jdk.source.code.nio;// $Id$

import java.nio.ByteBuffer;

public class SliceBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        buffer.position(3);
        buffer.limit(7);
        //由于切片缓存与原缓存共享同一份底层数组，所以，对切片中的数据更改也会影响到原数据
        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        //以下两行代码跟buffer.clear()方法起到作用一样;
        buffer.clear();
        //buffer.position(0);     //让position回归到起点0
        //buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }
}
