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
        /*LinkNode<Integer> head = new LinkNode<>(0);
        LinkNode<Integer> moveNode = head;
        for (int i = 0; i < 10; i++) {
            moveNode.setNext(new LinkNode<>(i));
            moveNode = moveNode.getNext();
        }
        System.out.println(findCountdownM(head, 1));
        System.out.println(findCountdownM(head, 3));
        System.out.println(findCountdownM(head, -1));
        System.out.println(findCountdownM(head, 0));
        System.out.println(findCountdownM(head, 10));
        System.out.println(findCountdownM(head, 11));
        */

        System.out.println("------");


        /*
        ListNode listNode = new ListNode(0);
        ListNode moveListNode = listNode;
        for (int i = 0; i < 10; i++) {
            moveListNode.next = new ListNode(i);
            moveListNode = moveListNode.next;
        }
        ArrayList<Integer> arrayList = reverseLinkNode(listNode);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(reverseLinkNode(new ListNode(2)));
        System.out.println(reverseLinkNode(null));
        */
        ListNode listNode1 = new ListNode(67);
        listNode1.next = new ListNode(0);
        listNode1.next.next = new ListNode(24);
        listNode1.next.next.next = new ListNode(58);
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
        if (head == null || (head.getNext() == null && m != 0))
            return null;
        if (head.getNext() == null && m == 0)
            return head;
        LinkNode pre = head;
        LinkNode post = head;
        int step = 0;
        while (pre.getNext() != null) {
            pre = pre.getNext();
            if (step < m) {
                step++;
            } else if (step == m) {
                post = post.getNext();
            }
        }
        if (step < m || m < 0)
            post = null;
        return post;
    }

    /**
     * 反转链表
     *
     * @param listNode 链表第一个节点，而不是head指针
     * @return
     */
    private static ArrayList<Integer> reverseLinkNode(ListNode listNode) {
        //空链表
        if (listNode == null)
            return new ArrayList<>();
        //单节点
        if (listNode.next == null) {
            return new ArrayList<>(listNode.val);
        }
        //核心算法
        ListNode temp = listNode;
        ListNode p;
        while (temp.next != null) {
            p = temp.next;
            temp.next = p.next;
            p.next = listNode;
            listNode = p;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (listNode != null) {
            arrayList.add(listNode.val);
            listNode = listNode.next;
        }
        return arrayList;
    }
}


class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}

