package com.github.liuxboy.jdk.source.code.nio;// $Id$

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FastCopyFile {
    static public void main(String args[]) throws Exception {
        String infile = "D:\\GitSpace\\DeepInSourceCode\\com.github.liuxboy.jdk\\src\\main\\resources\\rest\\infile.txt";
        String outfile = "D:\\GitSpace\\DeepInSourceCode\\com.github.liuxboy.jdk\\src\\main\\resources\\rest\\outfile.txt";

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        long startTime = System.currentTimeMillis();
        System.out.println("拷贝文件开始:");
        while (true) {
            buffer.clear();

            int r = fcin.read(buffer);

            if (r == -1) {
                break;
            }

            buffer.flip();

            fcout.write(buffer);
        }
        System.out.println("拷贝文件结束,共耗时:" + (System.currentTimeMillis() - startTime));
    }
}
