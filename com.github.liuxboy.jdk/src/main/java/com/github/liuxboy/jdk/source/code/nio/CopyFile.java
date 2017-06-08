package com.github.liuxboy.jdk.source.code.nio;// $Id$

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    static public void main(String args[]) throws Exception {
        String infile = "D:\\GitSpace\\DeepInSourceCode\\com.github.liuxboy.jdk\\src\\main\\resources\\rest\\infile.txt";
        String outfile = "D:\\GitSpace\\DeepInSourceCode\\com.github.liuxboy.jdk\\src\\main\\resources\\rest\\outfile.txt";

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        //FileChannel fcin = fin.getChannel();
        //FileChannel fcout = fout.getChannel();

        FileChannel fileChannel;    //Channel是既可读，又可写数据结构
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            fileChannel = fin.getChannel();     //in
            buffer.clear(); //将buffer的position = 0，limit = capacity,准备读入数据
            int r = fileChannel.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();  //将buffer的limit = position, position = 0,准备写出数据
            fileChannel = fout.getChannel();    //out
            fileChannel.write(buffer);
        }
    }
}
