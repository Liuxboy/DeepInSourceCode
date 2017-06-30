package com.github.liuxboy.jdk.source.code.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Package: com.github.liuxboy.jdk.source.code.serializable <br>
 * Author: liuchundong <br>
 * Date: 2017/6/24 <br>
 * Time: 19:08 <br>
 * Desc:
 */
public class SerializableData implements Serializable {

    private static final long serialVersionUID = -5495322140332662219L;

    //
    private String name;
    private int age;
    private double score;

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();

    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket s = new ServerSocket(8189)) {
            System.out.println("hehe");
        }
    }

}
