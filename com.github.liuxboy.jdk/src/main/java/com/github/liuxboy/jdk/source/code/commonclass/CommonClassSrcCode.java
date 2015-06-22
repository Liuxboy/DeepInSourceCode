package com.github.liuxboy.jdk.source.code.commonclass;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.omg.CORBA.DataInputStream;
import org.omg.CORBA.DataOutputStream;
/**
 * 
 * @author chundong.liu
 * @version JDK7.0
 * @since 2013.12.10
 *
 */
public class CommonClassSrcCode {
	//阅读JDK中常见类源码
	//System ClassLoader
	//常见类
	String str;
	StringBuffer strbf;
	System sys;
	Object o;
	Class<?> c;
	ClassLoader cl;
	/**
	 * 数据类型包装类
	 */
	//0、计数
	Number nb;
	//1、整型
	Byte byt;
	Short sht;
	Integer inte;
	Long lg;
	
	//2、浮点型
	Float fl;
	Double db;
	
	//3、逻辑型
	Boolean bl;
	//4、字符型
	Character chat;
	
	/**
	 * IO类
	 */
	DataInput dt;
	DataInputStream dis;
	java.io.DataInputStream jioDis;
	DataOutput dot;
	DataOutputStream dos;
	java.io.DataOutputStream jioDos;
	
	//1、基于字节的IO
	InputStream is;
	InputStreamReader isr;
 
	OutputStream os;
	OutputStreamWriter osw;
	
	//2、基于字符的IO
	Reader rd;
	Writer wr;
	
	//3、基于磁盘的IO
	File file;
	//4、基于网络的IO
	Socket sckt;
	
	/**
	 * 并发类 
	 * java.util.concurrent
	 * java.util.concurrent.comic
	 * java.util.concurrent.locks
	 **/
	
	Lock lk;
	Condition cd;
	ConcurrentHashMap<?, ?> chm;
	
	public static void main(String[] args) {
		System.out.println("Today is new day!");
	}
}
