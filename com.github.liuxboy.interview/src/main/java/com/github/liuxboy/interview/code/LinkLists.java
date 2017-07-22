package com.github.liuxboy.interview.code;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/8 <br>
 * Time: 09:08 <br>
 * Desc:
 */
public class LinkLists {
    public static void main(String[] args) {
        LinkNode<Integer> head = new LinkNode<>(0);
        LinkNode<Integer> moveNode = head;
        for (int i = 0; i < 10; i++) {
            moveNode.next = new LinkNode<>(i);
            moveNode = moveNode.next;
        }
        System.out.println(findCountdownM(head, 1));
        System.out.println(findCountdownM(head, 3));
        System.out.println(findCountdownM(head, -1));
        System.out.println(findCountdownM(head, 0));
        System.out.println(findCountdownM(head, 10));
        System.out.println(findCountdownM(head, 11));


        System.out.println("------");


        LinkNode listNode = new LinkNode<>(0);
        LinkNode moveListNode = listNode;
        for (int i = 0; i < 10; i++) {
            moveListNode.next = new LinkNode<>(i);
            moveListNode = moveListNode.next;
        }
        ArrayList<Integer> arrayList = reverseLinkNode(listNode);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(reverseLinkNode(new LinkNode<>(2)));
        System.out.println(reverseLinkNode(null));
        LinkNode listNode1 = new LinkNode<>(67);
        listNode1.next = new LinkNode<>(0);
        listNode1.next.next = new LinkNode<>(24);
        listNode1.next.next.next = new LinkNode<>(58);
        System.out.println(reverseLinkNode(listNode1));
    }

    /**
     * 设计一个复杂度为n的算法找到链表倒数第m个元素。最后一个元素假定是倒数第0个
     *
     * @param head
     * @param m
     * @return
     */
    private static LinkNode findCountdownM(LinkNode head, int m) {
        if (head == null || (head.next == null && m != 0))
            return null;
        if (head.next == null && m == 0)
            return head;
        LinkNode pre = head;
        LinkNode post = head;
        int step = 0;
        while (pre.next != null) {
            pre = pre.next;
            if (step < m) {
                step++;
            } else if (step == m) {
                post = post.next;
            }
        }
        if (step < m || m < 0)
            post = null;
        return post;
    }

    /**
     * 反转链表
     *
     * @param linkNode 链表第一个节点，而不是head指针
     * @return
     */
    private static ArrayList<Integer> reverseLinkNode(LinkNode linkNode) {
        //空链表
        if (linkNode == null)
            return new ArrayList<>();
        //单节点
        if (linkNode.next == null) {
            return new ArrayList<>((Integer) linkNode.data);
        }
        //核心算法
        LinkNode temp = linkNode;
        LinkNode p;
        while (temp.next != null) {
            p = temp.next;
            temp.next = p.next;
            p.next = linkNode;
            linkNode = p;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (linkNode != null) {
            arrayList.add((Integer) linkNode.data);
            linkNode = linkNode.next;
        }
        return arrayList;
    }
}

