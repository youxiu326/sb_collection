package com.youxiu326.collection;

import com.youxiu326.inter.MyList;

/**
 * 自定义ArrayList
 * @param <E>
 */
public class MyArrayList<E> implements MyList<E> {

    @Override
    public void add(E object) {

    }

    @Override
    public void add(int index, E object) {

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
}