package com.github.liuxboy.jdk.source.code.collection;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: String2List</p>
 * <p>Copyright: Copyright(c)2015</p>
 * <p>Company: JD.JR </p>
 * <p>Time: 2016/1/7 12:10</p>
 * <p>Description: 说明 </p>
 *
 * @author wyliuchundong
 * @version 0.0.1
 */
public class String2List {

    private static List<String> str2List(String masterIds) {
        String[] strings = masterIds.split(",");
        return Arrays.asList(strings);
    }

    public static void main(String[] args) {
        String string1 = "360000000000085272,360000000000085462,360000000000085488,360000000000087641,360000000000089316,360000000000508810";
        String string2 = "360000000000508810";
        String string3 = "";
        System.out.println("字符串转化结果1:" + str2List(string1));
        System.out.println("字符串转化结果2:" + str2List(string2));
        System.out.println("字符串转化结果3:" + str2List(string3));
    }
}
