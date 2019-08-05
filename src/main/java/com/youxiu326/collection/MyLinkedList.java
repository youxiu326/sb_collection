package com.youxiu326.collection;

import com.youxiu326.inter.MyList;

import java.util.LinkedList;

public class MyLinkedList<E> implements MyList<E> {

    // LinkedList 链表实际长度
    transient int size;

    // 第一个元素(头元素，为了查询开始)
    transient Node<E> first;

    // 最后一个元素(尾元素，为了添加开始)
    transient Node<E> last;

    /**
     *  链表节点存储元素
     */
    private static class Node<E> {
        // 存放元素的值
        E item;
        // 上一个节点Node
        Node prev;
        // 下一个节点Node
        Node next;

        Node(){}
        /**
         * 构造函数
         * @param prev 上一个节点
         * @param element 存入元素的值
         * @param next 下一个节点
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList() {
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkPositionIndex(index);
        if (index == size)
            linkLast(element);
        else
            System.out.println("");
            //linkBefore(element, node(index));
        return true;
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("The subscript crossing the line");
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * Links e as last element.
     */
    void linkLast(E e) {
        //取得最后一个元素
        // 1.如果最后一个元素为null 说明该链表为空
        // 2.若果最后一个元素不为null 说明链表不为空
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

}