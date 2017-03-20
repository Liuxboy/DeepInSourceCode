package com.github.liuxboy.jdk.source.code.commonclass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Package: com.github.liuxboy.jdk.source.code.commonclass <br>
 * Author: liuchundong <br>
 * Date: 2017/3/14 <br>
 * Time: 14:41 <br>
 * Desc:
 */
public class LambdaExpression {
    private static Button button = new Button();

    public static void main(String[] args) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("ActionDetected");
            }
        });

        button.addActionListener((ActionEvent e) -> {
            //handle actionEvent e
            System.out.println("Actiondetected");
        });

        button.addActionListener(e -> {
            //handle actionEvent e
            System.out.println("Actiondetected");
        });

        Runnable runnable0 = new Runnable() {
            @Override
            public void run() {
                System.out.println("running without lambda");
            }
        };

        Runnable runnable1 = () -> {
            System.out.println("running without lambda");
        };


        List list = new ArrayList();
        list.add("111");
        list.add("222");
        list.forEach(id -> {
            System.out.println(id);
        });
        list.stream().distinct();
    }

    public int add(int x, int y) {
        return x + y;
    }

}
