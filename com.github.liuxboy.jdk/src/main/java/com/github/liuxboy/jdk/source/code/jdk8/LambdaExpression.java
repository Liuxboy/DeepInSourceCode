package com.github.liuxboy.jdk.source.code.jdk8;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Package: com.github.liuxboy.jdk.source.code.jdk8 <br>
 * Author: liuchundong <br>
 * Date: 2017/8/1 <br>
 * Time: 17:38 <br>
 * Desc:
 */
public class LambdaExpression {
    public static void main(String[] args) {
        Function function = (Function<String, Integer>) s -> {
            if (NumberUtils.isDigits(s)) {
                return Integer.valueOf(s);
            }
            return 0;
        };
        System.out.println(function.apply("20"));

        Consumer consumer = o -> {
            if (o instanceof String) {
                System.out.println(o);
            }
            System.out.println("o isn't a String Object");
        };
        consumer.accept("I am a consumer");

        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                if (o instanceof String)
                    return true;
                return false;
            }
        };
        System.out.println(predicate.test("I am a String"));
    }
}
