package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/8 <br>
 * Time: 18:39 <br>
 * Desc:
 */
public class Stack {
    //栈顶
    private LinkNode<Integer> top;

    public LinkNode peek() {
        if (top != null) {
            return top;
        }
        return null;
    }

    public LinkNode pop() {
        if (top == null) {
            return null;
        } else {
            LinkNode temp = new LinkNode<>(top.getData());
            top = top.getNext();
            return temp;
        }
    }

    public void push(LinkNode<Integer> n) {
        if (n != null) {
            n.setNext(top);
            top = n;
        }
    }
}
