package com.youxiu326.inter;

/**
 * 自定义List 接口
 * @param <E>
 */
public interface MyList<E> {

    public void add(E object);

    public void add(int index, E object);

    public Object remove(int index);

    public boolean remove(E object);

    public int getSize();

    public Object get(int index);
} 