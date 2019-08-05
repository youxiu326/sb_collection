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

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public boolean add(E object) {
        ensureExplicitCapacity(size+1);
        elementData[size++] = object;
        return true;
    }

    @Override
    public boolean add(int index, E object) {
        rangeCheck(index);
        ensureExplicitCapacity(index+1);
        // System.arraycopy 将数组从指定的源数组(从指定位置开始)复制到目标数组的指定位置
        System.arraycopy(elementData, index, elementData, index+1, size-index);
        elementData[index] = object;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        //获取删除下标之后的元素 往前移动一位 并将最后一位置空 让垃圾回收期回收
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public boolean remove(E object) {
        // ArrayList 可以保存null
        if (object == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (object.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
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

    /**
     * 删除最开始的一个元素
     * @param index
     */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        //获取删除下标之后的元素 往前移动一位 并将最后一位置空 让垃圾回收期回收
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("数组越界啦!");
        }
    }

}