package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/8 <br>
 * Time: 08:52 <br>
 * Desc:
 */
class LinkNode<T> {
    private T data;
    private LinkNode<T> next;

    public LinkNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkNode<T> getNext() {
        return next;
    }

    public void setNext(LinkNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        final StringBuilder stbd = new StringBuilder("LinkNode{");
        stbd.append("\"data\":").append(data);
        stbd.append('}');
        return stbd.toString();
    }
}
