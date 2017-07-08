package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/8 <br>
 * Time: 19:00 <br>
 * Desc:
 */
public class Queue {

    private LinkNode<Integer> first, last;

    public void enqueue(LinkNode<Integer> n) {
        if (first == null) {
            first = n;
            last = first;
        } else {
            last.setNext(n);
            last = n;
        }
    }

    public LinkNode dequeue() {
        if (first != null) {
            LinkNode temp = new LinkNode<>(first.getData());
            first = first.getNext();
            return temp;
        }
        return null;
    }
}
