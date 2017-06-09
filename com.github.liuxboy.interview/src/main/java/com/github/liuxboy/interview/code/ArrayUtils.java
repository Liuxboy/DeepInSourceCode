package com.github.liuxboy.interview.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/9 <br>
 * Time: 10:11 <br>
 * Desc:
 */
public class ArrayUtils {
    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "a", "a", "b"};
        System.out.println(arrayAlias(array));
    }

    /**
     * @param array
     * @return
     * @link /resources/rest/ArrayAlias.png
     */
    private static String arrayAlias(String[] array) {

        String[] alias = new String[array.length];

        Map<String, Integer> aliasMap = new HashMap<>();
        Integer count;
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = array[i];
            count = aliasMap.get(str);
            if (count == null) {
                alias[i] = array[i];
                aliasMap.put(str, 0);
            } else {
                alias[i] = array[i] + (count + 1);
                aliasMap.put(str, count + 1);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < alias.length; j++) {
            //最后一个，不用加逗号分割
            if (j == alias.length - 1) {
                stringBuffer.append(alias[j]);
                return stringBuffer.toString();
            }
            stringBuffer.append(alias[j]).append(",");
        }
        return stringBuffer.toString();
    }
}

