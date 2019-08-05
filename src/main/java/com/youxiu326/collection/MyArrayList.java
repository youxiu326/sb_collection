package com.youxiu326.collection;

import com.youxiu326.inter.MyList;

import java.util.Arrays;

/**
 * 自定义ArrayList
 * @param <E>
 */
public class MyArrayList<E> implements MyList<E> {


    // 保存ArrayList中数据的数组
    private transient Object[] elementData;

    // ArrayList 实际长度
    private int size;

    public MyArrayList(){
        this(10);
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity<0){
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
        //初始化数组
        elementData = new Object[initialCapacity];
    }

    @Override
    public void add(E object) {

    }

    @Override
    public void add(int index, E object) {
        ensureExplicitCapacity(size+1);
        elementData[size++] = object;
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E object) {
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

    /**
     * 扩容的方法
     * ArrayList 默认扩容1.5倍
     * @param minCapacity
     */
    public void ensureExplicitCapacity(int minCapacity){
        // 如果存入的数据 超过了默认数组初始容量，就要扩容
        if (minCapacity - elementData.length > 0){
            // 原来数组的长度
            int oldCapacity = elementData.length;
            // 扩大1.5倍后的长度  [    >>1     等于  除以 2]
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity){
                //如果初始长度为1 防止出现这种情况 1+(1/2)=1
                newCapacity = minCapacity;
            }
            // 扩容
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
}