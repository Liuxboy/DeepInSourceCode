package com.github.liuxboy.jdk.source.code.nio;// $Id$

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class UseCharsets {
    static public void main(String args[]) throws Exception {
        String inputFile = "D:\\GitSpace\\DeepInSourceCode\\com.github.liuxboy.jdk\\src\\main\\resources\\rest\\infile.txt";
        String outputFile = "D:\\GitSpace\\DeepInSourceCode\\com.github.liuxboy.jdk\\src\\main\\resources\\rest\\outfile.txt";

        RandomAccessFile randomAccessFileR = new RandomAccessFile(inputFile, "r");
        RandomAccessFile randomAccessFileRW = new RandomAccessFile(outputFile, "rw");
        long inputLength = new File(inputFile).length();

        FileChannel fileChannelR = randomAccessFileR.getChannel();
        FileChannel fileChannelRW = randomAccessFileRW.getChannel();

        MappedByteBuffer inputData = fileChannelR.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        Charset latin1 = Charset.forName("ISO-8859-1");
        CharsetDecoder decoder = latin1.newDecoder();
        CharsetEncoder encoder = latin1.newEncoder();

        CharBuffer cb = decoder.decode(inputData);

        // Process char data here

        ByteBuffer outputData = encoder.encode(cb);

        fileChannelRW.write(outputData);

        randomAccessFileR.close();
        randomAccessFileRW.close();
    }
}
