package com.github.liuxboy.jdk.source.code.interview.java;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/21 21:41
 * @comment ExceptionTest
 * 作者：真实的归宿
 * SourceCode：http://blog.csdn.net/hguisu/article/details/6155636
 */
public class ExceptionTest {
    /**
     * Some RunTimeExceptions
     */
    ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException;
    ArithmeticException arithmeticException;
    NullPointerException nullPointerException;
    NegativeArraySizeException negativeArraySizeException;
    ArrayStoreException arrayStoreException;
    SecurityException securityException;
    IllegalArgumentException illegalArgumentException;
    IllegalStateException illegalStateException;
    ClassCastException classCastException;
    NoSuchElementException noSuchElementException;

    /**
     * Some IOException
     */
    IOException ioException;
    EOFException eofException;
    FileNotFoundException fileNotFoundException;
    MalformedURLException malformedURLException;
    UnknownHostException unknownHostException;
    /**
     * Some otherException
     */

    SQLException sqlException;
    NoSuchFieldException noSuchFieldException;
    NoSuchMethodException noSuchMethodException;
    ClassNotFoundException classNotFoundException;
    NumberFormatException numberFormatException;
    StringIndexOutOfBoundsException stringIndexOutOfBoundsException;
    IllegalAccessException illegalAccessException;
    InstantiationException instantiationException;
    CloneNotSupportedException cloneNotSupportedException;


    public ExceptionTest() {
    }

    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx1() throws Exception {
        boolean ret = true;
        try {
            ret = testEx2();
            if (!ret) {
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx2() throws Exception {
        boolean ret = true;
        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        ExceptionTest testException1 = new ExceptionTest();
        /*try {
            testException1.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        try {
            aMethod(0);
        } catch (Exception ex) {
            System.out.printf("exception in main");
        }
        System.out.println(" finished");
    }

    public static int aMethod(int i) throws Exception {
        try {
            return i / 10;
        } catch (Exception ex) {
            throw new Exception("exception in a Method");
        } finally {
            System.out.printf("finally");
        }
    }
}
