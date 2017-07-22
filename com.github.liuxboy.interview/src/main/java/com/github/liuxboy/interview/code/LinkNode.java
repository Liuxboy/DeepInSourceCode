package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/8 <br>
 * Time: 08:52 <br>
 * Desc:
 */
class LinkNode<T> {
    public T data;
    public LinkNode<T> next;

    public LinkNode(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        final StringBuilder stbd = new StringBuilder("LinkNode{");
        stbd.append("\"data\":").append(data);
        stbd.append('}');
        return stbd.toString();
    }
}
