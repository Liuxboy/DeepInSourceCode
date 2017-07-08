package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/8 <br>
 * Time: 08:58 <br>
 * Desc:
 */
public class Strings {

    public static void main(String[] args) {
        System.out.println(replaceSpace_0(new StringBuffer("A is A b ")));
        System.out.println(replaceSpace_0(new StringBuffer(" A is A b ")));
        System.out.println(replaceSpace_0(new StringBuffer("A is A")));
        System.out.println(replaceSpace_0(new StringBuffer("")));
        System.out.println(replaceSpace_0(null));

        System.out.println(replaceSpace_1(new StringBuffer("A is A b ")));
        System.out.println(replaceSpace_1(new StringBuffer(" A is A b ")));
        System.out.println(replaceSpace_1(new StringBuffer("A is A")));
        System.out.println(replaceSpace_1(new StringBuffer("")));
        System.out.println(replaceSpace_1(null));
    }

    /**
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.
     * 则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return
     */
    private static String replaceSpace_0(StringBuffer str) {
        if (str == null || str.length() == 0)
            return "";
        return str.toString().replaceAll(" ", "%20");
    }

    private static String replaceSpace_1(StringBuffer str) {
        if (str == null || str.length() == 0)
            return "";
        char[] chars = str.toString().toCharArray();
        StringBuffer result = new StringBuffer();
        for (char aChar : chars) {
            if (aChar == ' ') {
                result.append("%20");
                continue;
            }
            result.append(aChar);
        }
        return result.toString();
    }

    private static String replaceSpace_2() {
        return null;

    }
}
